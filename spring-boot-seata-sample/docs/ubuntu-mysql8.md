1. 安装mysql
    ```text
    sudo apt-get install mysql-server
    ```
2. 判断mysql是否启动
    ```text
    ps -ef|grep mysqld
    ```
3. 启动mysql
   ```text
   /etc/init.d/mysqld start
   ```
4. 检查进程监控端口
    ```text
    netstat -nlp|grep 3306
    ```
5. 若显示为127.0.0.1:3306，则修改bind-address参数
    ```text
    vi /etc/mysql/mysql.conf.d/mysqld.cnf
    或
    vi /etc/mysql/my.conf
    ```
6. 系统登录root账号，登录mysql
    ```text
    mysql -u root -p   （密码为root系统密码）
    ```
7. 修改数据库密码为指定密码
    ```text   
    alter user 'root'@'localhost' identified by '数据库密码' password expire never;
    alter user 'root'@'localhost' identified with mysql_native_password by '数据库密码';
    flush privileges;
    ```
8. 将root账号修改为允许远程连接
    ```text
    use mysql ;
    update mysql.user set plugin='mysql_native_password', host='%' where user='root';
    select host,user,authentication_string,plugin from user;
    注意%表示所有ip都能连接，可根据实际情况修改为只允许固定ip访问
    ```
9. 重启mysql
    ```text
    sudo service mysql restart
    ```
10. 检查防火墙配置,关闭防火墙，或开放3306端口
    ```text
    sudo ufw status
    若显示Status: inactive说明防火墙开启，
    此时可利用sudo ufw disable关闭防火墙
    或则sudo ufw allow 3306
    ```
