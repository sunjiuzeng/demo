package com.demo.upload;

import com.demo.enums.ResponseCode;
import com.demo.utils.UploadUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "upload")
public class UploadController {
    private static final Logger  logger= LoggerFactory.getLogger(UploadController.class);
    /**
     * @description 上传demo文件
     * @param uploadDemoFile
     * @param request
     * @param response
     */
    @RequestMapping("/importDemo")
    @ResponseBody
    public void importDemo(@RequestParam("uploadDemoFile") MultipartFile uploadDemoFile, HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入方法");
        logger.info("UploadController#fileUpload() start");
        //回传详细信息
        Map<String,Object> results = new HashMap<String,Object>();
        try{
            Map<String, String[]> params = request.getParameterMap();
            logger.info(params.toString());
            String curProjectPath = request.getSession().getServletContext().getRealPath("/");
            String saveDirectoryPath = curProjectPath + "/" + UploadUtils.uploadFolderName;
            File saveDirectory = new File(saveDirectoryPath);
            if(!saveDirectory.exists()) {
                saveDirectory.mkdir();
            }
            // 判断文件是否存在
            if (!uploadDemoFile.isEmpty()) {
                String fileName = uploadDemoFile.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(fileName);
                if(!ArrayUtils.contains(UploadUtils.extensionPermit, fileExtension)) {
                    logger.info("不支持此格式文件上传！");
                    UploadUtils.responseMessage(response, ResponseCode._048,results);
                }else{
                    uploadDemoFile.transferTo(new File(saveDirectory, fileName));
                    UploadUtils.responseMessage(response, ResponseCode._045,results);
                }
            }
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            UploadUtils.responseMessage(response, ResponseCode._046,results);
        }
        logger.info("UploadController#fileUpload() end");

    }
}
