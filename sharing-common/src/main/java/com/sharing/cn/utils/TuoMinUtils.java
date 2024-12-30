//package com.sharing.cn.utils;
//
//import org.apache.commons.beanutils.BeanUtilsBean;
//import org.apache.commons.beanutils.ConvertUtilsBean;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.commons.beanutils.PropertyUtilsBean;
//import org.apache.commons.lang3.StringUtils;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * 脱敏工具类
// */
//public class TuoMinUtils {
//
//    public static String sensifltMobile(String mobileNo) {
//        if (StringUtils.isEmpty(mobileNo)) {
//            return mobileNo;
//        } else {
//            return mobileNo.length() != 11 ? mobileNo : mobileNo.substring(0, 3) + "****" + mobileNo.substring(7);
//        }
//    }
//
//    public static String sensifltName(String name) {
//        if (StringUtils.isEmpty(name)) {
//            return name;
//        } else {
//            int len = name.length();
//            switch (len) {
//                case 1:
//                    return name;
//                case 2:
//                case 3:
//                    return "*" + name.substring(1);
//                default:
//                    return "****" + name.substring(name.length() - 2);
//            }
//        }
//    }
//
//
//    public static String sensifltAddress(String address) {
//        if (StringUtils.isEmpty(address)) {
//            return address;
//        } else {
//            return address.substring(0, 9) + "*********";
//        }
//    }
//
//    public static String sensifltSex(String sex) {
//        return !"男".equals(sex) && !"女".equals(sex) ? sex : "*";
//    }
//
//    public static String sensifltIdNo(String idNo) {
//        if (StringUtils.isEmpty(idNo)) {
//            return idNo;
//        } else {
//            int len = idNo.length();
//            if (len != 15 && len != 18) {
//                return idNo;
//            } else {
//                return len == 15 ? idNo.substring(0, 4) + "********" + idNo.substring(12) : idNo.substring(0, 6) + "*********" + idNo.substring(15);
//            }
//        }
//    }
//
//
//    public static String sensifltRecId(String recId) {
//        if (StringUtils.isEmpty(recId)) {
//            return recId;
//        } else {
//            int len = recId.length();
//            if (len != 29 && len != 32) {
//                return recId;
//            } else {
//                return len == 29 ? recId.substring(0, 4) + "********" + recId.substring(26) : recId.substring(0, 6) + "*********" + recId.substring(29);
//            }
//        }
//    }
//
//    public static void tuomin(TUOMIN tw, Object obj, String property) {
//        try {
//            _tuomin(tw, obj, property);
//        } catch (Exception var4) {
//            var4.printStackTrace();
//        }
//
//    }
//
//    private static void _tuomin(TUOMIN tw, Object obj, String property) {
//        if (obj != null && property != null) {
//            Object val = getPropertyByName(obj, property);
//            if (val instanceof String) {
//                String valStr = (String)val;
//                String result = null;
//                switch (tw) {
//                    case Sex:
//                        result = sensifltSex(valStr);
//                        break;
//                    case IdNo:
//                    case IdCard:
//                    case SrcDoctAccount:
//                        result = sensifltIdNo(valStr);
//                        break;
//                    case Mobile:
//                    case SrcDoctAccountMobile:
//                        result = sensifltMobile(valStr);
//                        break;
//                    case RecId:
//                    case InbId:
//                        result = sensifltRecId(valStr);
//                        break;
//                    case Name:
//                    case PersonName:
//                    case RealName:
//                        result = sensifltName(valStr);
//                        break;
//                    case Address:
//                        result = sensifltAddress(valStr);
//                        break;
//                    default:
//                        return;
//                }
//
//                setProperty(obj, property, result);
//            }
//        }
//    }
//
//    public static void tuomin(List lst, List<TUOMIN> tws, String... props) {
//        if (!WeicCollectionUtils.isEmpty(lst) && !WeicCollectionUtils.isEmpty(tws)) {
//            try {
//                Iterator var3 = lst.iterator();
//
//                while(var3.hasNext()) {
//                    Object obj = var3.next();
//                    int index = 0;
//                    Iterator var6 = tws.iterator();
//
//                    while(var6.hasNext()) {
//                        TUOMIN tw = (TUOMIN)var6.next();
//                        tuomin(tw, obj, props[index++]);
//                    }
//                }
//            } catch (Exception var8) {
//                var8.printStackTrace();
//            }
//
//        }
//    }
//
//    public static enum TUOMIN {
//        IdNo,
//        IdCard,
//        Mobile,
//        Name,
//        PersonName,
//        SrcDoctAccount,
//        SrcDoctAccountMobile,
//        RealName,
//        RecId,
//        Address,
//        InbId,
//        Sex;
//
//        private TUOMIN() {
//        }
//    }
//
//    public static Object getPropertyByName(Object obj, String property) {
//        try {
//            return PropertyUtils.getProperty(obj, property);
//        } catch (IllegalAccessException var3) {
//            var3.printStackTrace();
//            throw new WeicRuntimeException(var3.getMessage());
//        } catch (InvocationTargetException var4) {
//            var4.printStackTrace();
//            throw new WeicRuntimeException(var4.getMessage());
//        } catch (NoSuchMethodException var5) {
//            var5.printStackTrace();
//            throw new WeicRuntimeException(var5.getMessage());
//        }
//    }
//
//
//    public static void setProperty(Object bean, String pName, Object pVal) {
//        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
//        convertUtilsBean = regConverters(convertUtilsBean);
//        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
//
//        try {
//            beanUtilsBean.setProperty(bean, pName, pVal);
//        } catch (Throwable var6) {
//            var6.printStackTrace();
//        }
//
//    }
//
//
//    private static ConvertUtilsBean regConverters(ConvertUtilsBean convertUtilsBean) {
//        convertUtilsBean.deregister(Date.class);
//        convertUtilsBean.register(new WeicDateConverter(), Date.class);
//        convertUtilsBean.deregister(String.class);
//        convertUtilsBean.register(new WeicStringConverter(), String.class);
//        return convertUtilsBean;
//    }
//
//
//}
//
