FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE_PATH=target/*.jar
COPY ${JAR_FILE_PATH} eco.jar
ENTRYPOINT ["java", "-jar", "eco.jar"]