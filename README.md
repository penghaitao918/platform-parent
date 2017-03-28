# platform-parent
## 针对手游的一套平台框架
大概目录介绍： 
  
admin 是平台的GM工具后台，主要给运营针对游戏配置一些活动，和给玩家发送邮件奖励等等。  
assembly maven打包相关，可以忽略。  
bin 平台服务器开关脚本。  
common 一些公共类，包括工具类，一些常量定义，基础类等。  
  
platform :  
platform-api 平台服务的接口设计以及相关功能实体类  
platform-activity/game/billing/user 具体服务功能的实现  
  
platform-web 对外接口，用于跟客户端打交道，提供给客户端功能。  
  
## 大致框架  
平台服务 SpringMVC + dubbo + zookeeper 

GM工具前端 AngularJS + Bootstrap  

mysql + redis  
  
项目还在完善中，会不断更新。。。
