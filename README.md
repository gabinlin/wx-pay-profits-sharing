#项目： 微信电商收付通工具包
文档地址 [https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/guide.shtml](https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/guide.shtml)

##一、更新说明
* 1.0.1 草稿包
* 1.4.0 待接口调试包
* 1.5.0 基本流程，微信公众号发起支付-补差价-分账-余额查询&提现等接口调试通过

##二、Maven引用方式
```xml
 <dependency>
   <groupId>top.gabin</groupId>
   <artifactId>wx-pay-profits-sharing</artifactId>
   <version>1.5.4</version>
 </dependency>
```
##三、主程序入口
- 基本上通过ProfitsSharingService接口调用
- 配置化待完善

##四、官方工具
- [证书下载工具(命令行)](https://github.com/wechatpay-apiv3/CertificateDownloader) 用于下载证书
- [PostMan调试](https://github.com/wechatpay-apiv3/wechatpay-postman-script) 用于调试接口
- [Http-Client](https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient) 定制好的Http客户端，可以自动组装头信息(Java) 
- [Http-Client](https://github.com/wechatpay-apiv3/wechatpay-guzzle-middleware) 定制好的Http客户端，可以自动组装头信息(Php)

##五附录
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
