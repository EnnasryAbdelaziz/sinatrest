#FROM tomcat:8.5.81-jre8-openjdk-slim-bullseye
#FROM tomcat:6.0.53
FROM docker.io/tomcat:7.0.99
COPY ./target/SINISTREAT.war /usr/local/tomcat/webapps/
COPY ./xml-apis-1.4.01.jar /usr/local/tomcat/lib/
COPY ./ojdbc6-1.1.jar /usr/local/tomcat/lib/
COPY ./context.xml /usr/local/tomcat/conf/
#RUN apk add ttf-dejavu
#RUN apk add --no-cache msttcorefonts-installer fontconfig
#RUN update-ms-fonts
EXPOSE 8080
CMD ["catalina.sh", "run"]
