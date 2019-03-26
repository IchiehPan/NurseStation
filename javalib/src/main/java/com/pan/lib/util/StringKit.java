package com.pan.lib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author panyijie
 *
 */
public class StringKit {
    /**
     * �Ƿ�Ϊ�ջ���ַ���
     */
    public static boolean isBlank(String str) {
        boolean result = false;
        if ((str != null) && (str.equals(""))) {
            result = true;
        }
        return result;
    }

    /**
     * �Ƿ�Ϊ��
     */
    public static boolean isNull(String str) {
        return null == str;
    }

    /**
     * �Ƿ���Ч
     */
    public static boolean isValid(String str) {
        return (!isNull(str)) && (!isBlank(str));
    }

    /**
     * �Ƿ�ת��
     */
    public static boolean isBaleful(String str) {
        String[] errorArray = { "'", "\"", "\\", "/", ",", ":", "?", "<", ">" };

        for (int i = 0; i < errorArray.length; i++) {
            if (str.indexOf(errorArray[i]) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * �Ƿ�׳
     */
    public static boolean isVerified(String str) {
        if (!isValid(str)) {
            return false;
        }

        return !isBaleful(str);
    }

    /**
     * ��֤�ַ���
     */
    public static String validStr(String str) {
        if (isValid(str)) {
            return str;
        }
        return "";
    }

    /**
     * Array -> String
     */
    public static String array2String(String[] array, String regex) {
        StringBuffer sb = new StringBuffer();
        for (String str : array) {
            if (sb.length() > 0) {
                sb.append(regex);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * ƴ���ַ���
     * 
     * @param suffix��׺�ַ���
     */
    public static String substring(String str, int length, String suffix) {
        if ((null == str) || (str.trim().length() == 0)) {
            return "";
        }

        if (str.length() <= length) {
            return str;
        }

        String result = str.substring(0, length) + suffix;
        return result;
    }

    /**
     * Bit��ʽƴ���ַ���
     * 
     * @param suffix��׺�ַ���
     */
    public static String substringBit(String str, int length, String suffix) {
        if ((null == str) || (str.trim().length() == 0)) {
            return "";
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            int n = 0;
            int subLen = 2;
            for (; (subLen < bytes.length) && (n < length); subLen += 2) {
                n++;
                if (bytes[subLen] != 0) {
                    n++;
                    if (n > length) {
                        break;
                    }
                }
            }
            if (subLen == bytes.length) {
                return str;
            }
            return new String(bytes, 0, subLen, "UTF-8") + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String substringHtmlBit(String str, int length, String suffix) {
        String result = "";
        if ((str == null) || (str.trim().length() == 0)) {
            return "";
        }

        String regex = "<.[^>]*>";
        Pattern pattern = Pattern.compile(regex, 2);
        Matcher matcher = pattern.matcher(str);
        List<String> htmlTagList = new ArrayList<String>();
        while (matcher.find()) {
            htmlTagList.add(matcher.group(0));
        }
        String temp = matcher.replaceAll("");
        try {
            byte[] bytes = temp.getBytes("Unicode");
            int n = 0;
            int subLen = 2;
            for (; (subLen < bytes.length) && (n < length); subLen += 2) {
                n++;
                if (bytes[subLen] != 0) {
                    n++;
                    if (n > length) {
                        break;
                    }
                }
            }
            if (subLen == bytes.length)
                result = temp;
            else {
                result = new String(bytes, 0, subLen, "UTF-8") + suffix;
            }
            String htmlStr = "";
            for (int i = 0; i < htmlTagList.size() / 2; i++) {
                htmlStr = htmlStr + (String) htmlTagList.get(i);
            }
            result = htmlStr + result;
            htmlStr = "";
            for (int i = htmlTagList.size(); i > htmlTagList.size() / 2; i--) {
                htmlStr = (String) htmlTagList.get(i - 1) + htmlStr;
            }
            result = result + htmlStr;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String substringHtmlBit2(String str, int length, String suffix) {
        return substringBit(filterHtmlTags(str), length, suffix);
    }

    public static int stringBitLength(String str) {
        if ((str == null) || (str.trim().length() == 0)) {
            return 0;
        }

        int length = 0;
        try {
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 2; i < bytes.length; i += 2) {
                length++;
                if (bytes[i] != 0)
                    length++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return length;
    }

    public static int stringHtmlBitLength(String str) {
        if ((str == null) || (str.trim().length() == 0)) {
            return 0;
        }

        Pattern pattern = Pattern.compile("<.[^>]*>", 2);
        Matcher matcher = pattern.matcher(str);
        String temp = matcher.replaceAll("");

        int length = 0;
        try {
            byte[] bytes = temp.getBytes("Unicode");
            for (int i = 2; i < bytes.length; i += 2) {
                length++;
                if (bytes[i] != 0)
                    length++;
            }
        } catch (Exception e) {
            return 0;
        }
        return length;
    }

    /**
     * ����Tag��ǩ
     * 
     * @param strHTML����
     */
    public static String filterHtmlTags(String str) {
        return str.replaceAll("<.*?>", "");
    }

    public static String toSBC(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ')
                c[i] = '��';
            else if (c[i] < '') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '��')
                c[i] = ' ';
            else if ((c[i] > 65280) && (c[i] < 65375)) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * �ַ�����д��ĸ��д
     */
    public static String upperFirstChar(String input) {
        String result = "";
        if ((input == null) || (input.trim().length() == 0)) {
            return "";
        }

        String firstStr = input.substring(0, 1);
        String restStr = input.substring(1);
        result = new StringBuffer(firstStr.toUpperCase()).append(restStr).toString();

        return result;
    }

    /**
     * �ַ�����д��ĸСд
     */
    public static String lowerFirstChar(String input) {
        String result = "";
        if ((input == null) || (input.trim().length() == 0)) {
            return "";
        }

        String firstStr = input.substring(0, 1);
        String restStr = input.substring(1);
        result = new StringBuffer(firstStr.toLowerCase()).append(restStr).toString();

        return result;
    }

    /**
     * �ж��ַ����Ƿ�ƥ��������ʽ
     */
    public static boolean isMatch(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * �ж��б�����ַ����Ƿ�ƥ��������ʽ
     */
    public static boolean isMatch(List<String> list, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        for (String str : list) {
            matcher = pattern.matcher(str);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }
}
