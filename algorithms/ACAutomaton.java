import java.util.*;

public class ACAutomaton {
    // 字典树节点
    private static class TrieNode {
        Map<Character, TrieNode> children;
        TrieNode fail;           // 失败指针
        boolean isEndOfWord;     // 是否是某个模式串的结尾
        String word;             // 如果是结尾，存储完整的词
        
        TrieNode() {
            children = new HashMap<>();
            fail = null;
            isEndOfWord = false;
            word = null;
        }
    }
    
    private TrieNode root;
    
    public ACAutomaton() {
        root = new TrieNode();
    }
    
    // 构建字典树
    public void addPattern(String pattern) {
        TrieNode current = root;
        
        for (char c : pattern.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        
        current.isEndOfWord = true;
        current.word = pattern;
    }
    
    // 构建失败指针
    public void buildFailurePointers() {
        Queue<TrieNode> queue = new LinkedList<>();
        
        // 第一层的失败指针都指向根节点
        for (TrieNode node : root.children.values()) {
            node.fail = root;
            queue.offer(node);
        }
        
        // BFS构建其余节点的失败指针
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char ch = entry.getKey();
                TrieNode child = entry.getValue();
                
                // 构建child的失败指针
                TrieNode failNode = current.fail;
                while (failNode != null && !failNode.children.containsKey(ch)) {
                    failNode = failNode.fail;
                }
                
                child.fail = (failNode == null) ? root : failNode.children.get(ch);
                queue.offer(child);
            }
        }
    }
    
    // 在文本中搜索所有模式串
    public List<Match> search(String text) {
        List<Match> matches = new ArrayList<>();
        TrieNode current = root;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            // 如果当前字符在当前节点的子节点中找不到，跳转到失败指针
            while (current != root && !current.children.containsKey(ch)) {
                current = current.fail;
            }
            
            // 如果找到匹配的字符，移动到子节点
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
                
                // 检查是否有匹配的模式串
                TrieNode temp = current;
                while (temp != root) {
                    if (temp.isEndOfWord) {
                        matches.add(new Match(
                            temp.word,
                            i - temp.word.length() + 1,
                            i
                        ));
                    }
                    temp = temp.fail;
                }
            }
        }
        
        return matches;
    }
    
    // 匹配结果类
    public static class Match {
        String pattern;
        int start;
        int end;
        
        Match(String pattern, int start, int end) {
            this.pattern = pattern;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return String.format("Pattern '%s' found at position %d-%d",
                               pattern, start, end);
        }
    }
    
    // 测试代码
    //
    public static void main(String[] args) {
        ACAutomaton ac = new ACAutomaton();
        
        // 添加多个模式串
        String[] patterns = {"he", "she", "his", "hers"};
        for (String pattern : patterns) {
            ac.addPattern(pattern);
        }
        
        // 构建失败指针
        ac.buildFailurePointers();
        
        // 在文本中搜索
        String text = "hersheshe";
        List<Match> matches = ac.search(text);
        
        // 输出结果
        System.out.println("Text: " + text);
        System.out.println("Found matches:");
        for (Match match : matches) {
            System.out.println(match);
        }
    }
}
