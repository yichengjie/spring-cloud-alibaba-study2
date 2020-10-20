1. 安装mysql
    ```text
    sudo apt-get install mysql-server
    ```
2. 判断Mysql是否启动
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
6. 系统登录为root账号时，登录mysql并执行授权与配置（必须使用root)
    ```text
    a. mysql -u root -p   （密码为root系统密码）
    b. alter user 'root'@'localhost' identified by '数据库密码' password expire never;
    c. alter user 'root'@'localhost' identified with mysql_native_password by 'password';
    d. flush privileges;
    e. use mysql ;
    f. update mysql.user set plugin='mysql_native_password', host='%' where user='root';
    g. select Host,user,authentication_string,plugin from user;
    ```
7. 重启mysql
    ```text
    sudo service mysql restart
    ```
8. 检查防火墙配置
    ```text
    sudo ufw status
    若显示Status: inactive说明防火墙开启，
    此时可利用sudo ufw disable关闭防火墙
    或则sudo ufw allow 3306
    ```
