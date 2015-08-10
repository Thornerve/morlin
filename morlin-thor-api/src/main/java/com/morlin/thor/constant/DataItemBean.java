package com.morlin.thor.constant;

/**
 * XML数据项Bean
 * 对应XML文件中的数据项
 * @author liu_yong
 *
 */
public class DataItemBean {

    /**
     * 编号
     */
    private String code;

    /**
     * 父编号
     */
    private String parentCode;

    /**
     * 名称
     */
    private String name;
    
    /**
     * 父节点名称
     */
    private String parentName;

    /**
     * 级别
     */
    private String level;

    /**
     * 构造函数
     */
    public DataItemBean() {

    }

    /**
     * @return 编号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 父编号
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode 父编号
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return 父节点名称
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * @param parentName 父节点名称
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * @return 级别
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level 级别
     */
    public void setLevel(String level) {
        this.level = level;
    }
}

