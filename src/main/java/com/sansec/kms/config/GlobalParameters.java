package com.sansec.kms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/5/16 18:50
 */
@Component
@ConfigurationProperties(prefix = "global-parameter")
@Data
public class GlobalParameters {
    String caHost;
    String caPort;
    String caCert;
    String raCert;
    String raKeyIndex;
}
