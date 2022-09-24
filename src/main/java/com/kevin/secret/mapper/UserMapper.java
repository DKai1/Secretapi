package com.kevin.secret.mapper;

import com.kevin.secret.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dengkai
 * @since 2022-09-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户
     * @param userPhone
     * @return
     */
    User findByUserPhoneAfter(String userPhone);
}
