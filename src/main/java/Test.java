import java.util.ArrayList;
import java.util.List;

public class Test {
    static volatile boolean found = false;
    static String ans = "";

    public static void main(String[] args) {
        brutMultithread();
        brutforceSimple();
    }

    static void brutMultithread() {
        List<Character> alphabet = init();

        int size = 1000;
        int count = 1;
        int i = 1;

        List<String> tmp = new ArrayList<>();
        for (Character a : alphabet) {
            for (Character b : alphabet) {
                for (Character c : alphabet) {
                    if(count++ < size) {
                        tmp.add(String.valueOf(a) + String.valueOf(b) + String.valueOf(c));
                    } else {
                        count = 0;
                        new Thread(new BrutForcer(tmp, i++)).run();
                        tmp = new ArrayList<>();
                    }
                }
            }
        }

        if(tmp.size() != 0) {
            new Thread(new BrutForcer(tmp, i)).run();
        }
    }

    static void brutforceSimple() {
        List<Character> alphabet = init();

        for (Character a : alphabet) {
            for (Character b : alphabet) {
                for (Character c : alphabet) {
                    String tmp = String.valueOf(a) + String.valueOf(b) + String.valueOf(c);
                    if(check(tmp)) {
                        System.out.println("Password = " + tmp + " found by simple brutforce");
                        return;
                    }
                }
            }
        }
    }

    private static List<Character> init() {
        List<Character> alphabet = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++) {
            alphabet.add(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            alphabet.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            alphabet.add(i);
        }
        return alphabet;
    }

    static class BrutForcer implements Runnable {
        private int id;
        private List<String> set;

        public BrutForcer(List<String> set, int id) {
            this.set = set;
            this.id = id;
        }

        @Override
        public void run() {
            for (String s : set) {
                if(found) {
                    System.out.println("Password already found!");
                    break;
                }

                if(check(s)) {
                    System.out.println("Password = " + s + " found by worker with id = " + id);
                    found = true;
                    ans = s;
                }
            }
        }
    }

    static boolean check(String s) {
        return s.equals("Sev");
    }
}