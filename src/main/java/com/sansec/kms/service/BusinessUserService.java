package com.sansec.kms.service;

import com.sansec.kms.Result.Result;
import com.sansec.kms.model.BusinessUserModel;
import org.springframework.stereotype.Service;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/8/19 11:36
 */
@Service
public class BusinessUserService {
    Result createBusinessUser(BusinessUserModel businessUserModel){
        return Result.success(null);
    }
    Result editBusinessUser( String userId,String pwd,String userName){
        return Result.success(null);
    }
    Result deleteBusinessUser(String userId){
        return Result.success(null);
    }
}
