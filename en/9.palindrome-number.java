/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 *
 * https://leetcode.com/problems/palindrome-number/description/
 *
 * algorithms
 * Easy (58.36%)
 * Likes:    13514
 * Dislikes: 2793
 * Total Accepted:    5.8M
 * Total Submissions: 9.9M
 * Testcase Example:  '121'
 *
 * Given an integer x, return true if x is a palindrome, and false
 * otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it
 * becomes 121-. Therefore it is not a palindrome.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a
 * palindrome.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -2^31 <= x <= 2^31 - 1
 * 
 * 
 * 
 * Follow up: Could you solve it without converting the integer to a string?
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            int l1 = palindromeLength(s, i, i);
            int l2 = palindromeLength(s, i, i + 1);
            int len = Math.max(l1, l2);
            if (len > max) {
                max = len;
            }
        }
        return max  == s.length();
    }


    private int palindromeLength(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        return right - left - 1;
    }
}
// @lc code=end

