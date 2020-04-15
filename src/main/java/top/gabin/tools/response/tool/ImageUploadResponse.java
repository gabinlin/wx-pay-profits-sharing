package top.gabin.tools.response.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 部分微信支付业务指定商户需要使用图片上传 API来上报图片信息，从而获得必传参数的值：图片MediaID 。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEMERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * SYSTEMERROR	文件系统错误，请稍后重试	文件系统异常，请使用相同参数稍后重新调用	
 * 400	PARAM_ERROR	图片文件名称不正确，请检查后重新提交	图片文件名称不正确，只支持jpg，jpeg，png，bmp，请使用正确图片文件重新调用
 * PARAM_ERROR	文件二进制内容不是图片，请检查后重新提交	上传文件二进制内容头部不正确，只支持jpg，jpeg，png，bmp，请使用正确图片文件重新调用	
 * PARAM_ERROR	图片sha256值有误，请检查后重新提交	图片sha256值计算有误，请检查算法，重新计算后提交	
 * PARAM_ERROR	文件大小不能超过2M，请检查后重新提交	商户更换文件或者对图片进行压缩后，重新调用	
 * PARAM_ERROR	文件为空，请检查后重新提交	商户更换文件后，重新调用	
 * 429	FREQUENCY_LIMIT_EXCEED	操作过快，请稍后重试	请商户降低每秒调用频率
 * FREQUENCY_LIMIT_EXCEED	当天上传文件数已达上限	请商户降低每天调用频率	
 * 403	NO_AUTH	商户权限异常	请确认是否已经开通相关权限
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUploadResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：媒体文件标识 Id
	 * 变量名：media_id
	 * 是否必填：是
	 * 类型：string(512)
	 * 描述：
	 *  微信返回的媒体文件标识Id。
	 *  示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0 
	 * </pre>
	 */
	@JsonProperty(value = "media_id")
	private String mediaId;

	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}