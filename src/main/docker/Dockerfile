#Docker Hub上官方提供的java镜像
FROM  anapsix/alpine-java:8
#创建/tmp目录并持久化到Docker数据文件夹，因为Spring Boot使用的内嵌Tomcat容器默认使用/tmp作为工作目录
VOLUME /tmp
#将应用jar包复制到 app.jar
ADD tiafs-0.0.1-SNAPSHOT.jar app.jar
ENV spring.profiles.active="test"
ENTRYPOINT ["java","-jar","-Xmx350m","-Xms350m","/app.jar"]
