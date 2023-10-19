package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.OrganizeAiDataVo;
import org.springframework.web.multipart.MultipartFile;

/**
* @author Lenovo
* @description 针对表【chart(图表信息表)】的数据库操作Service
* @createDate 2023-10-07 16:45:36
*/
public interface ChartService extends IService<Chart> {

    void handleChartUpdateError(long chartId, String execMessage);

    String BiUserInput(Chart chart);
    OrganizeAiDataVo getOrganizeAiData(String result,Long chartId);
}
