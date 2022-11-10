package com.yue.config.config.JSON;

public class ResultTool {
//    成功并传数据
    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }
//    失败，及失败原因
    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
