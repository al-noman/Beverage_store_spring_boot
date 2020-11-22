FROM adoptopenjdk/openjdk11
MAINTAINER alnoman.cse@gmail.com
EXPOSE 8080
ADD target/*.jar BeverageStoreBoot.jar
ENTRYPOINT ["java","-jar","/BeverageStoreBoot.jar"]
