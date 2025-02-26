import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuffixAutomaton {

    private static class State {
        int len;
        State link;
        Map<Character, State> next = new HashMap<>();
        List<Integer> endpos = new ArrayList<>(); // 记录状态对应的endpos集合

        public State(int len) {
            this.len = len;
        }
    }

    private State root;
    private State last;

    public SuffixAutomaton(String s) {
        root = new State(0);
        last = root;

        for (int i = 0; i < s.length(); i++) {
            extend(s.charAt(i), i);
        }
        calculateEndpos(s.length()); // 计算endpos集合
    }

    private void extend(char c, int pos) {
        State cur = new State(last.len + 1);
        cur.endpos.add(pos); // 新状态的endpos包含当前位置
        State p = last;

        while (p != null && !p.next.containsKey(c)) {
            p.next.put(c, cur);
            p = p.link;
        }

        if (p == null) {
            cur.link = root;
        } else {
            State q = p.next.get(c);
            if (q.len == p.len + 1) {
                cur.link = q;
            } else {
                State clone = new State(p.len + 1);
                clone.next.putAll(q.next);
                clone.link = q.link;
                clone.endpos.addAll(q.endpos); // 克隆状态继承原状态的endpos
                while (p != null && p.next.get(c) == q) {
                    p.next.put(c, clone);
                    p = p.link;
                }
                q.link = clone;
            }
        }
        last = cur;
    }

    // 计算每个状态对应的endpos集合，用于后续应用
    private void calculateEndpos(int n) {
        List<State> states = new ArrayList<>();
        states.add(root);
        List<State> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            State u = queue.remove(0);
            for (State v : u.next.values()) {
                states.add(v);
                queue.add(v);
            }
        }

        states.sort((a, b) -> b.len - a.len); // 按len降序排序

        for (State u : states) {
            if (u.link != null) {
                u.link.endpos.addAll(u.endpos);
            }
        }
    }


    public boolean contains(String pattern) {
        State current = root;
        for (char c : pattern.toCharArray()) {
            if (!current.next.containsKey(c)) {
                return false;
            }
            current = current.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixAutomaton sam = new SuffixAutomaton(text);

        System.out.println("Contains 'ana': " + sam.contains("ana")); // true
        System.out.println("Contains 'ban': " + sam.contains("ban")); // true
        System.out.println("Contains 'apple': " + sam.contains("apple")); // false

        text = "abcabxabc";
        sam = new SuffixAutomaton(text);
        System.out.println("Contains 'abc': " + sam.contains("abc")); //true
        System.out.println("Contains 'abxa': " + sam.contains("abxa")); //true
    }
}
