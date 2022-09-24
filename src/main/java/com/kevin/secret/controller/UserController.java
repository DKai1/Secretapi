package com.kevin.secret.controller;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import com.kevin.secret.annotation.SecretApi;
import com.kevin.secret.entity.vo.UserAddVo;
import com.kevin.secret.service.UserService;
import com.kevin.secret.util.ResultResponse;
import com.kevin.secret.util.SecretResponse;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dengkai
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Description("创建用户")
    @PostMapping("/user/create")
    public ResultResponse createUser(@RequestBody UserAddVo vo) {
        return userService.createUser(vo);
    }

    @SecretApi
    @Description("获取用户")
    @GetMapping("/user/get")
    public ResultResponse findByPhone(String phone){
        return userService.findByUserPhoneAfter(phone);
    }

    public static void main(String[] args) {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChlB68xGQ5LnP2Rx/QdDnXqw0iXDZq66gfyMV9XWUGDyvDrwMVtmIIIE7Vilrxco4Hi03WGF6C1qfFz72FebhYFqCjMjhQMP08JAIMf4xmIAKvapMDbs6B4Evg0hGDoI8EjDKa3EYiDetB8we+XFFcsO04IULl015IXmSCB/OkuAuv6w9ePEetPwcOpKJweQJW1mTIEbEngmYqy738TG1gVDbfy7wdHNbqZIRE2HramW7cCSlx6qTUmv2+gwNRpUh7egtWRF2S7zzovsJwPVQJdMYpHnjD2AD41OGpx0z2hKnD/AXDha+/i5LM/UiH5Q/JS5JO9qO9joY8dRpR8b+7AgMBAAECggEAA5eYa1fuZnsK5vHD2k2LL6lE3HuyVgQxM6uyAEyAP3c84NfWOLhaS5X0SrkNtop2b+J/83+NMll1V2d77ii7mXG/VoF6UQYVyy19witfwrF5fc7fv9bJzodfCHuDGisKou4+/cRh5sXqcgfmJyxEPsxMKdBLfXKKiQ9YKq/kkcfgo8r8nvIFuemA7JZM3kzhTwH+vJuqEUos16cL3mCEi5jtGTlLqvtBgpys9x8Mjsn4wT3Q0xqhCkpu1WJ9fkO/51yML6KTq10e/niYYNLbb8qvzB9lhg2NdZztch7nF9VTmDBubea1XfvNFoFSr1IV+05f7+4ovYwCD6MyXeI+0QKBgQDXW3/usQuyEwQ0o31xRxeRbJlMzteAoT1QKXzpTn/ApdlG9VEQY9aMNyDIhqSuJsmnWxYlKmQ198fViPFBQZgtcMXGCXSJ7GjckibwrYIC8yXczD+sWI0SACJiDE/TVTbvUeFcaFi5MI4xUtIWoxtyIcytspvl4BZ6LQ4wgzLoAwKBgQDAEmtoBiiPSeSUjhQi0vqMKzIRt8uNp8leEuXYHluJClesmFs9KFUj5JrH5vaJFcLbfxeNKqFKVJJM9pxSW7yalUFDr8ZJB+bGUWTkT8e+9qjxp4Kdx1kTUCEubn4dQaWuO0e1a9fgH2NzslJX6ISC4ONaR0wKWQheWgfbYsGH6QKBgQCDGME1NXOQMIFT5d5cEdHoBfsARb3sILtpTAgtIkskpBEzX+y8FHqrmneLemNPXG9PQGSntTmRnMd6VsW+WsbfipHqbyERQOfItfOceZL2Zru6wF9WdAVUWt6TXzlMxcgOQYL1tNOl0EgPAILBowL3JForttmuQw6Ly1ei8osPrwKBgAFmoP3rPZR993rV5nOykj0/e2gBrSxlUuSqwp5m6kf/mVAa9F9n0ecnHbHjj1zEmd07+hH/g5mlMEP7cHxxStjdydISkCGsOxSrN08q/cv4xJauoBdCgvXPGc7a43SXK/wvVoCOPs7Vq8YGQO9/NKg3t7DK4FUa/SKjCXeiKsMxAoGAPlxWE+LrGgtNrPok3v9arQNy1FVAFBbgMB9qtu924j2wunm+BOwkRlSsu6OpXX0MnnF9JDeE7NmncOfI3aLm4rVzrAwVzyDIh8ilmM0XzSaGREP7gkimjgFH7oehlPx1S3rsaX20PTcPxJHVjrJckqnEFcnioF9lpTiHeT/Mykk=";
        String encryptKey = "Pt9WIlNBmJFB1Lo1EGrWsmKeUdBJ3LHdwXACkfqEHK61EE62ur1T9qYmdZS9x8lfEnYtOxMo+dgDeOLRSG+HN0Ep4lPB+agHCocZ+tJsTyt/ZrGTdVDKo5zwN0++AIRRV9dqzDqMkSJUYxUBaJ7neKBlHV85xk36S/MdA054BxWLYumoubJOr7i853QAcCOa2IQEcRps/l5xk5HSw2gUlrVh1tbiFt3DsazSQ+VSdAE1MKa7aUSSDTLeoTpeXIg76q1ET+lhBD8wadscib9Qc830oHx3nNKbcHk74hOAssDRohOSM1yE7VrNBXY7XGI+HNVkm8ZhQy7Qbcay4lVDmg==";
        String encryptData = "sMYUPxZVLigDb05oeKs2qaQmpm1rXWUtjgNUnhCV/3o=";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoZQevMRkOS5z9kcf0HQ516sNIlw2auuoH8jFfV1lBg8rw68DFbZiCCBO1Ypa8XKOB4tN1hhegtanxc+9hXm4WBagozI4UDD9PCQCDH+MZiACr2qTA27OgeBL4NIRg6CPBIwymtxGIg3rQfMHvlxRXLDtOCFC5dNeSF5kggfzpLgLr+sPXjxHrT8HDqSicHkCVtZkyBGxJ4JmKsu9/ExtYFQ238u8HRzW6mSERNh62plu3Akpceqk1Jr9voMDUaVIe3oLVkRdku886L7CcD1UCXTGKR54w9gA+NThqcdM9oSpw/wFw4Wvv4uSzP1Ih+UPyUuSTvajvY6GPHUaUfG/uwIDAQAB";


        String strRandomPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String randomKey = RandomUtil.randomString(strRandomPool, 16);

        String data = "{\"code\":\"18689680971\"}";

//        byte[] keyByte = randomKey.getBytes();
//        AES aes = new AES(Mode.ECB, Padding.ISO10126Padding, keyByte);
//        SecretResponse encryptVO = new SecretResponse();
//        encryptVO.setEncryptData(aes.encryptBase64(data));
//        RSA rsa = SecureUtil.rsa((String)null, publicKey);
//        encryptVO.setEncryptKey(rsa.encryptBase64(keyByte, KeyType.PublicKey));
////        log.info("afterReturning sign");
//        encryptVO.setSign(SecureUtil.md5(encryptVO.getEncryptData() + encryptVO.getEncryptKey()));


        RSA rsa = SecureUtil.rsa(privateKey, (String) null);
        byte[] keyByte = rsa.decrypt(encryptKey, KeyType.PrivateKey);
        AES aes = new AES(Mode.ECB, Padding.ISO10126Padding, keyByte);
        String data1 = aes.decryptStr(encryptData);
        System.out.println(data1);


//        ResultResponse resultResponse = new ResultResponse();
//        resultResponse.setCode(0);
//        resultResponse.setMsg("12222");
//        resultResponse.setData("324234234");
//        ces(resultResponse);

//        System.out.println(encryptVO);
    }

//    private static void ces(Object value){
//        ResultResponse jsonResult = (ResultResponse) value;
//        jsonResult.setCode(1);
//
//    }
}
