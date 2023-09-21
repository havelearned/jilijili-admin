package top.jilijili.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Quasar UI
 * 主要使用前端选择框,做适配
 *
 * @author Amani
 * @date 2023年09月13日 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder
public class Item implements Serializable {
    private String label;
    private String value;
}
