FROM maven:3.9-amazoncorretto-17-al2023 AS build
WORKDIR /build
COPY ../pom.xml .
COPY ../src ./src
RUN  mvn clean package -DskipTests


FROM  amazoncorretto:17.0.15-alpine3.21
WORKDIR /app
COPY --from=build /build/target/*.jar CrudJLAC.jar
EXPOSE 8080
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006", "-jar", "CrudJLAC.jar"]
