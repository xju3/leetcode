/*
 * @lc app=leetcode id=2384 lang=java
 *
 * [2384] Largest Palindromic Number
 *
 * https://leetcode.com/problems/largest-palindromic-number/description/
 *
 * algorithms
 * Medium (36.09%)
 * Likes:    628
 * Dislikes: 232
 * Total Accepted:    45.4K
 * Total Submissions: 125.8K
 * Testcase Example:  '"444947137"'
 *
 * You are given a string num consisting of digits only.
 * 
 * Return the largest palindromic integer (in the form of a string) that can be
 * formed using digits taken from num. It should not contain leading zeroes.
 * 
 * Notes:
 * 
 * 
 * You do not need to use all the digits of num, but you must use at least one
 * digit.
 * The digits can be reordered.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "444947137"
 * Output: "7449447"
 * Explanation: 
 * Use the digits "4449477" from "444947137" to form the palindromic integer
 * "7449447".
 * It can be shown that "7449447" is the largest palindromic integer that can
 * be formed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "00009"
 * Output: "9"
 * Explanation: 
 * It can be shown that "9" is the largest palindromic integer that can be
 * formed.
 * Note that the integer returned should not contain leading zeroes.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num.length <= 10^5
 * num consists of digits.
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

class Solution {

    /**
     * if the given nummber has odd length, we need select a big number which stands in the middle
     * if the given number has even length, we need to chose two big nubmer which stands in the middle.
     * we need to find the count for each digital. considering the middle number(s), put the big number at the string header as possible.
     * @param num
     * @return
     */

    /*
     * Key points for finding largest palindromic number:
     * 
     * 1. Count frequencies:
     *    - Count frequency of each digit (0-9) in input string
     * 
     * 2. Build palindrome:
     *    - Start with largest digit (9 down to 0)
     *    - Place pairs of digits symmetrically from outside in
     *    - Can use each digit up to frequency/2 times for pairs
     * 
     * 3. Handle middle digit:
     *    - If any digit has odd frequency, can use one instance in middle
     *    - Choose largest such digit for middle position
     * 
     * 4. Handle leading zeros:
     *    - Cannot have leading zeros in final number
     *    - Skip zero pairs at start of number
     *    - If result would be all zeros, return "0"
     * 
     * 5. Special cases:
     *    - Empty string -> return "0" 
     *    - Single digit -> return that digit
     *    - All zeros -> return "0"
     */
    public String largestPalindromic(String num) {
        
        // Count frequency of each digit
        int[] freq = new int[10];
        for (char c : num.toCharArray()) {
            freq[c - '0']++;
        }

        // Build palindrome from outside in
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        
        // Handle pairs of digits
        for (int i = 9; i >= 0; i--) {
            // Skip leading zeros
            if (i == 0 && left.length() == 0) {
                break;
            }
            
            // Add pairs of digits
            int pairs = freq[i] / 2;
            for (int j = 0; j < pairs; j++) {
                left.append(i);
                right.insert(0, i);
            }
            freq[i] -= pairs * 2;
        }

        // Find largest remaining digit for middle
        int middle = -1;
        for (int i = 9; i >= 0; i--) {
            if (freq[i] > 0) {
                middle = i;
                break;
            }
        }

        // Build final result
        StringBuilder result = new StringBuilder(left);
        if (middle != -1) {
            result.append(middle);
        }
        result.append(right);

        // Handle case of all zeros
        if (result.length() == 0) {
            return "0";
        }

        return result.toString();
    }


    public String s1(String num) {
       
        return "";
    }
}
// @lc code=end

