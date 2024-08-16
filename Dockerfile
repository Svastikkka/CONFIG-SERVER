FROM jenkins/jenkins:2.426.2-jdk17
USER root
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends \
        ansible \
        sshpass && \
    rm -rf /var/lib/apt/lists/*
