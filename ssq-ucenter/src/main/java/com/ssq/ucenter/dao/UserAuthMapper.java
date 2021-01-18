package com.ssq.ucenter.dao;

import com.ssq.ucenter.entity.UserAuth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserAuthMapper extends Mapper<UserAuth> {
    UserAuth selectByIdentifierAndCredential(String identifier, String credential);
    int updateCredentialByUid(@Param("userId")String userId, @Param("credential")String newCredential);
    int updateCredentialByIdentifier(String identifier, String credential);
}