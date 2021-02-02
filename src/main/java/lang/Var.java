package lang;

import java.util.ArrayList;
import java.util.List;

public class Var {

    static class X {
        public void print(List<Integer> lst) {
            System.out.println("X list " + lst);
        }

        public List<Integer> getCollection() {
            return new ArrayList<Integer>();
        }
    }

    static class Y extends X {
        public void print(List<Integer> lst) {
            System.out.println("Y list " + lst);
        }

        public ArrayList<Integer> getCollection() {
            return new ArrayList<Integer>();
        }
    }

    public static void main(String[] args) {
        X x = new Y();
        Y y = new Y();
        //var list = new ArrayList<>(); // ArrayList<Object>
        var list = new ArrayList<Integer>();
        list.add(1);
        x.print(list);
        y.print(list);
    }
}
