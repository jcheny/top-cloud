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

    public static final String REFRESH_TYPE = "REFRESH_TOKEN";

    public static final String USER_TABLE = "top_user";
    public static final String ROLE_TABLE = "top_role";
    public static final String USER_ROLE_TABLE = "top_user_role";
    public static final String MENU_TABLE = "top_menu";
    public static final String ROLE_MENU_TABLE = "top_user";

    /**
     * 使用用户名查询用户
     */

    public static final String QUERY_ADMIN_SQL =
            "SELECT `id` ,`username`, `password`, `status` FROM " + USER_TABLE + " WHERE username = ? or mobile = ? and is_deleted = 0";

    /**
     * 查询用户的角色Code
     */
    public static final String QUERY_ROLE_CODE_SQL =
            "SELECT `code` FROM " + ROLE_TABLE + " a LEFT JOIN " + USER_ROLE_TABLE + " b ON a.id = b.role_id WHERE b.user_id= ?";

    /**
     * 查询所有的权限名称
     */
    public static final String QUERY_ALL_PERMISSIONS =
            "SELECT `key` FROM " + MENU_TABLE;

    /**
     * 对于我们非超级用户，我们需要先查询 role->permissionId->permission
     */
    public static final String QUERY_PERMISSION_SQL =
            "SELECT `key` FROM " +
                    MENU_TABLE + " a " +
                    " LEFT JOIN " +
                    ROLE_MENU_TABLE + " b " +
                    " ON b.menu_id = a.id " +
                    " LEFT JOIN " +
                    USER_ROLE_TABLE + "c" +
                    " ON b.role_id = c.role_id " +
                    " WHERE c.user_id = ?";

    /**
     * 超级管理员的角色的Code
     */
    public static final String ADMIN_ROLE_CODE = "admin";

    /**
     * 使用后台用户的id 查询用户名称
     */
    public static final String QUERY_ADMIN_USER_WITH_ID = "SELECT `username` FROM " +
            USER_TABLE +
            "where id = ? and is_deleted = 0";


}
