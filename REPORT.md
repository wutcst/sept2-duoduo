# 软件工程实训任务二报告：小组协同开发

## 任务目的
* 巩固强化软件编程规范
* 提高面向对象软件建模与抽象能力
* 培养小组协同开发能力
* 掌握基于Maven的软件项目管理机制
* 掌握基于Github的小组协同开发工具和平台
* 了解DevOps软件开发流程

## 任务内容
* 创建软件开发小组
* 针对样例代码工程进行小组讨论，确定功能扩充需求点
* 基于Github中的issue管理功能明确工作任务并为组员分配工作任务
* 基于小组商定的分支模型进行软件功能开发，并按开发流程进行代码测试、提交、归并和同步
* 代码提交到远程仓库后，应进行自动化代码格式规范检查和测试以确保功能符合需求设计
* 完成前述各项任务后，可尝试进行代码自动化打包，自动生成可供执行的jar文件

## 任务分工
* 在github平台创建任务issue并为所有组员分配任务
### 郝一鸣
* GUI界面的设计与实现
### 钱科均
* command类的改写与实现
### 雷霆杰
* player类以及方法的实现
### 张家齐
* 基础类和地图的设计

## 设计思路
* 基础类的设计：item类以及Game中room类
* Player类的设计：基于item以及room类的设计，吸收实现第一次实践内容中Game类中方法，实现go、back、take、give、tp等功能
* Commands类的设计：包括Commands包以及ZuulCommands包，沿袭了第一次实践通过commands的实现方法，在收到GUI层面生成的Command消息后，在本层进行command分析，并向下通过Player对象调用相应方法来实现对应功能
* GUI层的设计：界面的布置，actionlistener编写，调用CommandGUI中的方法将点击事件转换为自定义的command命令，并向下传输到command层对应的execute方法。

## 类图
![UML类图](https://github.com/wutcst/sept2-duoduo/blob/da1d4a50e125ec0c5e96b85581bb1f39cdb5ce77/UML%E7%B1%BB%E5%9B%BE.png)

## 类的说明
