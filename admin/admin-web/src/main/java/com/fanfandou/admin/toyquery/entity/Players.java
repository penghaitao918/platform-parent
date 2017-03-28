package com.fanfandou.admin.toyquery.entity;


import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:菜单的实体类.
 */
public class Players implements Serializable {
    private static Players init = null;
    private final String START_SET = "set";
    private final String START_GET = "get";
    private final String START_IS = "is";
    private final int START_THREE = 3; // "set"字符串长度 3


    // 参数初始值---begin
    private final String INIT_STRING = "";
    private final int INIT_INT = 0;
    private final double INIT_DOUBLE = 0.0;
    private final float INIT_FLOAT = 0;
    private final boolean INIT_BOOLEAN = true;
    private final char INIT_CHAR = ' ';


    private final BigDecimal INIT_BigDecimal = new BigDecimal("0.0");
    private final Integer INIT_Integer = new Integer(0);
    private final Double INIT_Double = new Double(0.0);
    private final Float INIT_Float = new Float(0.0);
    private final Boolean INIT_Boolean = new Boolean(true);
    private final Character INIT_Character = new Character(' ');
    private final java.sql.Date INIT_DateS = new java.sql.Date(
            System.currentTimeMillis());
    private final java.util.Date INIT_DateU = new java.util.Date(
            System.currentTimeMillis());
    private final java.sql.Timestamp INIT_Timestamp = new java.sql.Timestamp(
            System.currentTimeMillis());
    private final long INIT_Long = new Long(0);
    @SuppressWarnings("rawtypes")
    private final java.util.List INIT_List = new ArrayList();
// 参数初始值---end


    // 参数类型---begin 封装类型给出完整名
    private final String TYPE_String = "java.lang.String";
    private final String TYPE_int = "int";
    private final String TYPE_double = "double";
    private final String TYPE_float = "float";
    private final String TYPE_boolean = "boolean";
    private final String TYPE_char = "char";
    private final String TYPE_long = "long";
    private final String TYPE_BigDecimal = "java.math.BigDecimal";
    private final String TYPE_Integer = "java.lang.Integer";
    private final String TYPE_Double = "java.lang.Double";
    private final String TYPE_Float = "java.lang.Float";
    private final String TYPE_Boolean = "java.lang.Boolean";
    private final String TYPE_Character = "java.lang.Character";
    private final String TYPE_DateS = "java.sql.Date";
    private final String TYPE_DateU = "java.util.Date";
    private final String TYPE_Timestamp = "java.sql.Timestamp";
    private final String TYPE_List = "java.util.List";
// 参数类型---end


    @SuppressWarnings("rawtypes")
    private List setList = new ArrayList(); // 存储所有setter方法
    @SuppressWarnings("rawtypes")
    private List getList = new ArrayList(); // 存储所有getter方法


    private Players() {
// 无参构造器
    }


    // 单例
    public static Object FZ(Object bean) {
        if (init == null) {
            init = new Players();
        }
        return init.initBean(bean);
    }


    /**
     * 初始化属性主要方法
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object initBean(Object bean) {
        Object object = null;
        try {
// 实例化bean
            object = bean.getClass().newInstance();
// 获取bean的类名
            Class beanClass = bean.getClass();
// 获取该Bean对外公共setter方法
            Method[] beanMethods = beanClass.getMethods();
            for (int i = 0; i < beanMethods.length; i++) {
                if (beanMethods[i].getName().startsWith(START_SET)) {
                    this.setList.add(beanMethods[i]);
                } else if (beanMethods[i].getName().startsWith(START_GET)
                        || beanMethods[i].getName().startsWith(START_IS)) {
                    this.getList.add(beanMethods[i]);
                }
            }
            for (int i = 0; i < this.setList.size(); i++) {
                Method setMethod = (Method) this.setList.get(i);
                String getMethod = getter(setMethod.getName());
// 判断属性是否有get方法
                if (hasMethod(getMethod)) {
                    Method m = (Method) getList.get(i);
                    Object o = m.invoke(bean);// 执行get方法返回一个Object
                    if (o == null) {
// set方法参数类型
                        Class[] typeSet = setMethod.getParameterTypes();
// javaBean的set方法只有一个参数,只需要取第一个
                        Object[] oarray = initValue(typeSet[0]);
                        setMethod.invoke(object, oarray);
                    } else {
                        Object[] oarray = new Object[1];
                        oarray[0] = o;
                        setMethod.invoke(object, oarray);
                    }
                }
            }


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return object;
    }


    /**
     * 获取getter方法 一个属性get方法名
     */
    private String getter(String setter) {
        String getMethod = START_GET + setter.substring(START_THREE);
        if (hasMethod(getMethod)) {
// 获得getter方法存在返回
            return getMethod;
        } else {
// 获得getter方法不在 参数可能是boolean类型
            getMethod = START_IS + setter.substring(START_THREE);
            if (hasMethod(getMethod)) {
                return getMethod;
            }
        }
        return getMethod;
    }


