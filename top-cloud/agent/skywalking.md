# vm参数
```
-javaagent:skywalking-agent.jar 
-Dskywalking.agent.service_name=top-gateway 
-Dskywalking.collector.backend_service=127.0.0.1:11800
```
选中你本地skywalking-agent.jar 的位置
skywalking.agent.service_name = 你的服务名
skywalking.collector.backend_service = SW上传的服务器

下载 SW
```
https://archive.apache.org/dist/skywalking/
```

修改你的持久化方式