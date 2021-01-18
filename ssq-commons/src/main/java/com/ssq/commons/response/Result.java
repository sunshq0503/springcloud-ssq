package com.ssq.commons.response;

import com.ssq.commons.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    /**
     * 1.status状态值：代表本次请求response的状态结果。
     */
    private Integer status;
    /**
     * 2.response描述：对本次状态码的描述。
     */
    private String desc;
    /**
     * 3.data数据：本次返回的数据。
     */
    private T data;

    /**
     * 成功，创建ResResult：没data数据
     */
    public static Result ok() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功，创建ResResult：有data数据
     */
    public static Result okCount(int count) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        Map map = new HashMap();
        map.put("count",count);
        result.setData(map);
        return result;
    }

    /**
     * 成功，创建ResResult：有data数据
     */
    public static Result ok(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     *
     * @param desc
     * @return
     */
    public static Result error(String desc) {
        Result result = new Result();
        result.setStatus(500);
        result.setDesc(desc);
        return result;
    }

    /**
     * 系統异常
     * @param
     * @return
     */
    public static Result exception() {
        Result result = new Result();
        result.setResultCode(ResultCode.SYSTEM_ERROR);
        return result;
    }

    /**
     * 把ResultCode枚举转换为ResResult
     */
    private void setResultCode(ResultCode code) {
        this.status = code.getCode();
        this.desc = code.getMessage();
    }
}
