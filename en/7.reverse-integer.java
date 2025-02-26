/*
 * @lc app=leetcode id=7 lang=java
 *
 * [7] Reverse Integer
 *
 * https://leetcode.com/problems/reverse-integer/description/
 *
 * algorithms
 * Medium (29.69%)
 * Likes:    13730
 * Dislikes: 13710
 * Total Accepted:    3.8M
 * Total Submissions: 12.7M
 * Testcase Example:  '123'
 *
 * Given a signed 32-bit integer x, return x with its digits reversed. If
 * reversing x causes the value to go outside the signed 32-bit integer range
 * [-2^31, 2^31 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed
 * or unsigned).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 123
 * Output: 321
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = -123
 * Output: -321
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: x = 120
 * Output: 21
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -2^31 <= x <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    /*
     * Key points to solve this challenge:
     * 
     * 1. Need to handle both positive and negative numbers
     * 
     * 2. Need to check for integer overflow/underflow since we're limited to 32-bit
     *    - Can't use long or 64-bit integers
     *    - Need to check before the final digit is added
     * 
     * 3. Algorithm approach:
     *    - Extract digits one by one using modulo (%) and division (/)
     *    - Build reversed number by multiplying by 10 and adding digits
     *    - Check for overflow before each multiplication/addition
     * 
     * 4. Edge cases to handle:
     *    - Numbers ending in zero (120 -> 21)
     *    - Negative numbers (-123 -> -321)
     *    - Numbers that cause overflow (1534236469 -> 0)
     *    - Maximum and minimum integer values
     */
    public int reverse(int x) {
        if (x == 0) return 0;
        boolean minus = false;

        if (x < 0) {
            minus = true;
            long result = (long) x * -1; // Use long to avoid overflow
            if (result <= Integer.MAX_VALUE) {
                x = (int) result; // Safe to cast back to int
            } else {
                return 0;
            }
        } 
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder(s);
        String tmp = sb.reverse().toString();


        long longValue = Long.valueOf(tmp);
        if (minus) {
            longValue *= -1;
        }
        if (longValue > Integer.MAX_VALUE){
            return 0;
        }

        if (longValue < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) longValue; 
        
    }
}
// @lc code=end

