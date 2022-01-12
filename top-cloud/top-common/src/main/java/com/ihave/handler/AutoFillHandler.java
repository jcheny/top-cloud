package com.ihave.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充属性字段
 *
 * @Author: chen
 * @Date: 2021/4/20 10:24 AM
 * @Version 1.0
 */
@Component
public class AutoFillHandler implements MetaObjectHandler {

    /**
     * 3 种情况不填充
     * 1 值为null
     * 2 自动类型不匹配
     * 3 没有改字段
     */

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = getUserId();
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createUser", Long.class, userId); // 创建人的填充
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = getUserId();
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateUser", Long.class, userId); // 修改人的填充
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if (authentication != null) {
            String principal = authentication.getPrincipal().toString();
            try {
                userId = Long.valueOf(principal);
            } catch (Exception e) {
                userId = 0L;
            }

        }
        return userId;
    }
}
