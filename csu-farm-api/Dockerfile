FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER opgames(opgames.cn@gmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/greenfarm

WORKDIR /opt/projects/greenfarm

EXPOSE 8086

ADD ./csu-farm-api/target/csu-farm-api-0.0.1-SNAPSHOT.jar ./

CMD java -jar -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=docker csu-farm-api-0.0.1-SNAPSHOT.jar