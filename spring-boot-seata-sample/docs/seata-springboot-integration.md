1. 添加依赖
    ```text
    <dependency>
        <groupId>io.seata</groupId>
        <artifactId>seata-spring-boot-starter</artifactId>
    </dependency>
    ```
2. 建表
    ```text
    CREATE TABLE undo_log(
        id               BIGINT(20)        NOT NULL       AUTO_INCREMENT   PRIMARY KEY,
        branch_id        BIGINT(20)        NOT NULL,
        xid              VARCHAR(100)       NOT NULL,
        context          VARCHAR(128)      NOT NULL,
        rollback_info    LONGBLOB          NOT NULL,
        log_status       INT(11)           NOT NULL,
        log_created      DATETIME          NOT NULL,
        log_modified     DATETIME          NOT NULL,
        UNIQUE KEY ux_undo_log(xid,branch_id)
    )  ENGINE = INNODB DEFAULT CHARSET = utf8;
    ```
3. 修改application.properties配置,从源码包中复制即可 
   > seata源码中/seata-1.3.0/script/client/spring/application.properties中有效内容复制粘贴
4. 配置主要关注项
    ```text
    seata.enabled=true
    seata.application-id=${spring.application.name}
    # 与服务端配置的保持一致即可，服务端为每个微服务添加对应的配置
    # 对应服务端配置: service.vgroupMapping.sample_account_service_tx_group=default
    seata.tx-service-group=sample_account_service_tx_group
    # 这里配置微服务对应的undo表名称
    seata.client.undo.log-table=undo_log
    # 注意这里与上面的seata.tx-service-group配置一定要对应上
    seata.service.vgroup-mapping.sample_account_service_tx_group=default
    seata.service.grouplist.default=192.168.221.128:8091
    # config与registry与服务端保持一致即可
    seata.config.type=nacos
    seata.config.nacos.namespace=
    seata.config.nacos.serverAddr=192.168.221.128:8848
    seata.config.nacos.group=SEATA_GROUP
    seata.registry.type=nacos
    seata.registry.nacos.application=seata-server
    seata.registry.nacos.server-addr=192.168.221.128:8848
    seata.registry.nacos.group=SEATA_GROUP
    seata.registry.nacos.namespace=
    ```
5. 在服务调用的最外层添加@GlobalTransactional注解
    ```text
    public class RestOrderServiceImpl implements IRestOrderService {
        @Reference(version = "1.0.1", group = "yicj")
        private IRepoService repoService;
        @Reference(version = "1.0.1", group = "yicj")
        private IOrderService orderService;
    
        @GlobalTransactional(timeoutMills = 30000, name = "sample-rest-web")
        @Override
        public ObjectResponse handleBusiness(OrderRequest orderRequest) throws Exception {
            log.info("开始全局事务: xid = {}", RootContext.getXID());
            log.info("begin order: "+orderRequest);
        }
    }
    ```
6. 在每微服务应用中可获取事务xid
    ```text
    String xid = RootContext.getXID() ;
    log.info("开始全局事务: xid = {}", xid);
    ```