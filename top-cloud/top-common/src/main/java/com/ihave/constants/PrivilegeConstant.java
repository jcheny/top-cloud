package com.ihave.constants;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/9/7 下午2:44
 */
public class PrivilegeConstant {
    //====================================================================================
    //该类中不允许出现其他非权限内容的常量或变量
    //====================================================================================

    /***************************************************************************************************/

    /* 菜单控制器 */
    public static final String MENU_QUERY = "hasAuthority('menu_query')";
    public static final String MENU_SAVE = "hasAuthority('menu_save')";
    public static final String MENU_UPDATE = "hasAuthority('menu_update')";
    public static final String MENU_DELETE = "hasAuthority('menu_delete')";

    /* 角色控制器 */
    public static final String ROLE_QUERY = "hasAuthority('role_query')";
    public static final String ROLE_SAVE = "hasAuthority('role_save')" ;
    public static final String ROLE_UPDATE = "hasAuthority('role_update')";
    public static final String ROLE_DELETE = "hasAuthority('role_delete')";
    public static final String ROLE_AUTHORIZE = "hasAuthority('role_authorize')";

    /* 角色授权资源相关控制器 */
    public static final String ROLE_RES_SAVE = "hasAuthority('role_res_save')";

    /* 用户角色赋予 */
    public static final String USER_ROLE_SAVE = "hasAuthority('user_role_save')";
    public static final String USER_SAVE = "hasAuthority('user_save')";
    public static final String USER_QUERY = "hasAuthority('user_query')";
    public static final String USER_UPDATE = "hasAuthority('user_update')";
    public static final String USER_DELETE = "hasAuthority('user_delete')";
    /***************************************************************************************************/
    /* app控制器 */
    public static final String APP_QUERY = "hasAuthority('app_query')";
    public static final String APP_SAVE = "hasAuthority('app_save')";
    public static final String APP_UPDATE = "hasAuthority('app_update')";
    public static final String APP_DELETE = "hasAuthority('app_delete')";

    /*  自动生成业务代码 */
    public static final String AUTO_QUERY = "hasAuthority('auto_query')";

    /* 客户端 */
    public static final String CLIENT_QUERY = "hasAuthority('client_query')";
    public static final String CLIENT_SAVE = "hasAuthority('client_save')";
    public static final String CLIENT_DELETE = "hasAuthority('client_delete')";
    public static final String CLIENT_UPDATE = "hasAuthority('client_update')";

    /* 路由控制器 */
    public static final String ROUTE_QUERY = "hasAuthority('route_query')";
    public static final String ROUTE_SAVE = "hasAuthority('route_save')";
    public static final String ROUTE_UPDATE = "hasAuthority('route_update')";
    public static final String ROUTE_DELETE = "hasAuthority('route_delete')";

    /* app版本控制器 */
    public static final String UPGRADE_QUERY = "hasAuthority('upgrade_query')";
    public static final String UPGRADE_SAVE = "hasAuthority('upgrade_save')";
    public static final String UPGRADE_UPDATE = "hasAuthority('upgrade_update')";
    public static final String UPGRADE_DELETE = "hasAuthority('upgrade_delete')";

    /***************************************************************************************************/
    /* 资源控制器 */
    public static final String RES_SAVE = "hasAuthority('res_save')";
    public static final String RES_UPDATE = "hasAuthority('res_update')";
    public static final String RES_DELETE = "hasAuthority('res_delete')";

}
