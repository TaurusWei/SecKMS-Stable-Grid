//package com.sansec.kms.controller;//package com.sansec.kms.controller;
//
//import com.sansec.kms.Result.Result;
//import com.sansec.kms.model.BusinessUserModel;
//import io.swagger.annotations.*;
//import org.slf4j.MDC;
//import org.springframework.http.MediaType;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//
///**
// * @author wangtao
// * @version V1.0
// * @Description: 业务人员控制
// * @preserve private
// * @ClassName: BusinessUserController.java
// * @Date 2017年1月16日 下午5:47:13
// */
//@RestController
//@Api(tags = "1、业务用户模块", description = "业务用户模块 Rest API")
//@RequestMapping(value = "/business/user",produces =MediaType.APPLICATION_JSON_VALUE )
//public class BusinessUserController {
//
//    @ApiOperation("创建业务用户/密钥属主")
//    @PostMapping(value = "/createBusinessUser")
//    public Result createBusinessUser(@RequestBody @Validated  @ApiParam(value="业务用户实体",required=true) BusinessUserModel businessUserModel) {
//        return Result.success(null);
////        return service.createBusinessUser(businessUserModel);
//    }
//    @ApiOperation(value = "编辑业务用户/密钥属主")
//    @PostMapping(value = "/editBusinessUser")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "业务系统id"),
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userName", value = "业务系统名称", required = false),
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "pwd", value = "密码", required = false)
//    })
//    public Result editBusinessUser(@RequestParam(name = "userId")@NotBlank(message = "User id cannot be blank")@Pattern(regexp = "^[a-zA-Z]([a-zA-Z0-9\\s*\\._-]{3,63})$",message = "User name need to begin with letters,composed of letters,numbers, '_' , '-' , '.' , and have a length of 4 to 64") String userId,
//                                   @RequestParam(name = "pwd",required = false) String pwd,
//                                   @RequestParam(name = "userName",required = false)String userName) {
////        return service.editBusinessUser(userName,pwd);
//        return Result.success(null);
//    }
//    @ApiOperation("删除业务用户/密钥属主")
//    @PostMapping("/deleteBusinessUser")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "业务系统id", required = true)})
//    public Result deleteBusinessUser(@RequestParam(name = "userId") String userId) {
////        return service.deleteBusinessUserByName(usrName);
//        return Result.success("");
//    }
//}
