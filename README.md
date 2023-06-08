# fishexam
# 实战：在Docker上部署Springboot项目（附源码）

## 1、docker的基本使用

1、为什么使用docker

2、docker的介绍

3、docker安装

> https://www.codezhou.top/article/docker%E4%BD%BF%E7%94%A8

## 2、dockers安装mysql

### 拉取 Mysql 5.7.31 镜像

```shell
docker pull mysql:5.7.31
```

### 运行 Mysql 5.7.31

```shell
docker run -d --name myMysql -p 9506:3306 -v /data/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7.31
```

参数解析：

> -d: 后台运行容器，并返回容器 ID
> –name myMysql: 为容器指定一个名称
> -p: 指定端口映射，格式为：主机(宿主)端口:容器端口
> -v: 绑定一个卷，容器的 /var/lib/mysql 映射到 主机的目录 /data/mysql
> -e MYSQL_ROOT_PASSWORD=123456: 设置环境变量，密码设置为 123456
> mysql:5.7.31：使用镜像 mysql:5.7.31

## 3、拉取并打包项目

springboot的项目地址

```
https://github.com/cativen/fishexam.git
```

打包项目

1、maven clean项目

![image-20230608213005282](https://gulimallcativen.oss-cn-shenzhen.aliyuncs.com/img/image-20230608213005282.png)

2、maven package项目

![image-20230608213035935](https://gulimallcativen.oss-cn-shenzhen.aliyuncs.com/img/image-20230608213035935.png)

打包成功之后生成jar文件（在target目录下）

![image-20230608213125538](https://gulimallcativen.oss-cn-shenzhen.aliyuncs.com/img/image-20230608213125538.png)

3、上传jar文件到linux服务器上

```shell
cd home			#进入home文件夹下面
```

```
mkdir fishexam		#创建文件夹fishexam 
```

![image-20230608213218437](https://gulimallcativen.oss-cn-shenzhen.aliyuncs.com/img/image-20230608213218437.png)

```
mv fishexam-0.0.1-SNAPSHOT.jar  fishexam.jar		#重命名jar包
```

![image-20230608214739100](https://gulimallcativen.oss-cn-shenzhen.aliyuncs.com/img/image-20230608214739100.png)

4、制作Dockerfile文件

在 jar 包的同一级文件夹下新建 Dockerfile 文件，文件内容如下

```shell
FROM  openjdk:8
VOLUME /home/fishexam
ADD fishexam.jar fishexam.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/fishexam.jar"]
```

参数解释

> from openjdk:8 拉取一个 jdk 为 1.8 的依赖环境
> fishexam.jar 就是你上传的 jar 包，替换为 jar 包的名称
> fishexam.jar 是你将该 jar 包重新命名为什么名称，在容器中运行
> expose 该容器暴露的端口是多少，就是 jar 在容器中以多少端口运行
> entrypoint 容器启动之后执行的命令，java -jar /fishexam.jar 即启动 jar

5、打包镜像

```shell
docker build -t fishexam .
```

6、运行容器

```shell
docker run -d -p 8888:8888 --name fishexam-8888 fishexam
```

## 最终效果

![image-20230608215254540](https://gulimallcativen.oss-cn-shenzhen.aliyuncs.com/img/image-20230608215254540.png)
