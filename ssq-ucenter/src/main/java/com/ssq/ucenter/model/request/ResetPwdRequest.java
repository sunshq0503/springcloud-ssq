package com.ssq.ucenter.model.request;

import lombok.Data;

@Data
public class ResetPwdRequest {
    String identifier;
    String verifyCode;
    String credential;
}
