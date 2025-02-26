import java.util.HashMap;
import java.util.Map;

class Node {
    Map<Character, Node> children = new HashMap<>();
    int start;
    int end;
    Node suffixLink;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int length() {
        return end - start;
    }
}

public class UkkonenSuffixTree {
    private String text;
    private Node root;
    private Node activeNode;
    private int activeEdge;
    private int activeLength;
    private int remainingSuffixCount;
    private int end;

    public UkkonenSuffixTree(String text) {
        this.text = text;
        this.root = new Node(-1, -1);
        this.activeNode = root;
        this.activeEdge = -1;
        this.activeLength = 0;
        this.remainingSuffixCount = 0;
        this.end = -1;

        for (int i = 0; i < text.length(); i++) {
            extend(i);
        }
    }

    private void extend(int pos) {
        end++;
        remainingSuffixCount++;
        Node lastNewNode = null;

        while (remainingSuffixCount > 0) {
            if (activeLength == 0) {
                activeEdge = pos;
            }

            if (!activeNode.children.containsKey(text.charAt(activeEdge))) {
                Node newNode = new Node(pos, Integer.MAX_VALUE);
                activeNode.children.put(text.charAt(activeEdge), newNode);
                if (lastNewNode != null) {
                    lastNewNode.suffixLink = activeNode;
                }
                lastNewNode = null;
            } else {
                Node nextNode = activeNode.children.get(text.charAt(activeEdge));
                if (activeLength >= nextNode.length()) {
                    activeLength -= nextNode.length();
                    activeEdge += nextNode.length();
                    activeNode = nextNode;
                    continue;
                }

                if (text.charAt(nextNode.start + activeLength) == text.charAt(pos)) {
                    if (lastNewNode != null && activeNode != root) {
                        lastNewNode.suffixLink = activeNode;
                    }
                    activeLength++;
                    break;
                }

                Node splitNode = new Node(nextNode.start, nextNode.start + activeLength);
                Node newNode = new Node(pos, Integer.MAX_VALUE);
                splitNode.children.put(text.charAt(pos), newNode);
                splitNode.children.put(text.charAt(nextNode.start + activeLength), nextNode);
                activeNode.children.put(text.charAt(activeEdge), splitNode);
                nextNode.start += activeLength;

                if (lastNewNode != null) {
                    lastNewNode.suffixLink = splitNode;
                }
                lastNewNode = splitNode;
            }

            remainingSuffixCount--;
            if (activeNode == root && activeLength > 0) {
                activeLength--;
                activeEdge = pos - remainingSuffixCount + 1;
            } else if (activeNode != root) {
                activeNode = (activeNode.suffixLink != null) ? activeNode.suffixLink : root;
            }
        }
    }

    // 将所有叶子节点的end设置为text.length()
    public void setEndForAllLeafs() {
        setEndForAllLeafs(root);
    }

    private void setEndForAllLeafs(Node node) {
        if (node == null) return;
        if (node.children.isEmpty()) {
            node.end = text.length();
        } else {
            for(Node child : node.children.values()) {
                setEndForAllLeafs(child);
            }
        }
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
        UkkonenSuffixTree st = new UkkonenSuffixTree(text);
        st.setEndForAllLeafs(); // 非常重要，必须调用此方法
        System.out.println("Contains 'ana': " + st.contains("ana")); // true
        System.out.println("Contains 'ban': " + st.contains("ban")); // true
        System.out.println("Contains 'apple': " + st.contains("apple")); // false

        text = "ababc";
        st = new UkkonenSuffixTree(text);
        st.setEndForAllLeafs();
        System.out.println("Contains 'ab': " + st.contains("ab")); // true
        System.out.println("Contains 'abc': " + st.contains("abc")); // true
        System.out.println("Contains 'ba': " + st.contains("ba")); // true
    }
}
