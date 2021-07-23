package com.screw.datasourcedoc.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: jiangjs
 * @description: 统一结果返回
 * @date: 2021/7/23 14:29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ResultJson<T> success(){
        return ResultJson.<T>builder()
                .code(200)
                .msg("成功")
                .build();
    }
    public static <T> ResultJson<T> success(T t){
        return ResultJson.<T>builder()
                .code(200)
                .msg("成功")
                .data(t)
                .build();
    }
    public static <T> ResultJson<T> error(int code,String msg){
        return ResultJson.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
