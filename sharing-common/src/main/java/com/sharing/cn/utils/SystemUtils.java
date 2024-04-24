package com.sharing.cn.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author ext.shikai1
 */
public class SystemUtils {


    // =================================== System.getProperty() 使用==========================================
    /**
     *  Java 运行时环境版本
     */
    public static final String VERSION = "java.version";

    /**
     * Java 运行时环境供应商
     */
    public static final String VENDOR = "java.vendor";

    /**
     * Java 供应商的 URL
     */
    public static final String VENDOR_URL = "java.vendor.url";
    /**
     * Java 安装目录
     */
    public static final String HOME = "java.home";
    /**
     *  Java 虚拟机规范版本
     */
    public static final String VM_SPECIFICATION_VERSION = "java.vm.specification.version";
    /**
     * Java 虚拟机规范供应商
     */
    public static final String VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
    /**
     * Java 虚拟机规范名称
     */
    public static final String VM_SPECIFICATION_NAME = "java.vm.specification.name";
    /**
     * Java 虚拟机实现版本
     */
    public static final String VM_VERSION = "java.vm.version";
    /**
     * Java 虚拟机实现供应商
     */
    public static final String VM_VENDOR = "java.vm.vendor";
    /**
     * Java 虚拟机实现名称
     */
    public static final String VM_NAME = "java.vm.name";
    /**
     * Java 运行时环境规范版本
     */
    public static final String SPECIFICATION_VERSION = "java.specification.version";
    /**
     * Java 运行时环境规范供应商
     */
    public static final String SPECIFICATION_VENDOR = "java.specification.vendor";
    /**
     * Java 运行时环境规范名称
     */
    public static final String SPECIFICATION_NAME = "java.specification.name";
    /**
     *  Java 类格式版本号
     */
    public static final String CLASS_VERSION = "java.class.version";
    /**
     * Java 类路径
     */
    public static final String CLASS_PATH = "java.class.path";
    /**
     * 加载库时搜索的路径列表
     */
    public static final String LIBRARY_PATH = "java.library.path";
    /**
     * 默认的临时文件路径
     */
    public static final String IO_TMPDIR = "java.io.tmpdir";
    /**
     * 要使用的 JIT 编译器的名称
     */
    public static final String COMPILER = "java.compiler";
    /**
     * 一个或多个扩展目录的路径
     */
    public static final String EXT_DIRS = "java.ext.dirs";
    /**
     * 操作系统的名称
     */
    public static final String OS_NAME = "os.name";
    /**
     * 操作系统的架构
     */
    public static final String OS_ARCH = "os.arch";
    /**
     * 操作系统的版本
     */
    public static final String OS_VERSION = "os.version";
    /**
     * 文件分隔符（在 UNIX 系统中是“/”）
     */
    public static final String FILE_SEPARATOR = "file.separator";
    /**
     * 路径分隔符（在 UNIX 系统中是“:”）
     */
    public static final String PATH_SEPARATOR = "path.separator";
    /**
     * 行分隔符（在 UNIX 系统中是“/n”）
     */
    public static final String LINE_SEPARATOR = "line.separator";
    /**
     * 用户的账户名称
     */
    public static final String USER_NAME = "user.name";
    /**
     * 用户的主目录
     */
    public static final String USER_HOME = "user.home";
    /**
     * 用户的当前工作目录
     */
    public static final String USER_DIR = "user.dir";

    /**
     * System.getProperty() 获取属性
     * @return
     */
    public static String getProperty(String code) {
        return System.getProperty(code);
    }

    /**
     * 获得当前时间的毫秒数，从1970年到现在的毫秒数
     * @return
     */
    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 调用垃圾回收器
     */
    public static void gc() {
        System.gc();
    }

    /**
     * 数组替换
     * @param source    源数组
     * @param sourceIndex   源数组要复制的起始位置
     * @param target    目标数组
     * @param targetIndex   目标数组放置的起始位置
     * @param length    复制的长度
     *
     * sourceIndex 参数为负。
     * targetIndex 参数为负。
     * length 参数为负。
     * sourceIndex + length 大于源数组的长度src.length。
     * targetIndex + length 大于目标数组的长度dest.length
     * 抛出IndexOutOfBoundsException
     */
    public static<T> T[] arrayReplace(T[] source, Integer sourceIndex, T[] target, Integer targetIndex, Integer length) {
        if (ArrayUtils.isEmpty(source)) {
            return target;
        }
        System.arraycopy(source, sourceIndex, target, targetIndex, length);
        return target;
    }

    @Test
    public void arrayCopy() {
        //ArrayList<Integer> list = new ArrayList<>();
        //for (int i = 0; i < 10; i++) {
        //    list.add(i);
        //}
        //Integer[] source = list.toArray(new Integer[]{});
        //Integer[] target = new Integer[]{11,12,13,14,15,16,17,18};
        //arrayReplace(source, 0, target, 1, 5);
        //System.out.println(JSONObject.toJSONString(target));

        String projectPath = SystemUtils.getProperty(SystemUtils.USER_DIR);
        System.out.println(projectPath);
    }


}
