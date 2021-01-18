package com.ssq.ucenter.model.request;

import lombok.Data;

@Data
public class ModifyPwdRequest {
    String credential;
    String newCredential;
}
