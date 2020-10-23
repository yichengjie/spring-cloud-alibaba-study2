1. 运行tzselect
    ```text
    tzselect
    ```
2. 在这里我们选择亚洲（ Asia）[4]，确认之后选择中国（China)[9]，最后选择北京(Beijing)[1]
    >3.1 选择亚洲:    4  
    3.2 选择中国：    9  
    3.3 选择北京：    1  
    3.4 选择最后确认：1
3. 复制文件到/etc目录下 (root账号)
    ```text
    cp /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
    ```