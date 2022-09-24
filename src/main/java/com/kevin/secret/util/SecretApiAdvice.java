package com.kevin.secret.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author dengkai
 */
public class SecretApiAdvice implements MethodBeforeAdvice, AfterReturningAdvice

    {
        private static final Logger log = LoggerFactory.getLogger(SecretApiAdvice.class);
        private static final String RESPONSE_DATA_KEY = "data";
        private SecretApiProperties secretApiProperties;

    public SecretApiAdvice(SecretApiProperties secretApiProperties) {
        this.secretApiProperties = secretApiProperties;
    }

        @Override
        public void before(Method method, Object[] args, Object target) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("before requestParam decrypt");
        Map<String, Object> requestParam = this.decryptRequestParam(attr.getRequest());
        this.resetRequestParam(method, args, requestParam);
    }

        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
        if (ObjectUtil.isEmpty(returnValue)) {
            log.error("afterReturning returnValue is null");
        } else if (!(returnValue instanceof ResultResponse)) {
            log.error("afterReturning returnValue instanceof ResultResponse don't match");
        } else {
            ResultResponse resultResponse = (ResultResponse)returnValue;
            Object data = resultResponse.getData();
            if (ObjectUtil.isEmpty(data)) {
                log.error("afterReturning returnValue data is null.");
            } else {
                String keyStr = this.randomKey(16);
                byte[] keyByte = keyStr.getBytes();
                AES aes = new AES(Mode.ECB, Padding.ISO10126Padding, keyByte);
                SecretResponse secretResponse = new SecretResponse();
                secretResponse.setEncryptData(aes.encryptBase64(JSONUtil.toJsonStr(data)));
                RSA rsa = SecureUtil.rsa((String)null, this.secretApiProperties.getPublicKey());
                secretResponse.setEncryptKey(rsa.encryptBase64(keyByte, KeyType.PublicKey));
                log.info("afterReturning sign");
                secretResponse.setSign(SecureUtil.md5(secretResponse.getEncryptData() + secretResponse.getEncryptKey()));
                resultResponse.setData(secretResponse);
                log.info("afterReturning encrypt end");
            }
        }
    }

        private Map<String, Object> decryptRequestParam(HttpServletRequest request) {
        String encryptData = request.getParameter("encryptData");
        String encryptKey = request.getParameter("encryptKey");
        String sign = request.getParameter("sign");
        log.info("start check sign...");
        String signParamContent = encryptData + encryptKey;
        String signStr = SecureUtil.md5(signParamContent);
        if (!StrUtil.isEmpty(signStr) && signStr.equals(sign)) {
            byte[] keyByte;
            try {
                log.info("parse aes encryptKey");
                RSA rsa = SecureUtil.rsa(this.secretApiProperties.getPrivateKey(), (String)null);
                keyByte = rsa.decrypt(encryptKey, KeyType.PrivateKey);
            } catch (Exception var11) {
                var11.printStackTrace();
                throw new RuntimeException("parameter error，key fail");
            }

            try {
                log.info("decrypt data ");
                AES aes = new AES(Mode.ECB, Padding.ISO10126Padding, keyByte);
                String data = aes.decryptStr(encryptData);
                return (Map)JSONUtil.toBean(data, Map.class);
            } catch (Exception var10) {
                var10.printStackTrace();
                throw new RuntimeException("parameter error，decrypt fail");
            }
        } else {
            log.error("check sign is fail");
            throw new RuntimeException("check sign is fail");
        }
    }

        private void resetRequestParam(Method method, Object[] args, Map<String, Object> requestParam) {
        Parameter[] parameters = method.getParameters();
        Class<?>[] paramClass = method.getParameterTypes();

        for(int index = 0; index < paramClass.length; ++index) {
            Class<?> clazz = paramClass[index];
            Parameter paramObj = parameters[index];
            Annotation[] annotations = paramObj.getAnnotations();
            if (annotations.length == 0 && clazz != HttpServletRequest.class && clazz != HttpServletResponse.class) {
                if (this.isBaseType(clazz)) {
                    String attrName = paramObj.getName();
                    Object attrVal = requestParam.get(attrName);
                    args[index] = this.getBaseTypeVal(attrVal, clazz);
                } else {
                    Field[] fields = clazz.getDeclaredFields();
                    Field[] var11 = fields;
                    int var12 = fields.length;

                    for(int var13 = 0; var13 < var12; ++var13) {
                        Field field = var11[var13];
                        field.setAccessible(true);

                        try {
                            field.set(args[index], this.getBaseTypeVal(requestParam.get(field.getName()), field.getType()));
                        } catch (Exception var16) {
                            var16.printStackTrace();
                            throw new RuntimeException("parameter：" + field.getName() + "set fail!");
                        }
                    }
                }
            }
        }

    }

        private boolean isBaseType(Class clazz) {
        return clazz == String.class || clazz == Byte.class || clazz == Short.class || clazz == Integer.class || clazz == Long.class || clazz == Double.class;
    }

        private <T> T getBaseTypeVal(Object attrVal, Class<T> clazz) {
        if (attrVal == null) {
            return null;
        } else {
            T t = null;
            if (clazz == String.class) {
                t = (T) attrVal.toString();
            } else if (clazz == Byte.class) {
                t = (T) Byte.valueOf(attrVal.toString());
            } else if (clazz == Short.class) {
                t = (T) Short.valueOf(attrVal.toString());
            } else if (clazz == Integer.class) {
                t = (T) Integer.valueOf(attrVal.toString());
            } else if (clazz == Long.class) {
                t = (T) Long.valueOf(attrVal.toString());
            } else if (clazz == Double.class) {
                t = (T) Double.valueOf(attrVal.toString());
            }

            return t;
        }
    }

        private String randomKey(int count) {
        String strRandomPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String randomKey = RandomUtil.randomString(strRandomPool, count);
        return randomKey;
    }

}
