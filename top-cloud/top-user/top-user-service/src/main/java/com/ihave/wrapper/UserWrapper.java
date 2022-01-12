package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.User;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.UserVo;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/28 下午2:57
 */
public class UserWrapper extends BaseEntityWrapper<User, UserVo> {

    public static UserWrapper build(){
        return new UserWrapper();
    }

    @Override
    public UserVo entityVO(User entity) {
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(entity,userVo);
        return userVo;
    }

    @Override
    public List<UserVo> listVO(List<User> list) {
        return super.listVO(list);
    }
}
