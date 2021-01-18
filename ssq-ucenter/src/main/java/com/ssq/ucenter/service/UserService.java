package com.ssq.ucenter.service;

import com.ssq.commons.response.Result;
import com.ssq.ucenter.model.request.*;

public interface UserService{
    Result login(LoginRequest request);
    Result regist(RegistRequest request);
    Result bind(BindRequest request);
    Result create();
    Result modifyPwd(ModifyPwdRequest request);
    Result forgetPwd(ForgetPwdRequest request);
    Result resetPwd(ResetPwdRequest request);
}
