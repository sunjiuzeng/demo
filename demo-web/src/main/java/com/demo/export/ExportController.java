package com.demo.export;

import com.demo.enums.ResponseCode;
import com.demo.upload.UploadController;
import com.demo.utils.BuildExcelUtils;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "export")
public class ExportController {
    private static final Logger logger= LoggerFactory.getLogger(ExportController.class);
    @RequestMapping("/exportFileTemplate")
    public void exportLactationMilkDalyAvgProduceInfo(@RequestParam Map<String, Object> params, HttpServletResponse response){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            Object endDate = params.get("endDate");
            jsonMap.put("endDate", endDate);
            HSSFWorkbook wrokbook = new BuildExcelUtils().builExcel(jsonMap);
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + new Date().getTime() + ".xls");

            /** 用户去下载入群列表信息 **/
            wrokbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            jsonMap.clear();
            jsonMap.put("errorMessage", ResponseCode._050.getMsg());
            jsonMap.put("returnStatus", ResponseCode._050.getCode());
            Gson gson=new Gson();
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(gson.toJson(jsonMap));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
}
