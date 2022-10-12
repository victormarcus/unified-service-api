FROM openjdk:8
ENV APP_HOME=/usr/app/
ENV BASE_URL=unified-service.free.beeceptor.com
WORKDIR $APP_HOME

COPY target/*.jar unified-service-api.jar
EXPOSE 8080
CMD ["java", "-jar", "unified-service-api.jar"]