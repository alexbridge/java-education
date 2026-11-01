package util;

import java.util.HashMap;
import java.util.Map;

public class Maps {

    public static void main(String[] args) {
        var map = new HashMap<Integer, Integer>(10);
        for (int i = 1; i <= 10; i++) {
            map.put(i, i * i);
        }

        System.out.println(map.get(4));

        Map m = new HashMap();
        m.put(123, "456");
        m.put("abc", "def");
        System.out.println(m);
        System.out.println(m.containsValue("def"));
        System.out.println(m.containsKey("abc"));

        var mergeMap = new HashMap<Integer, Integer>();
        mergeMap.put(1, 10);
        mergeMap.put(2, 20);
        mergeMap.put(3, null);
        mergeMap.merge(1, 3, (oldV, newV) -> oldV + newV);
        mergeMap.merge(3, 3, Integer::sum);
        System.out.println(mergeMap);

        mergeMap.compute(3, Integer::sum);
        System.out.println(mergeMap);

        Map<String, String> somes = new HashMap<>(){{ put("1", "2"); }};

        Object somesInterm = (Object) somes;

        HashMap<String, String> somes2 = (HashMap<String, String>) somesInterm;
        System.out.println(somes2);
    }
}
