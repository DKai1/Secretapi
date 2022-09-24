package com.kevin.secret.service;

import com.kevin.secret.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.secret.entity.vo.UserAddVo;
import com.kevin.secret.util.ResultResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dengkai
 * @since 2022-09-23
 */
public interface UserService extends IService<User> {

    /**
     * 创建用户
     * @param vo
     * @return
     */
    ResultResponse createUser(UserAddVo vo);

    /**
     * 获取用户
     * @param userPhone
     * @return
     */
    ResultResponse findByUserPhoneAfter(String userPhone);
}
