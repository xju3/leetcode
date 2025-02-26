import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    Map<Character, Node> children = new HashMap<>();
    int start;
    int end;
    Node suffixLink; // 后缀链接 (用于 Ukkonen 算法优化，此处简化未使用)
    List<Integer> stringIndices = new ArrayList<>(); // 存储到达此节点的字符串的索引

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int length() {
        return end - start;
    }
}

public class SuffixTree {
    Node root;
    String text;

    public SuffixTree(String text) {
        this.text = text;
        root = new Node(-1, -1);
        for (int i = 0; i < text.length(); i++) {
            insert(text.substring(i), i);
        }
    }

    private void insert(String s, int index) {
        Node current = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node(i + index, text.length()));
            }
            current = current.children.get(c);
        }
        current.stringIndices.add(index); // 记录字符串索引
    }

    // ... (后续方法见下文)

    public String longestCommonSubstring(String s1, String s2) {
        String combined = s1 + "#" + s2;
        SuffixTree st = new SuffixTree(combined);
        return findLCS(st.root, s1.length());
    }

    private String findLCS(Node node, int s1Length) {
        String lcs = "";
        if (node == null || node.children.isEmpty())
            return lcs;

        for (Node child : node.children.values()) {
            String currentLCS = findLCS(child, s1Length);
            if (currentLCS.length() > lcs.length()) {
                lcs = currentLCS;
            }
        }

        if (node.start != -1) { // 排除根节点
            boolean hasS1 = false;
            boolean hasS2 = false;
            for (int index : node.stringIndices) {
                if (index < s1Length)
                    hasS1 = true;
                else
                    hasS2 = true;
            }
            if (hasS1 && hasS2) {
                int len = Math.min(node.length(), Math.min(s1Length - node.start, text.length() - node.start));

                if (len > lcs.length()) {
                    lcs = text.substring(node.start, node.start + len);
                }
            }
        }
        return lcs;
    }

    public String longestRepeatingSubstring() {
        return findLRS(root);
    }

    private String findLRS(Node node) {
        String lrs = "";
        if (node == null || node.children.isEmpty())
            return lrs;

        for (Node child : node.children.values()) {
            String currentLRS = findLRS(child);
            if (currentLRS.length() > lrs.length()) {
                lrs = currentLRS;
            }
        }

        if (node.children.size() >= 2 && node.start != -1) {
            if (node.length() > lrs.length()) {
                lrs = text.substring(node.start, node.end);
            }
        }
        return lrs;
    }

    public boolean contains(String pattern) {
        Node current = root;
        for (char c : pattern.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixTree st = new SuffixTree(text);

        System.out.println("Contains 'ana': " + st.contains("ana")); // true
        System.out.println("Contains 'ban': " + st.contains("ban")); // true
        System.out.println("Contains 'apple': " + st.contains("apple")); // false

        System.out.println("Longest Repeating Substring: " + st.longestRepeatingSubstring()); // ana

        String s1 = "ababc";
        String s2 = "babca";
        SuffixTree st2 = new SuffixTree(s1 + "#" + s2);
        System.out.println(
                "Longest Common Substring of " + s1 + " and " + s2 + ": " + st2.longestCommonSubstring(s1, s2)); // bab
    }
}
