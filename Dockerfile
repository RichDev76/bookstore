FROM amazoncorretto:17 as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:17
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target
COPY --from=build ${DEPENDENCY}/bookstore-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]