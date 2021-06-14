#Alpine Linux with OpenJDK 8 JRE
FROM azul/zulu-openjdk-alpine:8

#Copy package .jar and rename to echo.war
COPY product-ms-0.0.1-SNAPSHOT.jar /echo.war

#Execute application
CMD ["/usr/bin/java", "-jar", "/echo.war"]