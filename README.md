#项目： 微信电商收付通工具包

##一、更新说明
* 1.0.1 草稿包

##二、Maven引用方式
```xml
 <dependency>
   <groupId>top.gabin</groupId>
   <artifactId>wx-pay-profits-sharing</artifactId>
   <version>1.0.1</version>
 </dependency>
```
##三、主程序入口
- 基本上通过ProfitsSharingService接口调用
- 配置化待完善

##四、官方工具
- [证书下载工具(命令行)](https://github.com/wechatpay-apiv3/CertificateDownloader) 用于下载证书
- [PostMan调试](https://github.com/wechatpay-apiv3/wechatpay-postman-script) 用于调试接口
- [Http-Client](https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient) 定制好的Http客户端，可以自动组装头信息