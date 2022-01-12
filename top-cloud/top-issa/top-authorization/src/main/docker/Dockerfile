FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD *.jar app.jar
ADD coinexchange.jks coinexchange.jks
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime