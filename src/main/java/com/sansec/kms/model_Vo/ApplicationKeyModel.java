package com.sansec.kms.model_Vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/8/21 16:14
 */
@Data
@ApiModel(value = "KeyModel",description = "返回给前端----应用密钥实体")
public class ApplicationKeyModel {
    String keyId;
    String name;
    String appId;
    int keyType;
    int createType;
    int keyApplicationType;
    String keyAlgorithm;
    int keySize;
    int keyVersion;
    String hashValue;
    String createTime;
    int LMK;
}
