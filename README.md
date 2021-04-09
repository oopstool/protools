# 工程简介
protools是一个简单易用的Java工具类库，通过静态方法封装，提供java开发中最常用的代码封装，
降低相关API的学习成本，提高工作效率。

# 延伸阅读
protools大部分的工具类基于guava开发，文件开发用到了easyExcel。

# 包含组件
protools一期阶段仅仅提供core包服务，远景规划包括有关地图的操作封装，大数据各种存储jdbc封装
，常用算法实现等。


| 模块                |     介绍                                                                          |
| -------------------|---------------------------------------------------------------------------------- |
| protools-core      |     核心，包括文件操作、日期、字符串各种Util等                                               |
| protools-map       |     地图相关操作（规划中）                     |
| protools-jdbc      |     jdbc相关操作 （规划中）                                                                    |
| protools-ml        |     各类算法实现 （规划中）                                              |


# 安装

## Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.github.oopstool</groupId>
    <artifactId>protools-core</artifactId>
    <version>1.0.3</version>
</dependency>
```
## PR
欢迎大家使用并提出意见和问题，联系邮箱houguangyu2015@163.com

