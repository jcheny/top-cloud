# top-cloud
一个基于springcloud + vue 的后台项目，包含自动生成代码的功能，使用比市面上的操作起来非常的简单，适用于毕业项目设计的首选项目，新手操作也非常友好，集成的技术也是市面上常用的技术

# 后端部分
下载项目直接导入idea工具中等待下载依赖
直接启动即可，我使用的都是我自己的外网环境（nacos、mysql、redis），后面我会把这些去除
建议大家去修改里面的这些环境

需要添加启动的环境变量（所有的都需要） spring.profiles.active=dev

```
top-cloud
  -- top-common （公共包）
  -- top-issa （基础服务）
    -- top-gateway
    -- top-authorization
  -- top-user （后端用户服务）
    -- top-user-api
    -- top-user-serivce
  -- top-system （系统服务）
    -- top-system-api
    -- top-system-service
  -- top-menber （会员服务【没用到】）
    -- top-menber-api
    -- top-menber-service
```
# 前端部分

下载项目 然后npm install 
然后直接启动即可 npm run dev

## 自动代码使用步骤

- 1.先在菜单表中添加菜单
- 2.设计数据库表
- 3.填写生成代码数据（自动代码菜单里）
- 4.将生成好了的代码复制到对应的位置里面
