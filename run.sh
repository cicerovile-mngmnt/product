# -XX:+UseG1GC \

java \
    -Xloggc:gc.log \
    -XX:+UseGCLogFileRotation \
    -XX:NumberOfGCLogFiles=10 \
    -XX:+PrintGCDetails \
    -XX:+PrintGCDateStamps \
    -XX:+PrintGCCause \
    -XX:+PrintTenuringDistribution \
    -XX:+UseGCLogFileRotation \
    -XX:NumberOfGCLogFiles=3 \
    -XX:GCLogFileSize=5M \
    -XX:-UseAdaptiveSizePolicy \
    -Xms1024m -Xmx1024m \
    -jar \
    -Djava.rmi.server.hostname=192.168.1.74 \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Dcom.sun.management.jmxremote=true \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.port=7091 \
    -Dcom.sun.management.jmxremote.authenticate=false \
    fuck-off-as-a-service-0.0.1-SNAPSHOT.jar \
    >> nohup
