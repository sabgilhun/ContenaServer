FROM jenkins/jenkins:lts

USER root

COPY install_docker.sh /install_docker.sh
RUN chmod +x /install_docker.sh
RUN /install_docker.sh

RUN  curl -L "https://github.com/docker/compose/releases/download/1.26.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
RUN chmod +x /usr/local/bin/docker-compose

RUN usermod -aG docker jenkins
USER jenkins
