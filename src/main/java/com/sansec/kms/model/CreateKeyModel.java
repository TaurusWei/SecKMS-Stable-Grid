package com.sansec.kms.model;

import com.sansec.kms.valid.ValidSupport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/8/18 20:34
 */
@Data
@ApiModel(value = "CreateKeyModel",description = "创建密钥实体")
public class CreateKeyModel {
    @Range(min=1, max=2,message = "Create type is illegal: 1 is created automatically, 2 is synthetic.")
    @ApiModelProperty(value = "创建类型",  name = "createType", example = "2", hidden = true)
    int createType=1;
    @NotBlank(message="Key name can not be null.")
    @ApiModelProperty(value = "密钥名称",  name = "keyName", example = "testKeyName", hidden = false)
    String name;
    @NotBlank(message="App id can not be null.")
    @Pattern(regexp = "^[a-zA-Z]([a-zA-Z0-9\\s*\\._-]{3,63})$",message = "App id need to begin with letters,composed of letters,numbers, '_' , '-' , '.' , and have a length of 4 to 64.")
    @ApiModelProperty(value = "业务系统Id",  name = "userName", example = "testAppId", hidden = false)
    String appId;
    @Range(min=1, max=2,message = "Key type is illegal: 1 is asymmetric key, 2 is symmetric key.")
    @ApiModelProperty(value = "密钥类型",  name = "keyType", example = "2", hidden = false)
    int keyType;
    @ApiModelProperty(value = "应用类型",  name = "keyType", example = "2", hidden = true)
    int keyApplicationType = 2;
    @NotBlank(message="Key algorithm can not be null.")
    @ValidSupport( supportAlg = {"SM1","SM2","SM4","SM9"},message = "Key algorithm is illegal, only SM1,SM2,SM4 and SM9 are supported.")
    @ApiModelProperty(value = "密钥算法",  name = "keyAlgorithm", example = "SM1", hidden = false)
    String keyAlgorithm;
    @ApiModelProperty(value = "密钥长度",  name = "keySize", example = "128", hidden = false)
    int keySize;
    @Range
    @ApiModelProperty(value = "lmk",  name = "lmk", example = "1", hidden = false)
    int lmk;
}
