package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Lists {

    public static void main(String[] args) {
        List<Integer> list = List.of();
        System.out.println(list);

        String[] arr = {"Tea","Cake"};
        List<String> texts = Arrays.asList(arr);

        arr[0] = "Cofee";
        System.out.println(Arrays.toString(arr));

        texts.set(0, "Cofee");
        System.out.println(texts);

        Set<String> s = Set.of("mickey", "minnie");
        List<String> x = new ArrayList<>(s);
        s.forEach(s1 -> System.out.println(s1));
        x.forEach(x1 -> System.out.println(x1));

    }
}
