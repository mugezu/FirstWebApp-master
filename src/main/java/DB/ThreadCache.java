package DB;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Роман on 12.06.2017.
 */
public class ThreadCache {
    private static ThreadCache singliton;

    private static InheritableThreadLocal<Map<String, Object>> cache;

    private ThreadCache() {
        cache = new InheritableThreadLocal<>();
        cache.set(new ConcurrentHashMap<>());
    }

    public static synchronized ThreadCache getInstance() {
        if (singliton == null) {
            singliton = new ThreadCache();
        }
        return singliton;
    }

    public Object getCache(String key) {
        return singliton.cache.get().get(key);
    }

    public void setCache(String key, Object value) {
        System.out.println(singliton.cache.get());
        singliton.cache.get().put(key, value);
        System.out.println(singliton.cache.get());
    }
}
