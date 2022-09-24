package com.kevin.secret.service.impl;

import com.kevin.secret.entity.User;
import com.kevin.secret.entity.vo.UserAddVo;
import com.kevin.secret.mapper.UserMapper;
import com.kevin.secret.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.secret.util.DateTimeUtil;
import com.kevin.secret.util.MD5Util;
import com.kevin.secret.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dengkai
 * @since 2022-09-23
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResultResponse createUser(UserAddVo vo) {
        User user = new User();
        BeanUtils.copyProperties(vo,user);

        user.setPassword(MD5Util.getMd5String(vo.getPassword()));

        //生成账号，时间戳+电话号码 md5 加密生成字符串
        String key = System.currentTimeMillis() + vo.getUserPhone();
        log.info("key : " + key);

        user.setUserAccount(key);
        user.setCreateTime(DateTimeUtil.getCurrentTime());

        boolean result =  save(user);
        if (result) {
            User userAfter = baseMapper.findByUserPhoneAfter(user.getUserPhone());
            return ResultResponse.ok(userAfter);
        }
        return ResultResponse.failed();
    }

    @Override
    public ResultResponse findByUserPhoneAfter(String userPhone) {
        User userAfter = baseMapper.findByUserPhoneAfter(userPhone);
        if (Objects.nonNull(userAfter)) {
            return ResultResponse.ok(userAfter);
        }

        return ResultResponse.failed();
    }
}
