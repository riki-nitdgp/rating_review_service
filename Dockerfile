FROM  adoptopenjdk:11-jre-hotspot



WORKDIR /opt/rating-review-service

#RUN mvnw install
COPY ./rating_review_service/target/*.jar application.jar


ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.12.0/opentelemetry-javaagent.jar /urs/local/opentelemetry-java/opentelemetry-javaagent-v1.12.0.jar

#COPY target/inventory-service-*.jar $BASE/
#


ENV JAVA_OPTS -javaagent:/urs/local/opentelemetry-java/opentelemetry-javaagent-v1.12.0.jar


EXPOSE 8002

CMD ["java", "-javaagent:/urs/local/opentelemetry-java/opentelemetry-javaagent-v1.12.0.jar", "-jar", "application.jar"]
