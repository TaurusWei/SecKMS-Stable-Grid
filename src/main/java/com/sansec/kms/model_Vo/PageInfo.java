package com.sansec.kms.model_Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/8/21 16:40
 */
@Data
@ApiModel(value = "PageInfo",description = "返回给前端----分页查询实体")
public class PageInfo {
    List list;
    int pages;
    int pageNum;
    int pageSize;
    int total;
}
