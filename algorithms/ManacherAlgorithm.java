public class ManacherAlgorithm {
    public static String findLongestPalindromeFromStart(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // 预处理字符串，在每个字符间插入特殊字符 '#'
        StringBuilder processed = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            processed.append(c).append("#");
        }
        String t = processed.toString();
        
        int n = t.length();
        int[] p = new int[n]; // p[i]表示以i为中心的回文半径
        int center = 0; // 当前回文中心
        int maxRight = 0; // 当前回文的最右边界
        
        int maxLen = 0; // 最长回文长度
        int start = 0; // 原始字符串中最长回文的起始位置
        
        for (int i = 0; i < n; i++) {
            if (i < maxRight) {
                // 利用对称性
                int mirror = 2 * center - i;
                p[i] = Math.min(maxRight - i, p[mirror]);
            }
            
            // 中心扩展
            int left = i - (p[i] + 1);
            int right = i + (p[i] + 1);
            while (left >= 0 && right < n && t.charAt(left) == t.charAt(right)) {
                p[i]++;
                left--;
                right++;
            }
            
            // 更新中心和右边界
            if (i + p[i] > maxRight) {
                center = i;
                maxRight = i + p[i];
            }
            
            // 检查是否是从原始字符串起始位置开始的回文
            int realStart = (i - p[i]) / 2;
            if (realStart == 0 && p[i] > maxLen) {
                maxLen = p[i];
                start = realStart;
            }
        }
        
        // 如果没有找到以0开始的回文，返回空字符串
        if (maxLen == 0) {
            return "";
        }
        
        // 返回原始字符串中的最长回文子串
        return s.substring(start, start + maxLen);
    }
    
    // 测试代码
    public static void main(String[] args) {
        String[] tests = {
            "ababa",           // 应返回 "ababa"
            "aaaa",           // 应返回 "aaaa"
            "abcd",           // 应返回 "a"
            "abacd",          // 应返回 "aba"
            "abc"             // 应返回 "a"
        };
        
        for (String test : tests) {
            System.out.println("Input: " + test);
            System.out.println("Longest palindrome from start: " + 
                             findLongestPalindromeFromStart(test));
            System.out.println();
        }
    }
}
