/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */

package com.thinkgem.jeesite.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author ThinkGem
 * @author Elerning team
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 转换为字节数组
     *
     * @param str 原字符串
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为Boolean类型
     * 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is
     * returned.
     */
    public static Boolean toBoolean(final Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    /**
     * 字节数组转换为字符串
     *
     * @param bytes 字节数组
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 如果对象为空，则使用defaultVal值
     * see: ObjectUtils.toString(obj, defaultVal)
     *
     * @param obj 对象
     * @param defaultVal 缺省默认值
     * @return
     */
    public static String toString(final Object obj, final String defaultVal) {
        return obj == null ? defaultVal : obj.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        // String regEx = "<.+?>"; //modify 2018/03/31
        String regEx = "<[^#]+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html 要过滤内容
     * @return
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param txt 要过滤内容
     * @return
     */
    public static String toHtml(String txt) {
        if (txt == null) {
            return "";
        }
        return replace(replace(StringUtils.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
    }

    /**
     * 替换掉任意回车字符 \n \r .
     *
     * @param html 要过滤内容
     * @return
     */
    public static String removeReturnChar(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("[\\n\\r]", "");
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str 目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Abbr 2 string.
     *
     * @param param the param
     * @param length the length
     * @return the string
     */
    public static String abbr2(String param, int length) {
        if (param == null) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        int n = 0;
        char temp;
        boolean isCode = false; // 是不是HTML代码
        boolean isHtml = false; // 是不是HTML特殊字符,如&nbsp;
        for (int i = 0; i < param.length(); i++) {
            temp = param.charAt(i);
            if (temp == '<') {
                isCode = true;
            } else if (temp == '&') {
                isHtml = true;
            } else if (temp == '>' && isCode) {
                n = n - 1;
                isCode = false;
            } else if (temp == ';' && isHtml) {
                isHtml = false;
            }
            try {
                if (!isCode && !isHtml) {
                    n += String.valueOf(temp).getBytes("GBK").length;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (n <= length - 3) {
                result.append(temp);
            } else {
                result.append("...");
                break;
            }
        }
        // 取出截取字符串中的HTML标记
        String tempResult = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
        // 去掉不需要结素标记的HTML标记
        // CHECKSTYLE:OFF
        tempResult = tempResult.replaceAll(
            "</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
            "");
        // CHECKSTYLE:ON
        // 去掉成对的HTML标记
        tempResult = tempResult.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
        // 用正则表达式取出标记
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
        Matcher m = p.matcher(tempResult);
        List<String> endHtml = new ArrayList<>();
        while (m.find()) {
            endHtml.add(m.group(1));
        }
        // 补全不成对的HTML标记
        for (int i = endHtml.size() - 1; i >= 0; i--) {
            result.append("</");
            result.append(endHtml.get(i));
            result.append(">");
        }
        return result.toString();
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    // /**
    // * 获得i18n字符串
    // */
    // public static String getMessage(String code, Object[] args) {
    // LocaleResolver localLocaleResolver = (LocaleResolver)
    // SpringContextHolder.getBean(LocaleResolver.class);
    // HttpServletRequest request =
    // ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    // Locale localLocale = localLocaleResolver.resolveLocale(request);
    // return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
    // }
    //
    // /**
    // * 获得用户远程地址
    // */
    // public static String getRemoteAddr(HttpServletRequest request){
    // String remoteAddr = request.getHeader("X-Real-IP");
    // if (isNotBlank(remoteAddr)) {
    // remoteAddr = request.getHeader("X-Forwarded-For");
    // }else if (isNotBlank(remoteAddr)) {
    // remoteAddr = request.getHeader("Proxy-Client-IP");
    // }else if (isNotBlank(remoteAddr)) {
    // remoteAddr = request.getHeader("WL-Proxy-Client-IP");
    // }
    // return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    // }

    /**
     * 驼峰命名法工具
     *
     * @return
     *         toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return
     *         toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return
     *         toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串
     *        例如：row.user.id
     *        返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    /**
     * 用定界符替换匹配到的定界符前后的空白字符,包括定界符，及连续两个定界符间没内容时也替换掉.
     *
     * @return
     */
    public static String replaceBlankArroundDelimiter(String source, String delimiter) {
        if (isBlank(source)) {
            return ""; // 合适?还是直接 reutrn source?
        }
        // 匹配 ## 前后的空白字符和连续的 ## (因为两个 ## 之间空白时当作无效数据)
        Pattern p = Pattern.compile("[#\\s\\t\\r\\n]*##[#\\s\\t\\r\\n]*");
        Matcher m = p.matcher(source);
        source = m.replaceAll(delimiter);

        return source;
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     *
     * @param c 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */
    public static boolean isChineseChar(char c) {
        // CHECKSTYLE:OFF
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
        // CHECKSTYLE:ON
    }

    /**
     * 判断是否为可扩展字符
     *
     * @param ch 等检测字符
     * @return
     */
    public static boolean isExtensibleChar(char ch) {
        String regEx = "[0-9A-Za-z_-]"; // 可扩展的字符列表（TODO:目前先只列出英文、数字、下划线、连字符这几种字符，后面需要别的字符时再追加）
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(String.valueOf(ch));
        if (matcher.find()) {
            return true;
        }
        // if(Character.isWhitespace(ch)|| isChineseChar(ch)){
        // return false;
        // }
        return false; // 其它情况全部返回false
    }

    /**
     * 判断是否不是可扩展字符
     *
     * @param ch 等检测字符
     * @return
     */
    public static boolean isNotExtensibleChar(char ch) {
        return !isExtensibleChar(ch);
    }

    //
    // /**
    // * test.
    // *
    // * @param args
    // */
    // public static void main(String[] args) {
    // String str = "abc ## efg ## ## hi jk ##"; // result: "abc##efg##hi jk##"
    //
    // System.out.println(replaceBlankArroundDelimiter(str, "##"));
    // }

    /**
     * Html 转码.
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml10(xml);
    }

    /**
     * Xml 解码.
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

}
