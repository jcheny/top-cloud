package com.ihave.constant;

/**
 * @Author: chen
 * @Date: 2021/4/9 2:26 PM
 * @Version 1.0
 */
public class LoginConstant {

    /**
     * 后台管理人员
     */
    public static final String GRANT_TYPE = "grant_type";

    public static final String LOGIN_TYPE = "loginType";

    public static final String LOGIN_TYPE_ADMIN = "admin";

    public static final String LOGIN_TYPE_MEMBER = "member";

    /**
     * 使用用户名查询用户
     */

    public static final String QUERY_ADMIN_SQL =
            "SELECT `id` ,`username`, `password`, `status` FROM top_user WHERE username = ? or mobile = ? and is_deleted = 0";

    public static final String QUERY_MEMBER_SQL =
            "SELECT `id` ,`username`, `password`, `status` FROM top_member WHERE username = ? or mobile = ? and is_deleted = 0";


    /**
     * 查询用户的角色Code
     */
    public static final String QUERY_ROLE_CODE_SQL =
            "SELECT `code` FROM top_role LEFT JOIN top_user_role ON top_role.id = top_user_role.role_id WHERE top_user_role.user_id= ?";

    /**
     * 查询所有的权限名称
     */
    public static final String QUERY_ALL_PERMISSIONS =
            "SELECT `key` FROM top_menu";

    /**
     * 对于我们非超级用户，我们需要先查询 role->permissionId->permission
     */
    public static final String QUERY_PERMISSION_SQL =
            "SELECT `key` FROM top_menu LEFT JOIN top_role_menu ON top_role_menu.menu_id = top_menu.id LEFT JOIN top_user_role  ON top_role_menu.role_id = top_user_role.role_id WHERE top_user_role.user_id = ?";

    /**
     * 超级管理员的角色的Code
     */
    public static final String ADMIN_ROLE_CODE = "admin";

    public static final String REFRESH_TYPE = "REFRESH_TOKEN";

    /**
     * 使用后台用户的id 查询用户名称
     */
    public static final String QUERY_ADMIN_USER_WITH_ID = "SELECT `username` FROM top_user where id = ? and is_deleted = 0";


}
