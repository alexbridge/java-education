package util;

import java.util.Arrays;
import java.util.List;

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

    }
}
