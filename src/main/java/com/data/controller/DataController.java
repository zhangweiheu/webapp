package com.data.controller;

import com.data.annotation.NotNeedLogin;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@NotNeedLogin
@RequestMapping("")
public class DataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    //    @Autowired
    //    DataService dataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String file() {
        return "import";
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadfile(@RequestParam("tableName") String tableName, @RequestParam("outPath") String outPath, @RequestParam("rows") Integer rows, @RequestParam("columns") Integer columns, HttpServletResponse response) {
        //        dataService.downloadData(tableName, outPath, rows, columns);
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(outPath));
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + "tmp" + outPath.split("tmp")[1].toString());
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
