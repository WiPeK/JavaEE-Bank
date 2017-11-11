FROM java:8-jdk


ENV         JAVA_HOME         /usr/lib/jvm/java-8-openjdk-amd64
ENV         GLASSFISH_HOME    /usr/local/glassfish4
ENV         PATH              $PATH:$JAVA_HOME/bin:$GLASSFISH_HOME/bin
ENV         DOMAIN_NAME       domain1
ENV         ADMIN_USER        admin
ENV         ADMIN_PASSWORD    admin

RUN         apt-get update && \
            apt-get install -y curl unzip zip inotify-tools && \
            rm -rf /var/lib/apt/lists/*

# disabled in development - run only just one
#RUN         curl -L -o /tmp/glassfish-4.1.zip http://download.java.net/glassfish/4.1/release/glassfish-4.1.zip && \
#            unzip /tmp/glassfish-4.1.zip -d /usr/local && \
#            rm -f /tmp/glassfish-4.1.zip

#RUN         /target/app.war /usr/local/glassfish4/glassfish/domains/domain1/autodeploy/app.war

#COPY domain.xml glassfish/domains/domain1/config/domain.xml
#COPY admin-keyfile glassfish/domains/domain1/config/admin-keyfile

#COPY api/target/app-api.war glassfish/domains/domain1/autodeploy/app-api.war
#COPY start.sh /

#ENTRYPOINT asadmin start-domain --verbose ${DOMAIN_NAME}
#ENTRYPOINT ["/start.sh"]

EXPOSE 8080 4848 8181


WORKDIR     /usr/local/glassfish4


# verbose causes the process to remain in the foreground so that docker can track it
#CMD         asadmin start-domain --verbose
#CMD ["asadmin", "start-domain", "-v"]