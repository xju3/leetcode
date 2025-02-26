/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (35.08%)
 * Likes:    30221
 * Dislikes: 1860
 * Total Accepted:    3.6M
 * Total Submissions: 10.2M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, return the longest palindromic substring in s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * 
 * 
 */

// @lc code=start
class Solution {

    public String longestPalindrome(String s) {
        // Input validation
        if (s == null || s.length() < 2) return s;
        int start = 0;
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {

            // a palindrome in an even-length string has at least 
            // two adjacent identical characters.
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > maxLength) {
                maxLength = len;
                // 此处计算很关键
                // 整体回文的长度有了，start需要从i开始向前计算， 去掉当前i位置字条长度，
                // 
                // 若回文件是单数(odd) = 3, 
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLength);
    }

    public String s1(String s) {
      

        int start = 0;
        int maxLength = 1;

        // For each character, try to expand around it
        for (int i = 0; i < s.length(); i++) {
            // Try odd length palindromes with current char as center
            int len1 = expandAroundCenter(s, i, i);
            // Try even length palindromes with current char and next char as center
            int len2 = expandAroundCenter(s, i, i + 1);
            // Update max length and start index if needed
            int len = Math.max(len1, len2);
            if (len > maxLength) {
                maxLength = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }

    // Helper method to expand around center and find palindrome length
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
}

// @lc code=end

