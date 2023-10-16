package com.yupi.springbootinit.constant;

/**
 * 用户常量
 *
 * @author 咸余羊
 * 
 */
public interface UserConstant {

    /**
     * 图片验证码 redis 前缀
     */
    String CAPTCHA_PREFIX = "api:captchaId:";
    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    String USER_LOGIN_EMAIL_CODE ="user:login:email:code:";
    String USER_REGISTER_EMAIL_CODE ="user:register:email:code:";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";

    // endregion
}
