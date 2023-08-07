
# 二开推荐阅读[如何提高项目构建效率](https://developers.weixin.qq.com/miniprogram/dev/wxcloudrun/src/scene/build/speed.html)
# 选择构建用基础镜像。如需更换，请到[dockerhub官方仓库](https://hub.docker.com/_/java?tab=tags)自行选择后替换。
FROM springci/graalvm-ce:java17-0.12.x
VOLUME /tmp
ADD 1110-jilijili-system/target/1110-jilijili-system-1.0-SNAPSHOT.jar app.jar
EXPOSE 1110
CMD [ "java","-jar","app.jar", "-Xms512m -Xmx512m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError"]
MAINTAINER Amani

