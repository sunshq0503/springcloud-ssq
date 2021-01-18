package com.ssq.ucenter.model.request;

import lombok.Data;

@Data
public class ForgetPwdRequest {
    Integer identityType;
    String identifier;
}
