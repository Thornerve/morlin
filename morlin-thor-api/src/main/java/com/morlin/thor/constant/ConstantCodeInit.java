package com.morlin.thor.constant;

import org.springframework.beans.factory.InitializingBean;

public class ConstantCodeInit implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        
        /**
         * 解析xml
         */
        XmlOperatorImpl xmlOperatorImpl = new XmlOperatorImpl();

        /**
         * 如果是行政区域代码XML，则将解析的结果存入行政区域Map中
         */
        DataItemService.regionsMap.putAll(xmlOperatorImpl
                .parseXml(Constants.CONTENT_PATH + "/" + Constants.REGIONS_XML));

        /**
         * 如果是编码、品牌、城市站等代码，则将解析的结果存入其他Map中
         */
        DataItemService.otherMap.putAll(xmlOperatorImpl.parseXml(Constants.CONTENT_PATH + "/" + Constants.OTHER_XML));

    }
}
