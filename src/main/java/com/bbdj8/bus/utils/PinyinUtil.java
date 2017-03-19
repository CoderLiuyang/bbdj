package com.bbdj8.bus.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类，获取汉字拼音的大写首字母
 * @author liwenjun
 *
 */
public class PinyinUtil {
	
	/**
	 * 完整的判断中文汉字和符号
	 * @param str
	 * @return
	 */
    public static boolean isChinese(String str) {
    	for (int i = 0; i < str.length(); i++) {
    		if (str.substring(i,   i+1).matches("[\\u4e00-\\u9fa5]+")) {
				return true;
			}
		}
    	return false;
    }
    
    /**
     * 保留字符串中的中文字符
     * @param str
     * @return
     */
    public static String stringToZH(String str){
    	StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i)+"").getBytes().length>1) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
    
    /**  
     * 获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音首字母  
     */   
    public static String getFirstSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                    if (arr[i] > 128) {   
                            try {   
                                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                    if (temp != null) {   
                                            pybf.append(temp[0].charAt(0));   
                                    }   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString().replaceAll("\\W", "").trim();   
    }  

}
