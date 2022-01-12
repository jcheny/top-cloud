package com.ihave.service;

import com.ihave.constant.LoginConstant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/23 下午4:01
 */
public class BaseUserService {


    protected UserDetails getUser(Long userId, JdbcTemplate jdbcTemplate, String sql) {

        // 1 使用用户名查询用户
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            if (rs.wasNull()) {
                throw new UsernameNotFoundException("id" + userId + "不存在");
            }
            long id = rs.getLong("id"); // 用户的id
            String password = rs.getString("password"); // 用户的密码
            int status = rs.getInt("status");
            return new User(   // 3 封装成一个UserDetails对象，返回
                    String.valueOf(id), //使用id->username
                    password,
                    status == 0,
                    true,
                    true,
                    true,
                    getSysUserPermissions(userId, jdbcTemplate)

            );
        }, userId);
    }

    protected UserDetails getUser(String param, JdbcTemplate jdbcTemplate, String sql) {
        // 1 使用用户名查询用户
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            if (rs.wasNull()) {
                throw new UsernameNotFoundException("帐号" + param + "不存在");
            }
            long id = rs.getLong("id"); // 用户的id
            String password = rs.getString("password"); // 用户的密码
            int status = rs.getInt("status");
            return new User(   // 3 封装成一个UserDetails对象，返回
                    String.valueOf(id), //使用id->username
                    password,
                    status == 0,
                    true,
                    true,
                    true,
                    getSysUserPermissions(id, jdbcTemplate)

            );
        }, param, param);
    }


    /**
     * 纠正用户的名称
     *
     * @param username 用户的id
     * @return
     */
    protected String adjustUsername(String username, JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject(LoginConstant.QUERY_ADMIN_USER_WITH_ID, String.class, username);
    }

    /**
     * // 2 查询这个用户对应的权限
     * 通过用户的id 查询用户的权限
     *
     * @param id
     * @return
     */
    protected Collection<? extends GrantedAuthority> getSysUserPermissions(long id, JdbcTemplate jdbcTemplate) {

        // 1 当用户为超级管理员时，他拥有所有的权限数据
        List<String> list = jdbcTemplate.queryForList(LoginConstant.QUERY_ROLE_CODE_SQL, String.class, id);

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        List<String> permissions = new ArrayList<>(); // 权限的名称
        for (String roleCode : list) {

            if (LoginConstant.ADMIN_ROLE_CODE.equals(roleCode)) { // 超级用户
                List<String> permissions1 = jdbcTemplate.queryForList(LoginConstant.QUERY_ALL_PERMISSIONS, String.class);

                if (!CollectionUtils.isEmpty(permissions1)) {
                    permissions.addAll(permissions1);
                }

            } else { // 2 普通用户，需要使用角色->权限数据
                List<String> permissions1 = jdbcTemplate.queryForList(LoginConstant.QUERY_PERMISSION_SQL, String.class, id);
                if (!CollectionUtils.isEmpty(permissions1)) {
                    permissions.addAll(permissions1);
                }
            }
        }

        return permissions.stream()
                .distinct() // 去重
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    protected UserDetails getMember(String username, JdbcTemplate jdbcTemplate, String sql) {
        // 1 使用用户名查询用户
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            if (rs.wasNull()) {
                throw new UsernameNotFoundException("帐号" + username + "不存在");
            }
            long id = rs.getLong("id"); // 用户的id
            String password = rs.getString("password"); // 用户的密码
            int status = rs.getInt("status");
            return new User(   // 3 封装成一个UserDetails对象，返回
                    String.valueOf(id), //使用id->username
                    password,
                    status == 0,
                    true,
                    true,
                    true,
                    Collections.emptyList()

            );
        }, username, username);
    }
}
