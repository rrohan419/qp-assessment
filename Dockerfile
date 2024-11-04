FROM eclipse-temurin:21-jre
WORKDIR /usr/app
COPY target/grocery-booking-0.0.1-SNAPSHOT.jar /usr/app/grocery-booking-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "grocery-booking-0.0.1-SNAPSHOT.jar"]
