1. 修改配置/Rocketmq/conf/broker.conf，添加公网ip
    ```text
    brokerIP1=192.168.221.128
    ```
2. 启动Name-Server
    ```text
    nohup sh bin/mqnamesrv &
    ```
3. 启动Broker
    ```text
    start Broker: nohup sh bin/mqbroker -n localhost:9876 -c conf/broker.conf autoCreateTopicEnable=true &
    ```
4. 停止RocketMQ
    ```text
    sh bin/mqshutdown broker
    sh bin/mqshutdown namesrv
    ```
5. 查看name server日志
    ```text
    tail -f ~/logs/rocketmqlogs/namesrv.log
    ```
6. 查看broker日志
    ```text
    tail -f ~/logs/rocketmqlogs/broker.log
    ```
7. 检查生产者正常
    ```text
    export NAMESRV_ADDR=localhost:9876
    sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
    ```
8. 检查消费者正常
    ```text
    sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
    ```

