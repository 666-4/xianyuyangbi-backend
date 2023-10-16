package com.yupi.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户更新请求
 *
 * @author 咸余羊
 * 
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}