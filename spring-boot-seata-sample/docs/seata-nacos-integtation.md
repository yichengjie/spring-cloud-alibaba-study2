1. 修改/seata/conf/file.conf
    > 1.1 将mode从file 改为 db  
      1.2 修改db配置项中的url、user、password
2. 修改/seata/conf/registry.conf，注意nacos中的group为SEATA_GROUP,后面将本地配置上传到nacos会使用
    > 2.1 将registry下type从file改为nacos  
      2.2 修改registry下nacos的serverAddr  
      2.3 修改config下type从file改为nacos  
      2.4 修改config下nacos的serverAddr
3. 下载seata的源码并进入到目录/seata-1.3.0/script/config-center
4.  修改config.txt文件内容
    > 4.1 修改store.db.url、store.db.user、store.db.password  
      4.2 使用git bash 进入目录/seata-1.3.0/script/config-center/nacos  
      4.3 执行sh nacos-config.sh -h 192.168.221.128   （后面ip为nacos服务器地址）
5. 创建seata数据，并找到/seata-1.3.0/script/server/db/mysql.sql执行
6. 启动seata并指定本机IP，IP不指定可能会导致seata注册到nacos的服务地址有问题，导致应用从nacos地址获取seata事务失败
    ```text
    ./seata-server.sh -h 192.168.221.128
    ```
    
    