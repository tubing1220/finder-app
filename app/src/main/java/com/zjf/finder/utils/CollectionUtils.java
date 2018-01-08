
package com.zjf.finder.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CollectionUtils {
    public CollectionUtils() {
    }

    public static <T> List<T> intersect(List<T> list1, List<T> list2) {
        ArrayList list = new ArrayList(Arrays.asList(new Object[list1.size()]));
        Collections.copy(list, list1);
        list.retainAll(list2);
        return list;
    }

    public static <T> List<T> asList(T... arr) {
        return arr == null ? null : new ArrayList(Arrays.asList(arr));
    }

    public static <T> List<T> union(List<T> list1, List<T> list2) {
        ArrayList list = new ArrayList(Arrays.asList(new Object[list1.size()]));
        Collections.copy(list, list1);
        list.removeAll(list2);
        list.addAll(list2);
        return list;
    }

    public static <T> List<T> diff(List<T> list1, List<T> list2) {
        List unionList = union(list1, list2);
        List intersectList = intersect(list1, list2);
        unionList.removeAll(intersectList);
        return unionList;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static boolean empty(Object object) {
        boolean ret = false;
        if (object == null) {
            ret = true;
        } else if (object.getClass().isArray()) {
            ret = Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            ret = ((Collection) object).isEmpty();
        } else if (object instanceof String) {
            ret = ((String) object).isEmpty();
        }

        return ret;
    }

    public static <T> boolean inArray(T t, List<T> list) {
        return t != null && !isEmpty(list) ? list.contains(t) : false;
    }

    public static <T> void addAll(List<T> list, T... ts) {
        List newList = Arrays.asList(ts);
        list.addAll(newList);
    }

    public static <T> int size(List<T> list) {
        return list == null ? 0 : list.size();
    }


    public static boolean isValid(List list, int position) {
        return !CollectionUtils.isEmpty(list) && position >= 0 && position < list.size();
    }
}
