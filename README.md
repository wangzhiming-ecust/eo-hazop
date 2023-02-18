# Springboot部署步骤
### **请确保部署springboot的jar包前已成功部署Ontop服务**

如成功部署ontop服务，则http://xxx.xxx.xxx.xxx:8080可访问该ontop服务，
其中xxx.xxx.xxx.xxx为ontop所部署服务器的ip地址。

#### 部署springboot步骤1
1 使用IDEA打开springboot目录下工程springboot工程kg

#### 部署springboot步骤2

更改rdf.nt文件地址，建议将其地址指向jar包所在的同一目录下。

2.1 打开kg/src/main/resources文件夹下的application.properties:
  更改spring.kg.saveExtractionFile = /root/test/rdf.nt为
    spring.kg.saveExtractionFile = /xxx/xxx/rdf.nt
    其中 /xxx/xxx/为linux本地目录  
    
2.2 打开com/rao/kg/dao/impl/QuerySparqlFromNtFile.java
  将18行的static final String filename = "/root/test/rdf.nt"; 
其中的文件路径更换为步骤2.1定义的路径

#### 部署springboot步骤3
打开kg/src/main/resources文件夹下的application.properties:

3.1 将其中的
spring.kg.endpoint = http://172.20.138.185:8080/sparql中
172.20.138.185更改为ontop服务器的ip地址。

3.2 server.port=8081为jar包服务端口，若8081端口已被使用，则将server.port=8081更换为server.port=xxxx
其中xxxx为空闲端口

3.3 使用IDEA的Maven重新打包springboot工程,生成jar部署到后端的服务器
部署命令为
nohup java -Dfile.encoding=utf-8 -jar kg-0.0.1-SNAPSHOT.jar >springbootlog.log 2>&1 &

