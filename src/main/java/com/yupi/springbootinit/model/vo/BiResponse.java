package com.yupi.springbootinit.model.vo;
/*
 * Author: 咸余杨
 * */

import lombok.Data;

/**
 * BI 的返回结果
 */
@Data
public class BiResponse {

    private String genChart;

    private String genResult;

    private Long chartId;
}
