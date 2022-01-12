package com.ihave.model.autocode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 配置文件model
 *
 * @author fuce
 * @ClassName: AutoConfigModel
 * @date 2021-01-17 23:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoConfigModel {
    /**
     * 表名称
     **/
    private String tableName;
    /**
     * 表描述
     **/
    private String tableComment;
    /**
     * 作者
     **/
    private String author;
    /**
     * 父菜单
     **/
    private String pid;
    /**
     * 自定义路径
     **/
    private String parentPath;

    private List<BeanColumn> beanColumns;

    /**
     * 网关path
     */
    private String gateWayPath;

    /**
     * 前端模块名
     */
    private String vueModel;
}
