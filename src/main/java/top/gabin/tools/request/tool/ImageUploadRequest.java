package top.gabin.tools.request.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 部分微信支付业务指定商户需要使用图片上传 API来上报图片信息，从而获得必传参数的值：图片MediaID 。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class ImageUploadRequest {
	/**
	 * <pre>
	 * 字段名：图片文件
	 * 变量名：file
	 * 是否必填：是
	 * 类型：message
	 * 描述：body 将媒体图片进行二进制转换，得到的媒体图片二进制内容，在请求body中上传此二进制内容。媒体图片只支持JPG、BMP、PNG格式，文件大小不能超过2M。 
	 * </pre>
	 */
	@JsonProperty(value = "file")
	private String file;

	/**
	 * <pre>
	 * 字段名：+媒体文件元信息
	 * 变量名：meta
	 * 是否必填：是
	 * 类型：Object
	 * 描述：媒体文件元信息，使用json表示，包含两个对象：filename、sha256。 
	 * </pre>
	 */
	@JsonProperty(value = "meta")
	private Meta meta;

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class Meta {
		/**
		 * <pre>
		 * 字段名：文件名称
		 * 变量名：filename
		 * 是否必填：否
		 * 类型：string[1,128]
		 * 描述：商户上传的媒体图片的名称，商户自定义，必须以JPG、BMP、PNG为后缀。 
		 * </pre>
		 */
		@JsonProperty(value = "filename")
		private String filename;

		/**
		 * <pre>
		 * 字段名：文件摘要
		 * 变量名：sha256
		 * 是否必填：否
		 * 类型：string[1,64]
		 * 描述：图片文件的文件摘要，即对图片文件的二进制内容进行sha256计算得到的值。 
		 * </pre>
		 */
		@JsonProperty(value = "sha256")
		private String sha256;

	}

}