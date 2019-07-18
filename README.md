<p align="center">
     <img  src="https://github.com/nekolr/fish/blob/master/media/logo.png">
     <br/>
     <br/>
     <a href="https://codeclimate.com/github/nekolr/fish/maintainability"><img src="https://api.codeclimate.com/v1/badges/fb6c707575e9adf15484/maintainability" /></a>
     <a href="https://github.com/nekolr/fish"><img src="https://img.shields.io/github/stars/nekolr/fish.svg?style=flat&label=Stars" /></a>
     <a href="https://github.com/nekolr/fish"><img src="https://img.shields.io/github/watchers/nekolr/fish.svg?style=flat&label=Watch" /></a>
     <a href="https://github.com/nekolr/fish"><img src="https://img.shields.io/github/forks/nekolr/fish.svg?style=flat&label=Fork" /></a>
     <a href="https://github.com/nekolr/fish/releases"><img src="https://img.shields.io/github/downloads/nekolr/fish/total.svg?style=flat" /></a>
     <a href="https://travis-ci.com/nekolr/fish"><img src="https://img.shields.io/travis/com/nekolr/fish.svg?style=flat" /></a>
</p>

## 简介
Fish 是一个使用 Spring Boot + Spring Security + JWT 开发的基于 RBAC 权限控制的快速启动器。

## 前端
前端项目为 [d2-admin-fish](https://github.com/nekolr/d2-admin-fish)

![home](https://github.com/nekolr/fish/blob/master/media/home.png)
![user](https://github.com/nekolr/fish/blob/master/media/user.png)
![role](https://github.com/nekolr/fish/blob/master/media/role.png)
![permission](https://github.com/nekolr/fish/blob/master/media/permission.png)
![menu](https://github.com/nekolr/fish/blob/master/media/menu.png)

## 使用
> 缓存使用了 Redis，因此需要先启动一个 Redis Server 服务，然后再启动主程序。

- 打包

```
$ mvn clean package
```

- 启动

```
$ cd target && java -jar fish-0.0.1-SNAPSHOT.jar
```

## Docker
- 构建镜像

需要注意的是，构建使用了多阶段构建。在第一个阶段使用了非官方的 [Maven Image](https://github.com/nekolr/maven-image)（它是在官方镜像的基础上修改了 Maven 的配置文件，加入了阿里云 Mirror 来加速构建过程）编译项目，因此需要先在服务器上构建该 Maven 镜像。
```bash
$ docker build --build-arg VERSION=0.0.1.RELEASE --no-cache -t nekolr/fish:0.0.1 .
```

- 运行容器
```bash
$ docker run --env VERSION=0.0.1.RELEASE
    --name fish -dit \
    --network=host \
    nekolr/fish:0.0.1
```