#FROM tomcat:8.5.81-jre8-openjdk-slim-bullseye
#FROM tomcat:6.0.53 test
FROM docker.io/tomcat:7.0.99
COPY ./SINISTREAT_Web/target/SINISTREAT.war /usr/local/tomcat/webapps/
COPY ./SINISTREAT_Web/xml-apis-1.4.01.jar /usr/local/tomcat/lib/
COPY ./SINISTREAT_Web/ojdbc6-1.1.jar /usr/local/tomcat/lib/
COPY ./SINISTREAT_Web/context.xml /usr/local/tomcat/conf/
#RUN apk add ttf-dejavu
#RUN apk add --no-cache msttcorefonts-installer fontconfig
#RUN update-ms-fonts
EXPOSE 8080
CMD ["catalina.sh", "run"]