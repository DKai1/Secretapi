package com.kevin.secret.entity.vo;

import lombok.Data;

/**
 * @author dengkai
 */
@Data
public class UserAddVo {

    private String userName;

    /**
     * 用户绑定手机
     */
    private String userPhone;

    /**
     * 密码
     */
    private String password;
}
