package com.spring.bacisic.admin.common.constants;

/**
 * 静态文件
 *
 * @author zhangby
 * @date 2019-05-13 12:19
 */
public class Constants {


    /************************* 系统常量 *********************************/
    /**
     * yes_no: 0 否
     */
    public static final Integer YES_NO_0 = 0;
    /**
     * yes_no: 1 是
     */
    public static final Integer YES_NO_1 = 1;
    /**
     * 成功: success
     */
    public static final String SUCCESS = "success";
    /**
     * 失败: fail
     */
    public static final String FAIL = "fail";

    /**
     * 非过滤URL
     */
    public static final String FILTER_EXCLUDE_PATH = "/,/v2/api-docs,/swagger-resources/**,/swagger-ui.html**,/webjars/**,/sys/oauth/**";

    /**
     * 登录授权类型
     */
    public static final String AUTH_TYPE = "auth_type";

    /**
     * 当前用户id
     */
    public static final String CURRENT_USER_ID = "current_user_id";

    /**
     * 管理员角色id
     */
    public static final String ROLE_ADMIN_ID = "1";

    /**
     * 系统管理员id
     */
    public static final String USER_ADMIN_ID = "1";

    /**
     * 系统父类id
     */
    public static final String SYS_PARENT_ID = "0";

    /************************* 通用错误信息 ******************************/

    /**  错误码 (ERROR CODE)  */
    /**
     * 正常 code：000
     */
    public static final String NORMAL = "000";
    /**
     * 错误 code：999
     */
    public static final String ERROR = "999";


    /**  错误信息 (ERROR MESSAGE) */
    /**
     * 正常 code：000
     */
    public static final String MSG_000 = "成功";
    /**
     * 错误 code：999
     */
    public static final String MSG_999 = "系统错误";

}
