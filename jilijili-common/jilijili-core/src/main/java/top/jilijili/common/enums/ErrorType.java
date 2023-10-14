package top.jilijili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    OPERATION_FAILED(11110,"操作失败");


    /**
     * 错误码
     */
    private final int code;

    /**
     * 提示信息
     */
    private final String msg;

}
