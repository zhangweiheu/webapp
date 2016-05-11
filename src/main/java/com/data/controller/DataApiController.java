package com.data.controller;

import com.data.annotation.NotNeedLogin;
import com.data.bean.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;

@RestController
@NotNeedLogin
@RequestMapping("/api")
public class DataApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataApiController.class);


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String file() {
        return "import";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResponse savefile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String outpath = "";
        JsonResponse jsonResponse = JsonResponse.success();
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("") + file.getOriginalFilename();
                String outfilePath = request.getSession().getServletContext().getRealPath("") + "tmp" + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                HashMap<String, Object> hashMap = null;
                //                dataService.saveData(new File(filePath));
                jsonResponse.put("tableName", hashMap.get("tableName"));
                jsonResponse.put("rows", hashMap.get("rows"));
                jsonResponse.put("columns", hashMap.get("columns"));
                jsonResponse.put("totalTime", hashMap.get("totalTime"));
                jsonResponse.put("IMPBefore", hashMap.get("IMPBefore"));
                jsonResponse.put("IMPAfter", hashMap.get("IMPAfter"));
                jsonResponse.put("outPath", outfilePath);
                return jsonResponse;
            } catch (Exception e) {
                return JsonResponse.failed(e.getMessage());
            }
        }
        return JsonResponse.failed();
    }
}
