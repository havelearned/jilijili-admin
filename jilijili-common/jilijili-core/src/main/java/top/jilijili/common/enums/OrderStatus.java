package top.jilijili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    COMPLETED_ORDERS("已完成", 1),
    INCOMPLETE_ORDERS("未完成", 0),
    EXPIRED_ORDERS("过期", 2);

    private final String label;
    private final Integer value;
}
