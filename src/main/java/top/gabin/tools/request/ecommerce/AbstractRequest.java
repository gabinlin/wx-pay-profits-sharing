package top.gabin.tools.request.ecommerce;

import top.gabin.tools.request.GetPath;

public abstract class AbstractRequest implements GetPath {
    protected String domain = "https://api.mch.weixin.qq.com/v3/";
    public String getUrl() {
        return domain + getPath();
    }
}
