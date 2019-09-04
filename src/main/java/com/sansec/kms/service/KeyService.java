package com.sansec.kms.service;

import com.sansec.kms.Result.CodeMsg;
import com.sansec.kms.Result.Result;
import com.sansec.kms.exception.GlobalException;
import com.sansec.kms.model.CreateKeyModel;
import com.sansec.kms.model.SynthetizeKeyModel;
import com.sansec.kms.model_Vo.SeesionKeyModel;
import com.sansec.kms.model_Vo.PageInfo;
import klms.Error;
import klms.db.DbOper;
import klms.model.klmsenum.EnumCryptographicAlgorithm;
import klms.model.klmsenum.EnumObjectType;
import klms.model.objects.Credential;
import klms.model.objects.ManagedObj;
import klms.request.*;
import klms.response.CreateKeyResponse;
import klms.response.GetKeyResponse;
import klms.response.ListKeysResponse;
import klms.response.OperKeyResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

import static com.sansec.kms.tools.CommonTools.twoStringXor;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:
 * @Date: 2019/8/19 11:38
 */
@Service
public class KeyService {

    @Autowired
    DbOper dbOper;
    /**
     * 创建密钥
     * @param createKeyModel
     * @return
     */
    public Result createKey(CreateKeyModel createKeyModel){
        Credential user = dbOper.getUser(createKeyModel.getAppId());
        if(null == user){
            //todo
        }
        CreateKeyRequest createKeyRequest = new CreateKeyRequest();
        createKeyRequest.setRemark(createKeyModel.getName());
        createKeyRequest.setUserName(createKeyModel.getAppId());
        createKeyRequest.setObjectType(createKeyModel.getKeyType()+"");
        EnumCryptographicAlgorithm enumCryptographicAlgorithm = new EnumCryptographicAlgorithm(createKeyModel.getKeyAlgorithm().toUpperCase());
        createKeyRequest.setAlg(enumCryptographicAlgorithm.getValue());
        createKeyRequest.setKeyLength(createKeyModel.getKeySize());
        createKeyRequest.setLmkNumber(createKeyModel.getLmk());
        CreateKeyResponse createKeyResponse = dbOper.addKey(createKeyRequest);
        if(createKeyResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.CREATE_SYMM_KEY_ERROR.fillArgs(createKeyResponse.getMsg()));
        }
        return Result.success(createKeyResponse.getManagedObj().getS_uniqueIdentifier());
    }

    /**
     * 密钥分量合成密钥
     * @param synthetizeKeyModel
     * @return
     */
    public Result synthetizeKey(SynthetizeKeyModel synthetizeKeyModel){
//        查看用户是否存在，不存在直接创建
        Credential user = dbOper.getUser(synthetizeKeyModel.getAppId());
        if(null == user){
            //todo
        }
        CreateKeyRequest createKeyRequest = new CreateKeyRequest();
        createKeyRequest.setRemark(synthetizeKeyModel.getName());
        createKeyRequest.setUserName(synthetizeKeyModel.getAppId());
        EnumObjectType enumObjectType = new EnumObjectType(synthetizeKeyModel.getKeyType());
        createKeyRequest.setObjectType(enumObjectType.getKey());
        EnumCryptographicAlgorithm enumCryptographicAlgorithm = new EnumCryptographicAlgorithm(synthetizeKeyModel.getKeyAlgorithm().toUpperCase());
        createKeyRequest.setAlg(enumCryptographicAlgorithm.getValue());
        createKeyRequest.setKeyLength(synthetizeKeyModel.getKeySize());
        createKeyRequest.setLmkNumber(synthetizeKeyModel.getLmk());
        createKeyRequest.setKeyApplicationType(synthetizeKeyModel.getKeyApplicationType());
        createKeyRequest.setCreateType(synthetizeKeyModel.getCreateType());
        createKeyRequest.setLmkNumber(synthetizeKeyModel.getLmk());

//        密钥分量的异或操作
        List<String> list = synthetizeKeyModel.getComponents();
        String key = list.get(0);
        for (int i = 1; i < list.size(); i++) {
           key = twoStringXor(key, list.get(i));
        }

        createKeyRequest.setKeyMaterial(key);
        CreateKeyResponse createKeyResponse = dbOper.addKey(createKeyRequest);
        if(createKeyResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.SYNTH_SYMM_KEY_ERROR.fillArgs(createKeyResponse.getMsg()));
        }
        return Result.success(createKeyResponse.getManagedObj().getS_uniqueIdentifier());
    }
    public Result activateKey(String keyId){
        OperKeyRequest operKeyRequest = new OperKeyRequest();
        operKeyRequest.setKeyId(keyId);
        OperKeyResponse operKeyResponse = dbOper.activateKey(operKeyRequest);

        if(operKeyResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.ACTIVATE_KEY_ERROR.fillArgs(operKeyResponse.getMsg()));
        }
        return Result.success(keyId);
    }
    public Result destroyKey(String keyId){
        OperKeyRequest operKeyRequest = new OperKeyRequest();
        operKeyRequest.setKeyId(keyId);
        OperKeyResponse operKeyResponse = dbOper.destroyKey(operKeyRequest);

        if(operKeyResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.DESTROY_KEY_ERROR.fillArgs(operKeyResponse.getMsg()));
        }
        return Result.success(keyId);
    }
    public Result revokeKey(String keyId){
        OperKeyRequest operKeyRequest = new OperKeyRequest();
        operKeyRequest.setKeyId(keyId);
        OperKeyResponse operKeyResponse = dbOper.revokeKey(operKeyRequest);

        if(operKeyResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.REVOKE_KEY_ERROR.fillArgs(operKeyResponse.getMsg()));
        }
        return Result.success(keyId);
    }
    public  Result deleteKey(String keyId){
        OperKeyRequest operKeyRequest = new OperKeyRequest();
        operKeyRequest.setKeyId(keyId);
        OperKeyResponse operKeyResponse = dbOper.deleteKey(operKeyRequest);

        if(operKeyResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.DELETE_KEY_ERROR.fillArgs(operKeyResponse.getMsg()));
        }
        return Result.success(keyId);
    }

    public Result updateKey(String keyId){
        GetKeyRequest getKeyRequest = new GetKeyRequest();
        getKeyRequest.setKeyId(keyId);
        GetKeyResponse key = dbOper.getKey(getKeyRequest);
        if(key.getManagedObj().getCreateType()==2){
            throw new GlobalException(CodeMsg.UPDATE_KEY_ERROR.fillArgs("key create type is illegal, only the key created automatically can be updated."));
        }
        //todo
//        OperKeyRequest operKeyRequest = new OperKeyRequest();
//        operKeyRequest.setKeyId(keyId);
//        OperKeyResponse operKeyResponse = dbOper.(operKeyRequest);
//
//        if(operKeyResponse.getStatus()!= Error.SUCCESS.code){
//            throw new GlobalException(CodeMsg.UPDATE_KEY_ERROR.fillArgs(operKeyResponse.getMsg()));
//        }
        return Result.success(keyId);
    }
    public Result findKey(String preId,int keyVersion,String keyId){
        if((StringUtils.isNotBlank(preId)&&keyVersion==0)||(keyVersion!=0&&StringUtils.isBlank(preId))){
            throw new GlobalException(CodeMsg.FIND_KEY_ERROR.fillArgs("please use keyID and keyVersion to find a key uniquely."));
        }
        if(StringUtils.isBlank(preId)&&StringUtils.isBlank(keyId)&&0==keyVersion){
            throw new GlobalException(CodeMsg.FIND_KEY_ERROR.fillArgs("parameters check error."));
        }
        GetKeyRequest getKeyRequest = new GetKeyRequest();
        GetKeyResponse key;
        if(StringUtils.isNotBlank(preId)){
            getKeyRequest.setKeyName(preId);
            getKeyRequest.setKeyVersion(keyVersion);
        }
        if(StringUtils.isNotBlank(keyId)){
            getKeyRequest.setKeyId(keyId);
        }
        key = dbOper.getKey(getKeyRequest);
        if(key.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.FIND_KEY_ERROR.fillArgs(key.getMsg()));
        }
        return Result.success(key.getManagedObj());
    }
    public Result listKeys(String preId, String appId , Integer keyApplicationType,int pageSize,int pageNum){
        ListKeysRequest listKeysRequest = new ListKeysRequest();
        if(StringUtils.isNotBlank(preId)){
            listKeysRequest.setKeyName(preId);
        }
        if(StringUtils.isNotBlank(appId)){
            listKeysRequest.setUserName(appId);
        }
        if(null!=keyApplicationType){
            if((1<=keyApplicationType.intValue())&&(keyApplicationType.intValue()<=2)){
                listKeysRequest.setKeyApplicationType(keyApplicationType);
            }else{
                throw new GlobalException(CodeMsg.LIST_KEY_ERROR.fillArgs("Key application type is illegal and must be from 1 to 2"));
            }
        }
        listKeysRequest.setPageNumber(pageNum);
        listKeysRequest.setPageSize(pageSize);
        ListKeysResponse listKeysResponse = dbOper.getKeyList(listKeysRequest);
        if(listKeysResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.LIST_KEY_ERROR.fillArgs(listKeysResponse.getMsg()));
        }
        List<ManagedObj> managedObjs = listKeysResponse.getManagedObjs();
        List<SeesionKeyModel> list = new ArrayList<>();
        SeesionKeyModel keyModel = new SeesionKeyModel();
        if(null==managedObjs){
            return Result.success(null);
        }else{
            for (int i = 0; i <managedObjs.size(); i++) {
                keyModel.setKeyId(managedObjs.get(i).getS_uniqueIdentifier());
                keyModel.setPreId(managedObjs.get(i).getS_nameValue());
                keyModel.setAppId(managedObjs.get(i).getUserName());
                keyModel.setRemark(managedObjs.get(i).getRemark());
                keyModel.setCreateTime(managedObjs.get(i).getS_initialDate());
                keyModel.setCreateType(managedObjs.get(i).getCreateType());
                keyModel.setKeyAlgorithm(managedObjs.get(i).getS_cryptographicAlgorithm());
                keyModel.setKeyType(managedObjs.get(i).getS_objectType());
                keyModel.setKeyApplicationType(managedObjs.get(i).getKeyApplicationType());
                keyModel.setHashValue(Base64Utils.encodeToString(managedObjs.get(i).getKeyHash()));
                keyModel.setKeySize(managedObjs.get(i).getKeyLength().intValue());
                keyModel.setKeyVersion(managedObjs.get(i).getS_keyVersion());
//                keyModel.setLMK(managedObjs.get(i).get);
                list.add(keyModel);
            }
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(listKeysResponse.getTotalCount());
        pageInfo.setPageNum(listKeysResponse.getPageNum());
        pageInfo.setPageSize(listKeysResponse.getPageSize());
        pageInfo.setPages(listKeysResponse.getPages());
        return Result.success(pageInfo);
    }
    public Result listHistoryKeys(String preId,int pageSize,int pageNum){
        ListHistoryKeysRequest listHistoryKeysRequest = new ListHistoryKeysRequest();
        listHistoryKeysRequest.setKeyName(preId);
        listHistoryKeysRequest.setPageNumber(pageNum);
        listHistoryKeysRequest.setPageSize(pageSize);
        ListKeysResponse listKeysResponse = dbOper.getHistoryKeyList(listHistoryKeysRequest);
        if(listKeysResponse.getStatus()!= Error.SUCCESS.code){
            throw new GlobalException(CodeMsg.LIST_HISTORY_KEY_ERROR.fillArgs(listKeysResponse.getMsg()));
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(listKeysResponse.getManagedObjs());
        pageInfo.setTotal(listKeysResponse.getTotalCount());
        pageInfo.setPageNum(listKeysResponse.getPageNum());
        pageInfo.setPageSize(listKeysResponse.getPageSize());
        pageInfo.setPages(listKeysResponse.getPages());
        return Result.success(pageInfo);
    }
}
