# 微信电商收付通工具包
官方文档地址： [https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/guide.shtml](https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/guide.shtml)

## 一、更新说明
| 版本号 | 更新内容 | 修改者 | 日期
|:-------:|:-------:|:-------:|:-------:|
| 1.5.0 | 基本流程涉及接口调试通过 | gabin | 2020-04-25
| 1.4.0 | 待接口调试包 | gabin | 2020-04-17
| 1.0.1 | 草稿包 | gabin | 2020-04-14

***
## 二、引用
- Apache Maven
```xml
 <dependency>
   <groupId>top.gabin</groupId>
   <artifactId>wx-pay-profits-sharing</artifactId>
   <version>1.5.6</version>
 </dependency>
```
- Gradle Groovy DSL
```
implementation 'top.gabin:wx-pay-profits-sharing:1.5.6'
```

- Gradle Kotlin DSL
```
implementation("top.gabin:wx-pay-profits-sharing:1.5.6")
```

- Scala SBT
```
libraryDependencies += "top.gabin" % "wx-pay-profits-sharing" % "1.5.6"
```

- [更多](https://search.maven.org/artifact/top.gabin/wx-pay-profits-sharing/1.5.6/jar)
***
## 三、主程序入口
- 基本上通过ProfitsSharingService接口调用
- 实现类ProfitsSharingServiceImpl
```
 // config包含了基本的参数配置，cacheService可用于实现获取的证书信息缓存
 // 通过重写这两个实现，可以自行定制集群或分布式的支持
 ProfitsSharingService service = new ProfitsSharingService(config, cacheService);
```
***
## 四、官方工具
- [证书下载工具(命令行)](https://github.com/wechatpay-apiv3/CertificateDownloader) 用于下载证书
- [PostMan调试](https://github.com/wechatpay-apiv3/wechatpay-postman-script) 用于调试接口
- Http-Client [java](https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient) [php](https://github.com/wechatpay-apiv3/wechatpay-guzzle-middleware) 定制好的Http客户端，可以自动组装头信息
***
## 五、附录
- postman中使用的forge.js [API文档](https://www.npmjs.com/package/node-forge#cipher)
- nodejs版 加解密 [示例（引用）](http://fangzhenqi.xin/art/nodejs/other/86.html)
- [签名官方示例代码](https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/qian-ming-sheng-cheng)
- [证书解密官方示例代码](https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/zheng-shu-he-hui-tiao-bao-wen-jie-mi)
- [敏感信息加解密官方示例代码](https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/min-gan-xin-xi-jia-mi)
- [证书常见问题](https://wechatpay-api.gitbook.io/wechatpay-api-v3/chang-jian-wen-ti/zheng-shu-xiang-guan)
- [签名常见问题](https://wechatpay-api.gitbook.io/wechatpay-api-v3/chang-jian-wen-ti/qian-ming-xiang-guan)
- 项目中分享了postman配置文件(参数要自己修改，和官方的相比，主要是处理了信息加密，仅涉及进件和修改结算信息；其他都是搬砖，如果时间很紧的可以直接拷，不然自己写可能印象更深)
<pre>
    wechatpay-apiv3.postman_collection_share.json
    wechapay-api3-upload-image.postman_collection_share.json
</pre>
注：需要创建环境才可以正常使用，因为用到了环境变量

```变量
```
