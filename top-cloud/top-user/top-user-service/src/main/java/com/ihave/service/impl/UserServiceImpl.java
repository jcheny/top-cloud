package com.ihave.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.api.R;
import com.ihave.entity.Role;
import com.ihave.entity.User;
import com.ihave.entity.UserRole;
import com.ihave.mapper.UserMapper;
import com.ihave.mapper.UserRoleMapper;
import com.ihave.request.UserRequest;
import com.ihave.service.RoleService;
import com.ihave.service.UserService;
import com.ihave.support.RkRule;
import com.ihave.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveUser(UserRequest request) {
        User user = new User();
        BeanUtil.copyProperties(request,user);
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        if(request.getRoleId() == null){
            Role role = roleService.getDefaultRole();
            request.setRoleId(role.getId());
        }
        userMapper.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(request.getRoleId());
        userRoleMapper.insert(userRole);
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateUser(UserRequest request) {
        User user = new User();
        BeanUtil.copyProperties(request,user);
        user.setPassword(null);
        if(request.getRoleId() != null){
            userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,request.getId()));
            UserRole userRole = new UserRole();
            userRole.setRoleId(request.getRoleId());
            userRole.setUserId(request.getId());
            userRoleMapper.insert(userRole);
        }
        return userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object forgetPwd(UserRequest user) {
        if(!user.getConfirmPassword().equals(user.getPassword())){
            throw new ApiException("两次密码不一致");
        }
        Object smsCode = redisTemplate.opsForValue().get(RkRule.build().flagKey(user.getType(), user.getMobile()));
        if(null == smsCode){
            throw new ApiException("用户修改密码失败，短信验证码失效");
        }
        else{
            // 短信验证码匹配
            if(!smsCode.toString().equals(user.getSmsCode())){
                throw new ApiException("用户修改密码失败，短信验证码不正确");
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            redisTemplate.delete(RkRule.build().flagKey(user.getType(), user.getMobile()));
            return R.data(userMapper.updateById(user));
        }
    }

    @Override
    public Object updatePwd(UserRequest user) {
        if(!user.getConfirmPassword().equals(user.getPassword())){
            throw new ApiException("两次密码不一致");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getPrincipal().toString());
        User authUser = userMapper.selectById(userId);
        if(new BCryptPasswordEncoder().matches(user.getOldPassword(),authUser.getPassword())){
            authUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return R.data(userMapper.updateById(authUser));
        }
        throw new ApiException("原密码错误");
    }

    @Override
    public Page<UserVo> pageUser(Page<User> page, String username, String mobile,String email,
                                 Integer status, Long roleId) {
        IPage<UserVo> pageUser = new Page<>(page.getCurrent(),page.getSize());
        return  userMapper.selectUser(pageUser,username, mobile,email,status,roleId);
    }

    @Override
    public UserVo userInfo(String userId) {

        return userMapper.selectUserInfo(userId);
    }

}
