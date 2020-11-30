FROM tomcat:latest
ADD target/shopping.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]