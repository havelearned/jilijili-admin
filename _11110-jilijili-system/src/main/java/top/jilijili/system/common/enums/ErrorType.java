package top.jilijili.system.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    OPERATION_FAILED(11110,"操作失败");


    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

}
