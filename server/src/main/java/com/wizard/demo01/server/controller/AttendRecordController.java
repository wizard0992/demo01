package com.wizard.demo01.server.controller;

import com.google.common.collect.Maps;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.AttendRecordEntity;
import com.wizard.demo01.server.common.PoiService;
import com.wizard.demo01.server.common.WebOperationService;
import com.wizard.demo01.server.service.AttendRecordService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wizard_0992
 * @date 2019/12/16 9:49
 */
@Controller
@RequestMapping("/attend/record")
public class AttendRecordController extends AbstractController {

    @Autowired
    private AttendRecordService attendRecordService;

    @Autowired
    private PoiService poiService;

    @Autowired
    private WebOperationService webOperationService;


    //列表
    @ResponseBody
    @RequestMapping("/list")
    public BaseResponse list(@RequestParam Map<String, Object> params){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();

        try {
            log.info("---考勤列表---");

            PageUtil page = attendRecordService.queryPage(params);
            resMap.put("page", page);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }


    //导出
    @RequestMapping("/export")
    public @ResponseBody
    String export(@RequestParam Map<String, Object> params, HttpServletResponse response){
        final String[] headers=new String[]{"ID","部门名称","姓名","日期","打卡状态","打卡开始时间","打卡结束时间","工时/小时"};
        try {
            String fileName=new StringBuilder("考勤明细-").append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).toString();
            String excelName=fileName+".xls";

            //针对于具体的业务模块-查询相应的数据，并做 “行转列映射” 的处理
            List<AttendRecordEntity> list=attendRecordService.selectAll(params);
            List<Map<Integer, Object>> listMap=attendRecordService.manageExport(list);

            //以下是通用的
            Workbook wb=poiService.fillExcelSheetData(listMap,headers,fileName);
            webOperationService.downloadExcel(response,wb,excelName);

            return excelName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