    /**
     * <>判断get方法是否存在<>
     */
    private boolean hasMethod(String MethodName) {
        boolean flag = false;
        String tempName = null;
        Method tempMethod = null;
        for (int k = 0; k < getList.size(); k++) {
            tempMethod = (Method) getList.get(k);
            tempName = tempMethod.getName();
            if (MethodName.equals(tempName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    /**
     * 对属性初始化主要方法<br>
     * 可根据具体需求变动,比如初始化某一类型属性<br>
     * <br>
     */
    private Object[] initValue(@SuppressWarnings("rawtypes") Class type) {
        Object[] oarray = new Object[1];
// javaBean的setter方法只有一个参数，只需要一个一维Object数组
        String typeStr = type.getName();
        if (TYPE_String.equals(typeStr)) {
// String类型初始化
            oarray[0] = INIT_STRING;
        } else if (TYPE_int.equals(typeStr)) {
// int类型初始化
            oarray[0] = new Integer(INIT_INT);
        } else if (TYPE_double.equals(typeStr)) {
// double类型初始化
            oarray[0] = new Double(INIT_DOUBLE);
        } else if (TYPE_float.equals(typeStr)) {
// float类型初始化
            oarray[0] = new Float(INIT_FLOAT);
        } else if (String.valueOf(TYPE_char).equals(typeStr)) {
// char类型初始化
            oarray[0] = new Character(INIT_CHAR);
        } else if (TYPE_BigDecimal.equals(typeStr)) {
// BigDecima类型初始化
            oarray[0] = INIT_BigDecimal;
        } else if (TYPE_Integer.equals(typeStr)) {
// Integer类型初始化
            oarray[0] = INIT_Integer;
        } else if (TYPE_Double.equals(typeStr)) {
// Double类型初始化
            oarray[0] = INIT_Double;
        } else if (TYPE_Float.equals(typeStr)) {
// Float类型初始化
            oarray[0] = INIT_Float;
        } else if (TYPE_boolean.equals(typeStr)) {
// boolean类型初始化
            oarray[0] = new Boolean(INIT_BOOLEAN);
        } else if (TYPE_Boolean.equals(typeStr)) {
// Boolean类型初始化
            oarray[0] = INIT_Boolean;
        } else if (TYPE_Character.equals(typeStr)) {
// Character类型初始化
            oarray[0] = INIT_Character;
        } else if (TYPE_DateS.equals(typeStr)) {
// Date类型初始化(java.sql.Date)
            oarray[0] = INIT_DateS;
        } else if (TYPE_DateU.equals(typeStr)) {
// Date类型初始化(java.util.Date)
            oarray[0] = INIT_DateU;
        } else if (TYPE_Timestamp.equals(typeStr)) {
// Timestamp类型初始化(java.sql.Timestamp)
            oarray[0] = INIT_Timestamp;
        } else if (TYPE_List.equals(typeStr)) {
// List类型初始化(java.util.List)
            oarray[0] = INIT_List;
        } else if (TYPE_long.equals(typeStr)) {
// List类型初始化(java.util.List)
            oarray[0] = INIT_Long;
        } else {
            try {
                oarray[0] = type.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return oarray;
    }


    public static void main(String[] args) {
        Menu menu =new Menu();
        menu.setName("123");
        menu=(Menu) Players.FZ(menu);
        System.out.print(menu);

    }
}
