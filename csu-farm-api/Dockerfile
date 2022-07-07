FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER opgames(opgames.cn@gmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/greenfarm

WORKDIR /opt/projects/greenfarm

EXPOSE 8086

ADD ./target/csu-farm-api-0.0.1-SNAPSHOT.jar ./

CMD java -jar csu-farm-api-0.0.1-SNAPSHOT.jar
