package com.ssq.ucenter.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    Integer identityType;
    String identifier;
    String credential;
}
