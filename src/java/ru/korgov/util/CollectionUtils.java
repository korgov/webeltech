package ru.korgov.util;

import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class CollectionUtils {
    public static <K, V> Map<K, V> zipMap(final List<? extends K> keys, final List<? extends V> values){
        if(keys.size() != values.size()){
            throw new RuntimeException("List's size must be equals!");
        }
        final int size = keys.size();
        final Map<K, V> out = new HashMap<K, V>();
        for(int i = 0; i < size; ++i){
            out.put(keys.get(i), values.get(i));
        }
        return out;
    }

    public static <T> List<T> list(final T... items){
        return Arrays.asList(items);
    }

    public static <T> List<T> emptyIfNull(final List<T> list){
        return list == null ? Collections.<T>emptyList(): list;
    }

    public static <T> Set<T> emptyIfNull(final Set<T> set){
        return set == null ? Collections.<T>emptySet(): set;
    }

    public static <K, V> Map<K, V> emptyIfNull(final Map<K, V> map){
        return map == null ? Collections.<K, V>emptyMap(): map;
    }

    public static <T> void addIfNotNull(final Collection<T> collection, final T item){
        if(item != null){
            collection.add(item);
        }
    }

    public static <K, V> void addIfNotNull(final Map<K, V> map, final K key, final V value){
        if(value != null){
            map.put(key, value);
        }
    }
    
    public static <K, V> void appendToMultiMap(final Map<K, List<V>> mm, final K key, final V value){
        List<V> values = mm.get(key);
        if(values == null){
            values = new ArrayList<V>();
            mm.put(key, values);
        }
        values.add(value);
    }

    @Nullable
    public static <T> T firstOrNull(final Collection<T> c){
        final Iterator<T> iterator = c.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }
    
}
