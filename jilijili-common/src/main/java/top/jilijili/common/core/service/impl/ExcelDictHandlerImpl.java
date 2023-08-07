package top.jilijili.common.core.service.impl;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Amani
 * @date 2023年04月22日 0:57
 */
@Slf4j
public class ExcelDictHandlerImpl implements IExcelDictHandler {

    /**
     * 从值翻译到名称
     *
     * @param dict  字典Key
     * @param obj   对象
     * @param name  属性名称
     * @param value 属性值
     * @return
     */
    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        log.debug(dict, obj, name, value);
        return null;
    }

    /**
     * 从名称翻译到值
     *
     * @param dict  字典Key
     * @param obj   对象
     * @param name  属性名称
     * @param value 属性值
     * @return
     */
    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        log.debug(dict, obj, name, value);
        return null;
    }
}
