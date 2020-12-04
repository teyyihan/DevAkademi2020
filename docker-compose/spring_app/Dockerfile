FROM openjdk:14
COPY devakademi-1.jar /
COPY wait-for-it.sh /
ENTRYPOINT ["./wait-for-it.sh", "mongodb:27017","--timeout=60", "--", "java", "-jar","devakademi-1.jar"]