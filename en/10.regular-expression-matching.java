/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (28.80%)
 * Likes:    12508
 * Dislikes: 2246
 * Total Accepted:    1.1M
 * Total Submissions: 3.8M
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string s and a pattern p, implement regular expression
 * matching with support for '.' and '*' where:
 * 
 * 
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a
 * previous valid character to match.
 * 
 * 
 */

// @lc code=start

import java.util.Map;

class Solution {


    public boolean s2(String s, String p) {
        Map<String, Boolean> cache = new HashMap<>();
        return dfs(s, p, 0, 0, cache);
    }

    public boolean dfs(String s, String p, int i, int j, Map<String, Boolean> cache) {

        String key = i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        // 如果两个字串都比较完成，返回True.
        if (i >= s.length() && j >= p.length()) return true;
        if (j >= p.length()) return false;

        // 最简单的情况，相应字符相同，或通过dot进行match.
        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        //判断patten后一个字符是否为*(star), 若为star,有两个策略，一是弃用，即返回空字串，二是采用，进行模式匹配.
        //无论是弃用还是采用，只是匹配得上，都是程序想要的结果.
        if ((j + 1) < p.length() && p.charAt(j + 1) == '*') {
            boolean value = dfs(s, p, i, j + 2) || (match && dfs(s, p, i + 1, j));
            cache.put(key, value);
            return cache.get(key);
        }

        if (match) {
             boolean value =  dfs(s, p, i + 1, j + 1);
             cache.put(key, value);
             return cache.get(key); 
        }

        return false;
    }

    public boolean s1(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        // Initialize first row (empty string)
        // i是s的下标
        // j是p的下标
        // 以pattern为基准构建数组
        for (int j = 1; j <= n; j++) {
            //只是寻找pattern中的星号.
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                
                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    dp[i][j] = dp[i][j - 2]; // zero occurrence
                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // one or more occurrence
                    }
                }
            }
        }
        
        return dp[m][n];
    }

    public boolean isMatch(String s, String p) {
        return s2(s, p);
        // return s1(s, p);
    }
}
// @lc code=end

