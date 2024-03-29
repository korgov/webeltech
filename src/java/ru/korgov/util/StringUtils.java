package ru.korgov.util;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/19/11
 */
public class StringUtils {
    public static String nvl(final String str, final String defValue){
        return str == null ? defValue : str;
    }

    public static String emptyIfNull(final String str){
        return nvl(str, "");
    }

    public static String nullIfEmpty(final String str){
        return str == null ? null : str.isEmpty() ? null : str;
    }

    public static boolean isEmpty(final String str){
        return str == null || str.trim().isEmpty();
    }
    
    public static String join(final Iterable<String> strs, final String sep){
        final StringBuilder sb = new StringBuilder();
        for(final String str : strs){
            sb.append(sep).append(str);
        }
        return sb.length() == 0 ? "" : sb.substring(sep.length());
    }
    public static String join(final String[] strs, final String sep){
        return join(CollectionUtils.list(strs), sep);
    }
}
