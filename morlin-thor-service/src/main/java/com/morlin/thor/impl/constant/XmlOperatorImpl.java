package com.morlin.thor.impl.constant;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.morlin.thor.constant.Constants;
import com.morlin.thor.constant.DataItemBean;
import com.morlin.thor.service.constant.IXmlOperator;

public class XmlOperatorImpl implements IXmlOperator{

	/**
     * 
     * 功能描述: <br>
     * dom4j方式解析XML文件
     * 
     * @param pathname XML文件路径
     * @return XML文件解析结果
     * @throws DocumentException 读取XML文件，生成document对象时异常
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Override
    public Map<String, Map<String, Object>> parseXml(String pathname) throws DocumentException {

        // XML解析结果，存放XML文件信息
        Map<String, Map<String, Object>> dataItemMap = new HashMap<String, Map<String, Object>>();
        // XML Document对象
        Document document = null;
        // 根节点元素对象
        Element rootElement = null;
        // 文件解析对象
        SAXReader saxReader = new SAXReader();

        // 输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(pathname);

        // 读取XML文件，生成document对象
        document = saxReader.read(inputStream);

        // 获取xml根元素
        rootElement = document.getRootElement();

        // 调用节点解析方法
        dataItemMap = getElements(rootElement, rootElement, dataItemMap);

        return dataItemMap;
    }

    /**
     * 
     * 功能描述: <br>
     * 解析节点数据项信息
     * 
     * @param rootElement 根节点
     * @param parentId 父编号
     * @param dataItemMap 代码集合
     * @return XML文件对应的代码集合
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private Map<String, Map<String, Object>> getElements(Element rootElement, Element parentElement,
            Map<String, Map<String, Object>> dataItemMap) {

        // 内层Map，包含两项：dataItemBean和dataItemBeanList
        Map<String, Object> itemMap = new HashMap<String, Object>();
        // 子节点元素对象
        Element element = null;
        // XML数据项对象
        DataItemBean dataItemBean = null;
        // XML数据项对象集合
        List<DataItemBean> dataItemBeanList = new LinkedList<DataItemBean>();
        // 父节点Code
        String parentId = parentElement.attributeValue(Constants.ATTR_CODE);
        // 父节点名称
        String parentName = parentElement.attributeValue(Constants.ATTR_CODE_NAME);

        // 获取根目录下的元素集合
        List elmentList = rootElement.elements();

        /**
         * 根目录下是否有元素
         */
        DataItemBean parentBean = new DataItemBean();
        parentBean.setCode(rootElement.attributeValue(Constants.ATTR_CODE));
        parentBean.setName(rootElement.attributeValue(Constants.ATTR_CODE_NAME));
        parentBean.setParentCode(parentId);
        parentBean.setParentName(parentName);
        parentBean.setLevel(rootElement.attributeValue(Constants.ATTR_LEVEL));
        itemMap.put(Constants.DATA_ITEM_BEAN, parentBean);
        if (elmentList.size() == 0) {
            /**
             * 根目录下没有元素，将根节点数据封装到Bean对象中
             */
            dataItemBean = new DataItemBean();
            dataItemBean.setCode(rootElement.attributeValue(Constants.ATTR_CODE));
            dataItemBean.setName(rootElement.attributeValue(Constants.ATTR_CODE_NAME));
            dataItemBean.setParentCode(parentId);
            dataItemBean.setParentName(parentName);
            dataItemBean.setLevel(rootElement.attributeValue(Constants.ATTR_LEVEL));
            dataItemBeanList.add(dataItemBean);
            itemMap.put(Constants.DATA_ITEM_BEAN_LIST, new LinkedList<DataItemBean>());
            dataItemMap.put(rootElement.attributeValue(Constants.ATTR_CODE), itemMap);
        } else {
            /**
             * 根目录下有元素，解析元素数据项，封装到返回结果中，递归调用解析方法，获取根元素数据项，直到节点下再无子节点
             */
            parentId = rootElement.attributeValue(Constants.ATTR_CODE);
            for (Iterator it = elmentList.iterator(); it.hasNext();) {
                element = (Element) it.next();

                /**
                 * 将节点数据封装到Bean对象中
                 */
                dataItemBean = new DataItemBean();
                dataItemBean.setCode(element.attributeValue(Constants.ATTR_CODE));
                dataItemBean.setName(element.attributeValue(Constants.ATTR_CODE_NAME));
                dataItemBean.setParentCode(parentId);
                dataItemBean.setParentName(parentName);
                dataItemBean.setLevel(element.attributeValue(Constants.ATTR_LEVEL));
                dataItemBeanList.add(dataItemBean);
                itemMap.put(Constants.DATA_ITEM_BEAN_LIST, dataItemBeanList);
                dataItemMap.put(rootElement.attributeValue(Constants.ATTR_CODE), itemMap);

                // 递归遍历
                getElements(element, rootElement, dataItemMap);
            }
        }

        return dataItemMap;
    }
}
