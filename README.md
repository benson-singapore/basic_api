# 基础项目构建模块

#### 项目介绍

基础项目构建模块是基于工作中管理系统模块，抽取出来的基础构建项目。包含：用户管理、角色管理、菜单管理、字典管理、以及用户角色菜单授权等基础功能，后续会更新更多的基础功能。

项目模块构建基于最近工作中的总结，优化了功能结构。使用了前后端分离，前端主要应该阿里开源框架，Ant Design。后端为java开发，项目架构包含了，springboot、spring-security、spring-oauth2、jwt、mybatis-plus、redis、swagger、集成了oauth2 密码登录授权，以及swagger api接口文档管理。


#### 部署与启动
- 启动：执行Main方法启动项目
- 地址：
    swagger-ui: http://localhost:8010/swagger-ui.html。
- 前端项目部署：
    下载源码：[basic_web](https://gitlab.com/basecode-group/basic-project/basic_web)。
    编译与运行：yarn ， yarn start
    访问：http://localhost:8000。

