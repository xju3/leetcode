/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 *
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (49.78%)
 * Likes:    9955
 * Dislikes: 8490
 * Total Accepted:    3.8M
 * Total Submissions: 7.6M
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads the
 * same forward and backward. Alphanumeric characters include letters and
 * numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric
 * characters.
 * Since an empty string reads the same forward and backward, it is a
 * palindrome.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(String s) {

        if (s == null  || s.length() <= 1) return true;
        int left = 0;
        int right = s.length() - 1;

        while(left <= right) {
            char x = s.charAt(left);
            char y = s.charAt(right);

            if (!Character.isLetterOrDigit(x)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(y)) {
                right--;
                continue;
            }
            if (Character.toLowerCase(x) != Character.toLowerCase(y)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
        
    }
}
// @lc code=end

