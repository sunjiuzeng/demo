package com.demo.utils;

import com.demo.enums.ResponseCode;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

public class UploadUtils {
	/** 日志对象*/
	private static final Logger logger= LoggerFactory.getLogger(UploadUtils.class);
	/** 上传目录名*/
	public static final String uploadFolderName = "uploadFiles";

	/** 上传临时文件存储目录*/
	public static final String tempFolderName = "tempFiles";

	/** 上传文件最大为30M*/ 
	public static final Long fileMaxSize = 30000000L; 

	/** 允许上传的扩展名*/
	public static final String [] extensionPermit = {"txt", "xls", "zip","xlsx"};

	/** 统一的编码格式*/
	public static final String encode = "UTF-8";

    /**
     * 返回结果函数
	 * @param response
     * @param responseCode
     * @param results
	 */
	public static void responseMessage(HttpServletResponse response, ResponseCode responseCode, Map<String,Object> results) {
		response.setCharacterEncoding(encode);
		response.setContentType("application/json; charset=" + encode);
		Writer writer = null;
		try {
			writer = response.getWriter();
			Gson gson=new Gson();
			results.put("code", responseCode.getCode());
			results.put("message", responseCode.getMsg());
			writer.write(gson.toJson(results));
			writer.flush();
			writer.close();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
}
