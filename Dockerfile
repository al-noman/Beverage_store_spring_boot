FROM adoptopenjdk/openjdk11
MAINTAINER alnoman.cse@gmail.com
VOLUME /home/noman/NOMAN/MSC_BAMBERG/SUMMER_2020/THESIS/MyDocker
EXPOSE 8080
ADD target/*.jar BeverageStoreBoot.jar
ENTRYPOINT ["java","-jar","/BeverageStoreBoot.jar"]
