# Docker 镜像构建
# @author <a href="https://github.com/liyupi">程序员鱼皮</a>
#
FROM maven:3.8.1-jdk-8-slim as builder

# Copy local code to the container image.
VOLUME /home/backend
ADD yangbi.jar yangbi.jar

EXPOSE 8101

# Run the web service on container startup.
CMD ["java","-jar","/yangbi.jar"]