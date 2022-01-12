package com.ihave.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.entity.User;
import com.ihave.request.UserRequest;
import com.ihave.vo.UserVo;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
public interface UserService extends IService<User>{


    Long saveUser(UserRequest user);

    Object updateUser(UserRequest user);

    Object forgetPwd(UserRequest user);

    Object updatePwd(UserRequest user);

    Page<UserVo> pageUser(Page<User> page, String username, String mobile,String email,
                          Integer status, Long roleId);

    UserVo userInfo(String userId);
}
