# jitwatch_Concepts

#注意区分JDK 《=8 与JDk 》=9

#公共部分：
1. 构建hsdis 
2. 
   预先构建好的适用于 openjdk 17的 https://chriswhocodes.com/hsdis/ 

   [windows]（https://dropzone.nfshost.com/hsdis/）

   [linux]  https://www.chrisnewland.com/building-hsdis-on-linux-amd64-on-debian-369

2. 将 hsdis.dll 放进 openJDk/bin 目录

    注意CPU架构，下载正确的文件
     linux hsdis.so
     OSX  hsdis.dylib

3. 使用该JDK启动 jitwatch 注意配置hsdis成功了，默认jitwatch会显示已经加载hsdis，如果没有会提示。

    jitwatch下载地址：https://github.com/AdoptOpenJDK/jitwatch

4. jitwatch可以配置语言，

## 使用sandbox运行简单代码时候：这种不区分JDK8 or after JDK 8

1.直接使用Jitwatch的sandbox， 运行Java简单代码，

2.配置Jotwatch沙盒的注意，如果你需要使用其他jar，你需要在沙河的环境变量里面添加文件

3.配置ok ，直接运行简单Java 自动弹出相应的窗口，也可以手动查看输出



## 使用ide运行代码时候：这种区分JDK8 or after JDK 8
但是注意这种情况下不区分，手动用java -jar  or 直接使用ide run ：只是配置的地方不同，配置的命令一致。

先说 after java 8 的

实际主要是配置jvm.vmoptions
    验证vmoptions是否按版本合格可以到这个网站 https://jacoline.dev/inspect
    or https://perfma.com/   https://heapdump.cn/  有相关的工具可以校验

关键命令：
        -XX:+UnlockDiagnosticVMOptions    #
         -XX:+PrintAssembly               #打印编译指令  注意这个
         -XX:+LogCompilation   /    #运行后这个会生成一个hotspot_pid*.log  * 是数字   后面jitwatch载入的log就是这个日志，而不是打印的其他log 
         -XX:+DebugNonSafepoints

1. ide运行代码，需要先运行一次，之后去配置运行的vm环境变量  eclipse  ideaJ 类似
   
实例 vm配置参数  

     -Xmx128m -XX:-TieredCompilation  -XX:+UnlockDiagnosticVMOptions  -XX:+PrintAssembly -XX:+LogCompilation -XX:+DebugNonSafepoints -Xlog:all:file=Summary1.log
2. java 命令行运行的方式，需要手动设置 jdk的 vmoptions，相类似参数配置进去


再说 JDk8 ，小于JDK8的请搜索oracle官网有相关blog

1.ide运行：  
* eclipse 直接有插件  jitclipse  https://jitclipse.io/
* idea 老版本的有插件 https://github.com/yole/jitwatch-intellij 

2.不使用插件的情况下：  也是配置运行的vm参数：
























#Thanks

https://github.com/LJWLgl/CommonUtil
https://github.com/AdoptOpenJDK/jitwatch

ide插件，目前仅适用于 jdk8
https://jitclipse.io/


#附注 

如何在eclipse里面运行jmh的example ；ideaj直接可以ru

当前测试环境是基于eclipse环境下进行的，以下的环境依赖是必需的：

JDK
eclipse
Maven
eclipse的m2e-apt插件
maven project相关设置
如果已经有安装好的环境，请跳过对应的步骤。



1.JDK

JDK版本的取决于测试代码运行的JVM，这里的JVM是指服务器上的JVM版本。什么样的版本就去下对应的版本就好。JDK安装后需要配置环境信息。

JDK下载地址：https://www.oracle.com/java/technologies/javase-downloads.html



2.eclipse

要根据JDK版本选择合适的eclipse版本。

eclipse下载地址：https://www.eclipse.org/downloads/packages/



3.Maven

Maven的版本是由JDK版本确定的，下载地址：http://maven.apache.org/download.cgi

Maven安装后需要配置环境信息。



4.eclipse的m2e-apt插件

这个插件需要启动eclipse后到eclipse->Help->Eclipse Marketplace中去搜索后安装，安装完成后重启eclipse即可。
eclipse < 2022-09 m2e-apt 1.5.4
m2e 2不需要



5.maven project相关设置

第一步：在POM文件中加入jmh的依赖信息：


   <dependencies>

   <dependency>

   <groupId>org.openjdk.jmh</groupId>

   <artifactId>jmh-core</artifactId>

   <version>${jmh.version}</version>

   </dependency>

   <dependency>

   <groupId>org.openjdk.jmh</groupId>

   <artifactId>jmh-generator-annprocess</artifactId>

   <version>${jmh.version}</version>

   <scope>provided</scope>

   </dependency>

   </dependencies>


配置依赖完成后，右键选择项目，选择Run As -> Maven install ，下载依赖包到项目中。下载完成后刷新项目，可以在Maven Dependencies下看到JMH的包。

第二步：右键选择project ，选择最下面的Properties，然后打开，选择Maven -> Enable project specific settings -> Automatically configure JDT AP ， 然后Apply and Close 。



全部配置完成后，JMH的环境准备工作就全部结束了。
