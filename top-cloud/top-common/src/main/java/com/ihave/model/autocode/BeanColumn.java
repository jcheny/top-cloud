package com.ihave.model.autocode;


import lombok.Data;

/**
 * 实体列
 */
@Data
public class BeanColumn {

    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 表\目录
     **/
    private String table_catalog;
    /**
     * 是否为null
     **/
    private String is_nullable;
    /**
     * 表名
     **/
    private String table_name;
    /**
     * 数据库
     **/
    private String table_schema;
    /**
     * 额外的 EXTRA": "auto_increment  自增id
     **/
    private String extra;
    /**
     * 列名
     **/
    private String column_name;
    /**
     * 主键 PRI
     **/
    private String column_key;
    /**
     * 数字精度
     **/
    private String numeric_precision;
    /**
     * 权限
     **/
    private String privileges;
    /**
     * 列注释
     **/
    private String column_comment;
    /**
     * 数字刻度
     **/
    private String numeric_scale;
    /**
     * 列/类型
     **/
    private String column_type;
    /**
     * 生成表达式
     **/
    private String generation_expression;
    /**
     * 序数位置
     **/
    private String ordinal_position;
    /**
     * 数据类型
     **/
    private String data_type;
    /**
     * 默认值
     **/
    private String column_default;
    /**
     * 字符最大长度
     **/
    private String character_maximum_length;
    /**
     * 字符\八位字节\长度
     **/
    private String character_octet_length;
    /**
     * 日期时间精度
     **/
    private String datetime_precision;
    /**
     * 字符集名称
     **/
    private String character_set_name;
    /**
     * 排序规则名称
     **/
    private String collation_name;

    /**
     * 实体类型 java.lang.String
     **/
    private String beanType;

    /**
     * 实体bean列名 例如:nameVc
     **/
    private String beanName;
    /**
     * mapperxml需要类型 例如:jdbcType="VARCHAR"
     **/
    private String jdbcType;

    /**
     * java类型
     **/
    private String javaType;
    /**
     * java 首字母大写
     **/
    private String javaName;


    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }


    public String getJavaType() {
        String beanType = this.getBeanType();
        String returnStr = "String";
        if (beanType == null) {
            return returnStr;
        } else {
            returnStr = beanType.substring(beanType.lastIndexOf(".") + 1, beanType.length());
        }
        javaType = returnStr;
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getBeanType() {
        String type = this.getData_type();
        String returnStr = "java.lang.String";
        if (type == null) {
            return returnStr;
        }
        switch (type) {
            case "tinyint":
                returnStr = "java.lang.Integer";
                break;
            case "smallint":
                returnStr = "java.lang.Integer";
                break;
            case "int":
                returnStr = "java.lang.Integer";
                break;
            case "bigint":
                returnStr = "java.lang.Long";
                break;
            case "mediumint":
                returnStr = "java.lang.Integer";
                break;
            case "integer":
                returnStr = "java.lang.Integer";
                break;
            case "float":
                returnStr = "java.lang.Float";
                break;
            case "double":
                returnStr = "java.lang.Double";
                break;
            case "decimal":
                returnStr = "java.math.BigDecimal";
                break;
            case "bit":
                returnStr = "java.lang.Byte";
                break;
            case "char":
                returnStr = "java.lang.Character";
                break;
            case "varchar":
                returnStr = "java.lang.String";
                break;
            case "tinytext":
                returnStr = "java.lang.String";
                break;
            case "text":
                returnStr = "java.lang.String";
                break;
            case "mediumtext":
                returnStr = "java.lang.String";
                break;
            case "longtext":
                returnStr = "java.lang.String";
                break;
            case "date":
                returnStr = "java.util.Date";
                break;
            case "datetime":
                returnStr = "java.util.Date";
                break;
            case "timestamp":
                returnStr = "java.util.Date";
                break;
            default:
                break;
        }
        beanType = returnStr;
        return beanType;
    }

    public String getBeanName() {
        if (getColumn_name() != null) {
            return upperCase_(this.column_name, false);
        }
        return beanName;
    }

    public String getJavaName() {
        javaName = firstUpperCase(getBeanName());
        return javaName;
    }


    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String firstUpperCase(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    /**
     * 将下划线转化为大写
     *
     * @param name
     * @param firstCase 首字母是否大写 true:大写 false;小写
     * @return
     */
    public static String upperCase_(String name, boolean firstCase) {
        if (isEmpty(name)) {
            return "";
        }
        String[] s = name.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        for (String s1 : s) {
            stringBuffer.append(s1.substring(0, 1).toUpperCase() + s1.substring(1));
        }
        if (!firstCase) {
            return firstLowerCase(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }


    public static String firstLowerCase(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;

    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    public String getJdbcType() {
        String datetype = getData_type();
        String returnStr = "VARCHAR";
        if (datetype == null) {
            return returnStr;
        }
        switch (datetype) {
            case "tinyint":
                returnStr = "TINYINT";
                break;
            case "smallint":
                returnStr = "SMALLINT";
                break;
            case "int":
                returnStr = "INTEGER";
                break;
            case "bigint":
                returnStr = "BIGINT";
                break;
            case "mediumint":
                returnStr = "INTEGER";
                break;
            case "integer":
                returnStr = "INTEGER";
                break;
            case "float":
                returnStr = "REAL";
                break;
            case "double":
                returnStr = "DOUBLE";
                break;
            case "decimal":
                returnStr = "DECIMAL";
                break;
            case "bit":
                returnStr = "OTHER";
                break;
            case "char":
                returnStr = "CHAR";
                break;
            case "varchar":
                returnStr = "VARCHAR";
                break;
            case "tinytext":
                returnStr = "VARCHAR";
                break;
            case "text":
                returnStr = "VARCHAR";
                break;
            case "mediumtext":
                returnStr = "VARCHAR";
                break;
            case "longtext":
                returnStr = "VARCHAR";
                break;
            case "date":
                returnStr = "TIMESTAMP";
                break;
            case "datetime":
                returnStr = "TIMESTAMP";
                break;
            case "timestamp":
                returnStr = "TIMESTAMP";
                break;
            default:
                break;
        }
        jdbcType = returnStr;
        return jdbcType;
    }

}
