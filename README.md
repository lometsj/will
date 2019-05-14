# java mysql web

spring boot 

src tree:
```$xslt
src/main/
├── java
│   └── com
│       └── lome
│           └── will
│               └── demo
│                   ├── Controller
│                   │   ├── apiController.java   #some api for ajax call
│                   │   └── webController.java   #web router
│                   ├── DemoApplication.java
│                   ├── Entity
│                   │   ├── User.java
│                   │   └── Will.java
│                   ├── Repo
│                   │   ├── UserRepo.java
│                   │   └── WillRepo.java
│                   └── Service
│                       ├── UserService.java
│                       └── WillService.java
└── resources
    ├── application.properties
    ├── static
    │   ├── css
    │   │   └── styles.css
    │   ├── img
    │   │   ├── bg.jpg
    │   │   └── down.png
    │   ├── sign.html
    │   ├── willCreate.html
    │   └── willView.html
    └── templates
```

controller 主要是路径相关的，apiController主要响应了ajax调用，webController主要用于返回静态页面
Entity是实体类，对应sql的表结构数据
Repo中继承了JpaRepository实现了一些基本的sql增删改查
Service主要是一些关于业务的函数，通过注入的Repo对数据进行修改

html都在static目录下，引入jquery来写ajax调用
保存的pdf默认保存在`/tmp`目录下面，如果是windows运行需要改下这个目录,分别在WillService和webController 下的UPLOAD_FOLDER变量。

写的比较仓促