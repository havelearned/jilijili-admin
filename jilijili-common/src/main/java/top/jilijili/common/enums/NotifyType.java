package top.jilijili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Amani
 * @date 2023年09月10日 8:45
 */
@Getter
@AllArgsConstructor
public enum NotifyType {

    /**
     * 系统通知
     */
    SYSTEM_NOTIFY(1),

    /**
     * 异常通知
     */
    ERROR_NOTIFY(2),

    /**
     * 未知,解释不通的通知
     */
    UNKNOWN_NOTIFY(3);


    private final Integer value;

}
