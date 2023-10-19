package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.constant.ChartConstant;
import com.yupi.springbootinit.model.entity.Chart;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.OrganizeAiDataVo;
import com.yupi.springbootinit.service.ChartService;
import com.yupi.springbootinit.mapper.ChartMapper;
import com.yupi.springbootinit.utils.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
* @author Lenovo
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
* @createDate 2023-10-07 16:45:36
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

    @Resource
    private ChartMapper chartMapper;

    @Override
    public void handleChartUpdateError(long chartId, String execMessage) {
        Chart updateChartResult = new Chart();
        updateChartResult.setId(chartId);
        updateChartResult.setChartStatus(ChartConstant.CHART_STATUS_FAILED);
        updateChartResult.setExecMessage(execMessage);
        int i = chartMapper.updateById(updateChartResult);
        if (i != 1) {
            log.error("更新失败状态失败" + chartId + execMessage);
        }
    }

    @Override
    public String BiUserInput(Chart chart) {
        String goal = chart.getGoal();
        String chartType = chart.getChartType();
        String csvData = chart.getChartData();

        //用户输入
        StringBuilder userInput = new StringBuilder();
        userInput.append("分析需求：").append("\n");

        // 拼接分析目标
        String userGoal = goal;
        if (StringUtils.isNotBlank(chartType)) {
            userGoal += "，请使用" + chartType;
        }
        userInput.append(userGoal).append("\n");
        userInput.append("原始数据：").append("\n");
        userInput.append(csvData).append("\n");
        return userInput.toString();
    }

    @Override
    public OrganizeAiDataVo getOrganizeAiData(String result,Long chartId) {
        String[] splits = result.split("【【【【【");
        if (splits.length < 3) {
            handleChartUpdateError(chartId, "更新图表成功状态失败");
        }
        String genChart = splits[1].trim();
        String genResult = splits[2].trim();
        OrganizeAiDataVo organizeAiDataVo = new OrganizeAiDataVo();
        organizeAiDataVo.setGenChart(genChart);
        organizeAiDataVo.setGenResult(genResult);
        return organizeAiDataVo;
    }
}




