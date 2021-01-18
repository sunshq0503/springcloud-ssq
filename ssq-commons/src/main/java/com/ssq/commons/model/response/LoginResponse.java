package com.ssq.commons.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    Integer uaid;//user_auth_id
    String uid;//user_id
    String nickName;
    String token;
    String identifier;
    Integer identityType;
}
