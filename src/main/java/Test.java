import java.util.ArrayList;
import java.util.List;

public class Test {
    static boolean found = false;
    static String ans = "";

    public static void main(String[] args) {
        brutMultithread();
        brutforceSimple();
    }

    static void brutMultithread() {
        List<Character> alphabet = init();

        int n = 3;
        List<BrutForcer> brutForcers = new ArrayList<>();
        int THREADS_COUNT = 10;
        int size = (int) (Math.pow(alphabet.size(), n) / THREADS_COUNT);
        int i = 0;
        int count = 0;

        List<String> tmp = new ArrayList<>();
        for (Character a : alphabet) {
            for (Character b : alphabet) {
                for (Character c : alphabet) {
                    ++i;
                    if(i % size == 0) {
                        brutForcers.add(new BrutForcer(tmp, count++));
                        tmp = new ArrayList<>();
                    }

                    tmp.add(String.valueOf(a) + String.valueOf(b) + String.valueOf(c));
                }
            }
        }

        if(tmp.size() != 0) {
            brutForcers.add(new BrutForcer(tmp, count));
        }

        for (BrutForcer brutForcer : brutForcers) {
            new Thread(brutForcer).run();
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