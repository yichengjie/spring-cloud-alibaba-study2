#### 修改配置
1. 修改/Rocketmq/bin/runbroker.sh中JAVA_OPT，适当修改即可，原内存过大可能无法启动
    ```text
    JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx1g -Xmn512m"
    ```
2. 修改/Rocketmq/bin/runserver.sh中JAVA_OPT，同1中原因
    ```text
    JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
    ```
3. 修改配置/Rocketmq/conf/broker.conf，添加公网ip
    ```text
    brokerIP1=192.168.221.128
    ```
#### 启停服务
1. 启动Name-Server
    ```text
    nohup sh bin/mqnamesrv &
    ```
2. 启动Broker
    ```text
    nohup sh bin/mqbroker -n localhost:9876 -c conf/broker.conf autoCreateTopicEnable=true &
    ```
3. 停止RocketMQ
    ```text
    sh bin/mqshutdown broker
    sh bin/mqshutdown namesrv
    ```
#### 查看日志
1. 查看name server日志
    ```text
    tail -f ~/logs/rocketmqlogs/namesrv.log
    ```
2. 查看broker日志
    ```text
    tail -f ~/logs/rocketmqlogs/broker.log
    ```
#### 验证服务是否正常
1. 检查生产者正常
    ```text
    export NAMESRV_ADDR=localhost:9876
    sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
    ```
2. 检查消费者正常
    ```text
    sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
    ```

