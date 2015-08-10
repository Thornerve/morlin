package com.morlin.thor.service.constant;

import java.util.Map;
import org.dom4j.DocumentException;

/**
 * XML解析接口
 * @author liu_yong
 *
 */
public interface IXmlOperator {

	 /**
     * 
     * 功能描述: <br>
     * XML解析接口定义；根据传入的XML文件路径，获取文件路径输入流，使用dom4j方式对XML文件进行解析
     * 
     * @param pathname 代码XML文件路径
     * @return 返回XML中的数据集合，结构：Map<String, Map<String, Object>>
     *         其中，外层Map的key对应XML中的"code"属性值；
     *         内层Map中的key包含两项：dataItemBean和dataItemList.
     *         dataItemBean为外层"code"属性值对应的数据项Bean（DataItemBean），
     *         dataItemBeanList为外层"code"属性值对应的子数据项Bean集合
     * @throws DocumentException 读取XML文件，生成document对象时异常
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    Map<String, Map<String, Object>> parseXml(String pathname) throws DocumentException;
}
