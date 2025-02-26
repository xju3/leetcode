/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 *
 * https://leetcode.com/problems/shortest-palindrome/description/
 *
 * algorithms
 * Hard (40.12%)
 * Likes:    4303
 * Dislikes: 275
 * Total Accepted:    294K
 * Total Submissions: 732.8K
 * Testcase Example:  '"aacecaaa"'
 *
 * You are given a string s. You can convert s to a palindrome by adding
 * characters in front of it.
 * 
 * Return the shortest palindrome you can find by performing this
 * transformation.
 * 
 * 
 * Example 1:
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * Input: s = "abcd"
 * Output: "dcbabcd"
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 5 * 10^4
 * s consists of lowercase English letters only.
 * 
 * 
 */

// @lc code=start
class Solution {

    
    public String shortestPalindrome(String s) {
        return s3(s);
    }


    public String s3(String s) {
        if (s == null || s.length() == 0) return s;
        String palindrome  = manacheFindLongestPalindromeFromStart(s);
        if (palindrome.length() == s.length()) return s;
        String remainder = s.substring(palindrome.length());
        StringBuilder sb = new StringBuilder(remainder);
        return sb.reverse().toString() + s;
    }


    // 查找从起始位置开始的最长回文
    public String findLongestPalindromeFromStart(String s) {
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

    private  int[] computeLPSArray(String pattern) {
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


    public String s2(String s) {
        if (s == null || s.length() == 0) return s;
        String palindrome  = manacheFindLongestPalindromeFromStart(s);
        if (palindrome.length() == s.length()) return s;
        String remainder = s.substring(palindrome.length());
        StringBuilder sb = new StringBuilder(remainder);
        return sb.reverse().toString() + s;
    }


    public static String manacheFindLongestPalindromeFromStart(String s) {
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

    public String findFirstLongestPaliindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // 预处理字符串
        String processedStr = preprocess(s);
        int n = processedStr.length();
        int[] p = new int[n]; // 记录每个字符的回文半径
        int center = 0; // 当前回文中心
        int right = 0; // 当前回文的最右边界

        // 遍历处理后的字符串
        for (int i = 0; i < n; i++) {
            // 利用对称性快速计算 p[i]
            if (i < right) {
                int mirror = 2 * center - i; // i 关于 center 的对称点
                p[i] = Math.min(right - i, p[mirror]);
            }

            // 尝试扩展回文
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n &&
                   processedStr.charAt(i - p[i] - 1) == processedStr.charAt(i + p[i] + 1)) {
                p[i]++;
            }

            // 更新最右边界和中心
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        // 找到最长回文的中心位置和半径
        int maxLen = 0;
        int maxCenter = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                maxCenter = i;
            }
        }

        // 提取最长回文子串
        int start = (maxCenter - maxLen) / 2; // 原始字符串中的起始位置
        int end = start + maxLen; // 原始字符串中的结束位置
        return s.substring(start, end);
    }

    // 预处理字符串，插入特殊字符
    private String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        return sb.toString();
    }

    public String s1(String s) {

        // Handle empty or single char string
        if (s == null || s.length() <= 1) return s;
        if (isPalindrome(s)) return s;

        // Find longest palindrome starting from beginning
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            // Try odd length palindromes
            int len1 = expandAroundCenter(s, i, i);
            // Try even length palindromes 
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            int newStart = i - (len - 1) / 2;
            
            // Only update if palindrome starts at beginning
            if (len > maxLen && newStart == 0) {
                maxLen = len;
            }
        }

        // If longest palindrome covers whole string, return as is
        if (maxLen == s.length()) return s;

        // Get the non-palindrome part and reverse it
        String remainder = s.substring(maxLen);
        StringBuilder sb = new StringBuilder(remainder);
        
        // Add reversed remainder to front of original string
        return sb.reverse().toString() + s;
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


    private boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
// @lc code=end

