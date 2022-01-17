# top-cloud
一个基于springcloud + vue 的后台项目，包含自动生成代码的功能，使用比市面上的操作起来非常的简单，适用于毕业项目设计的首选项目，新手操作也非常友好，集成的技术也是市面上常用的技术

下载必看文档：https://www.yuque.com/docs/share/fcf85dad-21d4-476a-8bd3-1cb307b85b31?# 《top-cloud》

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

### 自动代码属性解释
```
数据库表名:数据库的名字 （全部加前缀top_）
如果想修改可以去到common包 -> resources -> auto_code -> auto_code_config.yml 配置你数据库的前缀

模块名：对应的是java项目里面的模块
比如 /top-system/top-system-service 这样子，后期如果你自己有新的包 可以参考我项目的骨架结构
生成的代码就会方法在包下面，如果理解不了就天test ，然后生成完成之后可以去项目的路径下看看有什么变化

api网关名称：这个是对应gateway的前缀
比如top-system服务用的前缀是 system 这个可以在前端路由中心查看

前端模块名：对应是前端views包下的包名字 和 api包下的包名字，查询项目的这两个文件夹你就能理解了
```
## 技术支持

wx：cy669731945
