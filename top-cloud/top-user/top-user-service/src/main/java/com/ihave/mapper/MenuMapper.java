package com.ihave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihave.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 系统菜单 MenuMapper
 * @author CHENYU_自动生成
 * @email ${email}
 * @date 2021-09-07 10:09:21
 */
public interface MenuMapper extends BaseMapper<Menu>{


    List<Menu> selectUserTree(@Param("principal") Long principal);
}