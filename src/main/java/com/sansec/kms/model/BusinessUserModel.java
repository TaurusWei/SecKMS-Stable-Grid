package com.sansec.kms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "BusinessUserModel",description = "业务用户实体")
public class BusinessUserModel {
	@NotBlank(message="User id can not be null.")
	@ApiModelProperty(value = "业务系统id",name = "userId",example = "dahjkdhakjhdak",required = true,hidden = false)
	private String userId;

	@NotBlank(message="User name can not be null.")
	@Pattern(regexp = "^[a-zA-Z]([a-zA-Z0-9\\s*\\._-]{3,63})$",message = "User name need to begin with letters,composed of letters,numbers, '_' , '-' , '.' , and have a length of 4 to 64")
	@ApiModelProperty(value = "业务用户名称",  name = "userName", example = "testBUser", required = true, hidden = true)
	private String userName;

	@NotBlank(message="Password cannot be empty.")
	@ApiModelProperty(value = "业务用户密码",  name = "pwd", example = "swxa1234..", required = true, hidden = false)
	private String pwd;

	@ApiModelProperty(value = "业务系统名称",  name = "userName", example = "testBUser", required = true, hidden = false)
	private String remark;
	@ApiModelProperty(value = "adminPermission",hidden = true)
	private String adminPermission;
	@ApiModelProperty(value = "pwdPermission",hidden = true)
	private String pwdPermission;
	@ApiModelProperty(value = "createBy",hidden = true)
	private String createBy;
	@ApiModelProperty(value = "createDate",hidden = true)
	private String createDate;
	@ApiModelProperty(value = "updateBy",hidden = true)
	private String updateBy;
	@ApiModelProperty(value = "updateDate",hidden = true)
	private String updateDate;
	@ApiModelProperty(value = "groupId",hidden = true)
	private String groupId;
	@ApiModelProperty(value = "credentialType",hidden = true)
	private String credentialType;
	@ApiModelProperty(value = "certcn",hidden = true)
	private String certcn;
	@ApiModelProperty(value = "cert",hidden = true)
	private String cert;
	@ApiModelProperty(value = "certenddate",hidden = true)
	private String certenddate;
	@ApiModelProperty(value = "groupFlag",hidden = true)
	private Integer groupFlag;

	// 关联组表名称
	@Pattern(regexp = "^[a-zA-Z]([a-zA-Z0-9\\s*\\._-]{3,63})$",message = "User name need to begin with letters,composed of letters,numbers, '_' , '-' , '.' , and have a length of 4 to 64")
	@ApiModelProperty(value = "业务用户组名称",  name = "groupName", example = "testBGroup", hidden = true)
	private String groupName;

}