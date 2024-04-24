package com.sharing.cn.utils;

import lombok.Getter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class NameGeneratorUtil {
    /**
     * -- GETTER --
     *  获取唯一实例的途径
     *
     * @return 唯一实例
     */
    @Getter
    private static final NameGeneratorUtil instance = new NameGeneratorUtil(); // 唯一实例
    private final List<String> surname = new ArrayList<>(); // 姓氏
    private final List<String> words = new ArrayList<>(); // 单字
    private final List<String> asciis = new ArrayList<>(); // 字符


    public NameGeneratorUtil() {
        // TODO document why this constructor is empty
    }

    public static void main(String[] args) {
        String surnamePath = "/Users/workspase/sharing/sharing-web/src/main/resources/templates/txt/surname.txt";
        String wordPath = "/Users/workspase/sharing/sharing-web/src/main/resources/templates/txt/word.txt";
        String asciiPath = "/Users/workspase/sharing/sharing-web/src/main/resources/templates/txt/ascii.txt";

        NameGeneratorUtil nameGeneratorUtil = new NameGeneratorUtil();
        nameGeneratorUtil.initInstance(surnamePath, wordPath, asciiPath);


        String s = nameGeneratorUtil.nameOfPerson("梁", 3);
        System.out.println(s);

        String s1 = nameGeneratorUtil.nameOfOther("梁", "", 3);
        System.out.println(s1);

    }



    /**
     * * 初始化姓氏列表、单字列表和字符列表
     * <p>
     * 需要如下三个文件
     * <ul>
     * <li>surname.txt</li>
     * <li>words.txt</li>
     * <li>ascii.txt</li>
     * </ul>
     * </p>
     * @param surnamePath surname.txt 路径
     * @param wordPath word.txt 路径
     * @param asciiPath  ascii.txt 路径
     */
    public void initInstance(String surnamePath, String wordPath, String asciiPath) {
        try (FileReader fileReader = new FileReader(surnamePath)) {
            int tmp = 0; // 临时字符，保存每次读取的字符
            String str = ""; // 临时姓氏，保存姓氏
            while ((tmp = fileReader.read()) != -1) { // 当读取的字符是非结束符时
                if ((char)tmp != ',') { // 若读取字符不为空值
                    str += (char)tmp; // 保存读取的姓氏
                }
                else { // 遇到空值时保存姓氏
                    surname.add(str); // 姓氏列表添加读取的姓氏
                    str = ""; // 清空临时姓氏
                }
            }
        }
        catch (IOException e) { // 防止文件路径错误
            e.printStackTrace(); // 显示异常信息
        }
        try (FileReader fileReader = new FileReader(wordPath)) {
            int tmp = 0; // 临时字符，保存每次读取的字符
            while ((tmp = fileReader.read()) != -1) // 当读取的字符是非结束符时
                if ((char) tmp != ' ') // 若读取字符不为空值
                    this.words.add(""+(char) tmp); // 单字列表添加读取的姓氏
        }
        catch (IOException e) { // 防止文件路径错误
            e.printStackTrace(); // 显示异常信息
        }
        try (FileReader fileReader = new FileReader(asciiPath)) {
            int tmp = 0; // 临时字符，保存每次读取的字符
            while ((tmp = fileReader.read()) != -1) // 当读取的字符是非结束符时
                asciis.add(""+(char) tmp); // 字符列表添加读取的姓氏
        }
        catch (IOException e) { // 防止文件路径错误
            e.printStackTrace(); // 显示异常信息
        }
    }

    /**
     * 人名生成器
     * @param sur_name 姓氏（若长度超过2，则返回空值）
     * @param length 长度（若长度不在2~4范围内，则设置随机长度）
     * @return 返回姓名
     */
    public String nameOfPerson(String sur_name, int length) {
        if (sur_name.length() > 2) return null; // 若长度超过2，则返回空值
        Random random = new Random(); // 随机生成器实例
        String name = ""; // 临时名字，保存名字
        if (length < 2 || length > 4) // 若长度不在2~4范围内
            length = random.nextInt(2)+2; // 设置随机长度
        length -= sur_name.length() == 0 ? 0 : 1; // 如果姓氏不为空，则长度-1
        for (int i = 0; i < length; i++) {
            if (i == 0 && sur_name.length() == 0) { // 当i等于1时生成姓氏
                name += surname.get(random.nextInt(surname.size())); // 姓氏随机生成
            }
            else { // 当i不等于1时名字生成
                name += words.get(random.nextInt(surname.size())); // 名字随机生成
            }
        }
        return sur_name+name; // 返回姓氏+名字
    }

    /**
     * 万能取名器
     * @param prefix_name 前缀
     * @param suffix_name 后缀
     * @param length 长度
     * @return 名字
     */
    public String nameOfOther(String prefix_name, String suffix_name, int length) {
        if (length < prefix_name.length()+suffix_name.length() || length == 0) return null; // 若前缀和后缀的长度总和小于长度，则返回空值
        Random random = new Random(); // 随机生成器实例
        String name = ""; // 临时名字，保存名字
        length -= prefix_name.length()+suffix_name.length(); // 名字剩余长度等于长度-前缀长度-后缀长度
        for (int i = 0; i < length; i++) {
            name += words.get(random.nextInt(surname.size())); // 名字随机生成
        }
        return prefix_name+name+suffix_name; // 返回前缀+名字+后缀
    }

    /**
     * 随机字符串
     * @param length 字符串长度
     * @param condition 是否有字符为首的条件
     * @return 字符
     */
    public String getRandomPassword(int length, boolean condition) {
        Random random = new Random(); // 随机生成器实例
        String words= ""; // 临时名字，保存名字
        do {
            words= ""; // 清空字符串
            words+= asciis.get(random.nextInt(asciis.size())); // 首字符随机生成
        } while (condition && !(words.charAt(0) <= 'z'&& words.charAt(0) >= 'a') && !(words.charAt(0) <= 'Z'&& words.charAt(0) >= 'A')); // 当条件为真，且首字符为字母则退出循环
        for (int i = 0; i < length-1; i++) {
            words+= asciis.get(random.nextInt(asciis.size()));  // 剩余字符随机生成
        }
        return words; // 返回字符
    }

}
