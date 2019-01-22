FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=newsdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/newsdb
ADD ./target/NewsAppServer-0.0.1-SNAPSHOT.jar /usr/src/NewsAppServer-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "NewsAppServer-0.0.1-SNAPSHOT.jar"]
