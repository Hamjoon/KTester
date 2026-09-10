FROM python:3.13-slim-bookworm

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends \
        openjdk-17-jdk-headless git unzip p7zip-full curl ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Maven 3.9.9 (the version used in the paper)
ARG MAVEN_VERSION=3.9.9
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=/opt/maven/bin:$PATH
# A login shell (bash -l) resets PATH from /etc/profile and would lose /opt/maven/bin,
# so also expose mvn on the default path.
RUN ln -s /opt/maven/bin/mvn /usr/local/bin/mvn

# JAVA_HOME must point at the JDK for JPype. The Debian package path differs by CPU
# architecture (java-17-openjdk-arm64 / -amd64), so link it to a fixed name.
RUN ln -s "$(dirname "$(dirname "$(readlink -f "$(which java)")")")" /usr/lib/jvm/java-17
ENV JAVA_HOME=/usr/lib/jvm/java-17

# The README asks for an English JVM locale (the code parses javac/JUnit output).
ENV _JAVA_OPTIONS="-Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8"
ENV PYTHONUNBUFFERED=1

WORKDIR /work
COPY code/requirements.txt /tmp/requirements.txt
RUN pip install --no-cache-dir -r /tmp/requirements.txt

CMD ["bash"]
