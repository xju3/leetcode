public class KMPPalindrome {
    // 计算KMP算法的前缀表
    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;  // 当前最长前缀长度
        int i = 1;    // 从第二个字符开始比较
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    // 查找从起始位置开始的最长回文
    public static String findLongestPalindromeFromStart(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        // 创建原字符串和其反转的拼接，中间加特殊字符防止重叠
        StringBuilder reversed = new StringBuilder(s).reverse();
        String combined = s + "#" + reversed.toString();
        
        // 计算KMP的前缀表
        int[] lps = computeLPSArray(combined);
        
        // 找到起始位置的最长回文
        int maxLen = 0;
        // 只考虑原字符串长度范围内的匹配
        for (int i = s.length() + 1; i < combined.length(); i++) {
            if (lps[i] > maxLen && 
                (combined.length() - i - 1 + lps[i] == s.length())) {
                maxLen = lps[i];
            }
        }
        
        // 返回找到的回文子串
        return s.substring(0, maxLen);
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
