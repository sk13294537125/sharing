package com.sharing.cn.utils;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * emoji Util
 */
public class EmojiUtils{


    @Test
    public void toAliases() {
        String str = "Here is a boy: :boy|type_6:!";
        System.out.println("to aliases 之后：");
        System.out.println(toAliases(str, null));
        System.out.println(toAliases(str, EmojiParser.FitzpatrickAction.REMOVE));
        System.out.println(toAliases(str, EmojiParser.FitzpatrickAction.IGNORE));

    }

    /**
     *
     * 转换为字符
     * @param input 转换字符
     * @param fitzpatrickAction 转换类型
     * @return
     */
    public static String toAliases(String input, EmojiParser.FitzpatrickAction fitzpatrickAction){
        if(StringUtils.isEmpty(input)){
            return null;
        }
        return EmojiParser.parseToAliases(input, null == fitzpatrickAction ? EmojiParser.FitzpatrickAction.PARSE : fitzpatrickAction);
    }

    /**
     * 转换 emoji 字符
     * @param input
     * @return
     */
    public static String toUnicode(String input){
        if(StringUtils.isEmpty(input)){
            return null;
        }
        return EmojiParser.parseToUnicode(input);
    }


}
