package com.ihave.utils;

import cn.hutool.core.date.DateTime;
import com.ihave.model.autocode.AutoCodeConfig;
import com.ihave.model.autocode.AutoConfigModel;
import com.ihave.model.autocode.SnowflakeIdWorker;
import com.ihave.model.autocode.TableInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 自动生成 通用类
 */
public class AutoCodeUtil {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();

        //java代码模板
        templates.add("auto_code/model/Entity.java.vm");
        templates.add("auto_code/mapperxml/EntityMapper.xml.vm");
        templates.add("auto_code/service/EntityService.java.vm");
        templates.add("auto_code/mapper/EntityMapper.java.vm");
        templates.add("auto_code/controller/EntityController.java.vm");
        templates.add("auto_code/request/EntityRequest.java.vm");
        templates.add("auto_code/service/impl/EntityServiceImpl.java.vm");
        templates.add("auto_code/vo/EntityVo.java.vm");
        templates.add("auto_code/wrapper/EntityWrapper.java.vm");
        templates.add("auto_code/vue/EntityApi.js.vm");
        templates.add("auto_code/vue/EntityViews.vue.vm");
        return templates;
    }


    /**
     * 创建单表
     */
    public static void autoCodeOneModel(TableInfo tableInfo, AutoConfigModel autoConfigModel) {
        AutoCodeConfig autoCodeConfig = new AutoCodeConfig();
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        Map<String, Object> map = new HashMap<>();
        //数据库表数据
        map.put("tableInfo", tableInfo);
        //字段集合
        map.put("beanColumns", tableInfo.getBeanColumns());
        //配置文件
        map.put("SnowflakeIdWorker", SnowflakeIdWorker.class);
        //class类路径
        map.put("parentPack", autoCodeConfig.getConfigkey("parentPack"));
        //作者
        map.put("author", autoConfigModel.getAuthor());
        //时间
        map.put("datetime", new DateTime());
        //sql需要的权限父级pid
        map.put("pid", autoConfigModel.getPid());
        //网关path（有前端的时候使用）
        map.put("gateWay",autoConfigModel.getGateWayPath());
        map.put("vueModel",autoConfigModel.getVueModel());

        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            try {
                String targetPath = autoConfigModel.getParentPath();
                String filepath = getCoverFileName(template, tableInfo, autoCodeConfig.getConfigkey("parentPack"), targetPath);
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                File file = new File(filepath);
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                if (!file.exists())
                    file.createNewFile();
                try (FileOutputStream outStream = new FileOutputStream(file);
                     OutputStreamWriter writer = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
                     BufferedWriter sw = new BufferedWriter(writer)) {
                    tpl.merge(context, sw);
                    sw.flush();
                    System.out.println("成功生成Java文件:" + filepath);
                }
            } catch (IOException e) {
                try {
                    throw new Exception("渲染模板失败，表名：" + "c" + "\n" + e.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    /**
     * 自动生成压缩文件方法
     */
    public static void autoCodeOneModel(TableInfo tableInfo, AutoConfigModel autoConfigModel, ZipOutputStream zip) {
        AutoCodeConfig autoCodeConfig = new AutoCodeConfig();
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        Map<String, Object> map = new HashMap<>();
        //数据库表数据
        map.put("tableInfo", tableInfo);
        //字段集合
        map.put("beanColumns", tableInfo.getBeanColumns());
        //配置文件
        map.put("SnowflakeIdWorker", SnowflakeIdWorker.class);
        //class类路径
        map.put("parentPack", autoCodeConfig.getConfigkey("parentPack"));
        //作者
        map.put("author", autoConfigModel.getAuthor());
        //时间
        map.put("datetime", new DateTime());
        //sql需要的权限父级pid
        map.put("pid", autoConfigModel.getPid());
        VelocityContext velocityContext = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            try {
                String filepath = getCoverFileName(template, tableInfo, autoCodeConfig.getConfigkey("parentPack"), "");
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                StringWriter sw = new StringWriter();
                tpl.merge(velocityContext, sw);
                zip.putNextEntry(new ZipEntry(filepath));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                try {
                    throw new Exception("渲染模板失败，表名：" + "c" + "\n" + e.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static String getCoverFileName(String template, TableInfo tableInfo, String packageName, String targetPath) {

        String separator = File.separator;
        String packagePath = targetPath + separator + "src" + separator + "main" + separator + "java" + separator;
        String resourcesPath = targetPath + separator + "src" + separator + "main" + separator + "resources" + separator;
        String vuePath = targetPath + separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", separator) + separator;
        }

        if (template.contains("Entity.java.vm")) {//model.java
            return packagePath + "entity" + separator + tableInfo.getJavaTableName() + ".java";
        }
        if (template.contains("EntityExample.java.vm")) {//modelExample.java
            return packagePath + "entity" + separator + tableInfo.getJavaTableName() + "Example.java";
        }

        if (template.contains("EntityMapper.java.vm")) {//daomapper.java
            return packagePath + "mapper" + separator + tableInfo.getJavaTableName() + "Mapper.java";
        }
        if (template.contains("EntityMapper.xml.vm")) {//daomapper.xml
            return resourcesPath + "mapper" + separator + tableInfo.getJavaTableName() + "Mapper.xml";
        }

        if (template.contains("EntityService.java.vm")) {
            return packagePath + "service" + separator + tableInfo.getJavaTableName() + "Service.java";
        }
        if (template.contains("EntityController.java.vm")) {
            return packagePath + "controller" + separator + tableInfo.getJavaTableName() + "Controller.java";
        }
        if (template.contains("EntityServiceImpl.java.vm")) {
            return packagePath + "service" + separator + "impl" + separator + tableInfo.getJavaTableName() + "ServiceImpl.java";
        }
        if (template.contains("EntityVo.java.vm")) {
            return packagePath + "vo" + separator + tableInfo.getJavaTableName() + "Vo.java";
        }
        if (template.contains("EntityRequest.java.vm")) {
            return packagePath + "request" + separator + tableInfo.getJavaTableName() + "Request.java";
        }
        if (template.contains("EntityWrapper.java.vm")) {
            return packagePath + "wrapper" + separator + tableInfo.getJavaTableName() + "Wrapper.java";
        }
        if (template.contains("EntityViews.vue.vm")) {
            return vuePath + "vue" + separator + tableInfo.getVueTableName() + ".vue";
        }
        if (template.contains("EntityApi.js.vm")) {
            return vuePath + "vue" + separator + tableInfo.getVueTableName() + ".js";
        }

        return "";
    }
}
