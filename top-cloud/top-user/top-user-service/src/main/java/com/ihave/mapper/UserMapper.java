package com.ihave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.entity.User;
import com.ihave.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    Page<UserVo> selectUser(@Param("page") IPage<UserVo> page, @Param("username") String username, @Param("mobile") String mobile, @Param("email") String email,
                            @Param("status") Integer status, @Param("roleId") Long roleId);

    UserVo selectUserInfo(@Param("userId") String userId);
}