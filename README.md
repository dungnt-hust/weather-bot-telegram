# PROJECT CORE SPRING
##Example env:application.yml
-------------------------------------------------------
```
server: 
  port: 9000 
  servlet:
    context-path: /apis
spring: 
  datasource: 
    driver-class-name: com.mysql.cj.jdbc.Driver 
    url: jdbc:mysql://host:3306/coreproject?useSSL=false&characterEncoding=utf8 
    password: password
    username: username
app: 
  auth: 
    tokenSecret: tokenscret
    tokenExpirationMsec: 864000000
logging:
  path: /var/log/coreproject
```
-------------------------------------------------------
##Dựng server để chạy lần đầu:
**B1:**  ```cd /etc/systemd/system```

**B2: Tạo file coreproject.service với nội dung**
```
[Unit]
Description=coreproject
After=syslog.target
After=network.target[Service]
User=root
Type=simple

[Service]
ExecStart=/usr/bin/java -jar /home/ductbm/deployment/coreproject-0.0.1-SNAPSHOT.jar
Restart=always
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=itwebapp

[Install]
WantedBy=multi-user.target
```
**B3: Copy file jar lên thư mục trên Server vào đường dẫn như ở file bước 2**
```
/home/ductbm/deployment/coreproject-0.0.1-SNAPSHOT.jar
```
**B4 Run serivce**
```
systemctl start coreproject
systemctl restart coreproject
systemctl stop coreproject
systemctl status coreproject
```
## Với những lần chạy tiếp theo chỉ cần làm lại Bước 3 Bước 4 bên trên
***Note: file log được cấu hình trong env VD /var/log/core-project

##Cài mới trên các server
*Yêu cầu*
```
Cài mysql
Cài nginx
Cài java8

```

	

