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
        xid              VARCHAR(20)       NOT NULL,
        context          VARCHAR(128)      NOT NULL,
        rollback_info    LONGBLOB          NOT NULL,
        log_status       INT(11)           NOT NULL,
        log_created      DATETIME          NOT NULL,
        log_modified     DATETIME          NOT NULL,
        UNIQUE KEY ux_undo_log(xid,branch_id)
    )  ENGINE = INNODB DEFAULT CHARSET = utf8;
    ```