package com.sansec.kms.controller;

import com.sansec.kms.Result.Result;
import com.sansec.kms.model.CreateKeyModel;
import com.sansec.kms.model.SynthetizeKeyModel;
import com.sansec.kms.service.KeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/8/15 22:30
 */
@RestController
@Api(tags = "1、密钥模块", description = "密钥模块 Rest API")
@RequestMapping(value = "/key")
public class KeyController {
    @Autowired
    KeyService keyService;

    @ApiOperation("创建密钥")
    @PostMapping(value = "/createKey")
    public Result createKey(@RequestBody @Validated CreateKeyModel createKeyModel) {
        return keyService.createKey(createKeyModel);
    }

    @ApiOperation("合成密钥")
    @PostMapping(value = "/synthetizeKey")
    public Result synthetizeKey(@RequestBody @Validated SynthetizeKeyModel synthetizeKeyModel) {
        return keyService.synthetizeKey(synthetizeKeyModel);
    }

    @ApiOperation(value = "密钥激活")
    @PostMapping(value = "/activateKey")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "keyId", value = "密钥Id", required = true)})
    public Result activateKey(@RequestParam(name = "keyId") @NotBlank(message = "Key id cannot be null.") @Pattern(regexp = "^[a-zA-Z0-9_(.)-]{0,64}$", message = "Key id must be composed of letters,numbers and '_''-''.',and have a length of 1 to 64.") String keyId) {
        return keyService.activateKey(keyId);
    }

    @ApiOperation("密钥注销")
    @PostMapping(value = "/revokeKey")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "keyId", value = "密钥Id", required = true)})
    public Result revokeKey(@RequestParam(name = "keyId") @NotBlank(message = "Key id cannot be null.") @Pattern(regexp = "^[a-zA-Z0-9_(.)-]{0,64}$", message = "Key id must be composed of letters,numbers and '_''-''.',and have a length of 1 to 64.") String keyId) {
        return keyService.revokeKey(keyId);
    }

    @ApiOperation("密钥销毁")
    @PostMapping(value = "/destroyKey")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "keyId", value = "密钥Id", required = true)})
    public Result destroyKey(@RequestParam(name = "keyId") @NotBlank(message = "Key id cannot be null.") @Pattern(regexp = "^[a-zA-Z0-9_(.)-]{0,64}$", message = "Key id must be composed of letters,numbers and '_''-''.',and have a length of 1 to 64.") String keyId) {
        return keyService.destroyKey(keyId);
    }

    @ApiOperation("密钥删除")
    @PostMapping(value = "/deleteKey")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "keyId", value = "密钥Id", required = true)})
    public Result deleteKey(@RequestParam(name = "keyId") @NotBlank(message = "Key id cannot be null.") @Pattern(regexp = "^[a-zA-Z0-9_(.)-]{0,64}$", message = "Key id must be composed of letters,numbers and '_''-''.',and have a length of 1 to 64.") String keyId) {
        return keyService.deleteKey(keyId);
    }

    @ApiOperation("密钥更新")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "keyId", value = "密钥Id", required = true)})
    @PostMapping(value = "/updateKey")
    public Result updateKey(@RequestParam(name = "keyId") @NotBlank(message = "Key id cannot be null.") @Pattern(regexp = "^[a-zA-Z0-9_(.)-]{0,64}$", message = "Key id must be composed of letters,numbers and '_''-''.',and have a length of 1 to 64.") String keyId) {
        return keyService.updateKey(keyId);
    }

    @ApiOperation("密钥查看")
    @PostMapping(value = "/findKey")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "preId", value = "前置标识", example = "B12345678", required = false), @ApiImplicitParam(paramType = "query", dataType = "Long", name = "keyVersion", value = "密钥版本", example = "0", required = false), @ApiImplicitParam(paramType = "query", dataType = "String", name = "keyId", value = "密钥id", example = "Symmeeccc3e8-f48d-4f1e-9f77-cee508e4e6e5", required = false)})
    public Result findKey(@RequestParam(name = "preId", required = false) String preId, @RequestParam(name = "keyVersion", defaultValue = "0", required = false) int keyVersion, @RequestParam(name = "keyId", required = false) String keyId) {
        return keyService.findKey(preId, keyVersion, keyId);
    }

    @ApiOperation("密钥查询")
    @PostMapping(value = "/listKeys")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "preId", value = "前置标识", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "appId", value = "业务系统id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "keyApplicationType", value = "密钥类型",required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", example = "10",value = "每页显示多少条数据"),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", example = "1",value = "页数")})
    public Result listKeys(@RequestParam(name = "preId", required = false) String preId,
                           @RequestParam(name = "appId", required = false) String appId,
                           @RequestParam(name = "keyApplicationType", required = false) Integer keyApplicationType,
                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                           @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        return keyService.listKeys(preId, appId, keyApplicationType, pageSize, pageNum);
    }

    @ApiOperation("历史密钥查询")
    @PostMapping(value = "/listHistoryKeys")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "preId", value = "前置标识",example = "B12345678",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "每页显示多少条数据", example = "10",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页数", example = "1",required = true)})
    public Result listHistoryKeys(@RequestParam(name = "preId") @NotBlank(message = "PreId can not be null.") @Pattern(regexp = "PreId must be composed of letters，numbers and '_'，and have a length of 1 to 64") String preId,
                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        return keyService.listHistoryKeys(preId,pageSize,pageNum);
    }

}
