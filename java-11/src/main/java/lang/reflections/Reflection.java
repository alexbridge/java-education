package lang.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * Is not public, we can not instantiate it
         */
        // DefaultVisibility def = new DefaultVisibility();

        Class c = Class.forName("lang.reflections.dto.DefaultVisibility");

        System.out.println(c.getName());

        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();

        Arrays.stream(fields)
                .map(Field::getName)
                .forEach(System.out::println);

        Arrays.stream(methods)
                .map(Method::getName)
                .forEach(System.out::println);
    }
}
