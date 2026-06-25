package com.sharing.cn.common.encrypt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileClass {

    public static void main(String[] args) {
        String directoryPath = "/Users/workspase-ykt/bmac-admin/bmac-web-app/target/lib";

        File directory = new File(directoryPath);

        // 检查目录是否存在
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("目录不存在或不是有效目录！");
            return;
        }

        // 读取目录下的所有文件和子目录
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("目录为空！");
            return;
        }
        List<String> list = new ArrayList<>();
        System.out.println("目录 " + directoryPath + " 下的所有文件：");
        for (File file : files) {
            if (file.isFile()) {
                String name = file.getName();
                int lastDashIndex = name.lastIndexOf("-");
                if (lastDashIndex != -1) {
                    String prefix = name.substring(0, lastDashIndex);
                    String suffix = name.substring(lastDashIndex + 1);
                    list.add(suffix);
                    System.out.println(prefix);
                } else {
                    System.out.println(name + "    (无版本信息)");
                }

            } else if (file.isDirectory()) {
                System.out.println("目录: " + file.getName());
            }
        }

        for (String s : list) {
            if (s.endsWith(".jar")) {
                s = s.substring(0, s.length() - 4);
            }
            System.out.println(s);
        }


    }


}
