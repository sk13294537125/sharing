package com.sharing.cn.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

    /**
     * 获取汉字串拼音首字母
     * @param chinese 汉字字符串
     * @return 拼音首字母缩写
     */
    public static String getFirstLetters(String chinese) {
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        char[] chars = chinese.toCharArray();
        for (char c : chars) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]")) { // 中文字符
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        pinyin.append(pinyinArray[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else if (Character.isLetter(c)) { // 非中文的字母字符
                pinyin.append(Character.toUpperCase(c));
            }
            // 其他字符（数字、标点等）忽略
        }
        return pinyin.toString();
    }

    public static void main(String[] args) {
        System.out.println(getFirstLetters("中华人民共和国")); // 输出: ZHRMGHG
        System.out.println(getFirstLetters("Java编程"));      // 输出: JABC
        System.out.println(getFirstLetters("123ABC测试"));   // 输出: ABCCS
        System.out.println(getFirstLetters("Hello世界")); // 输出: HESJ

    }
}