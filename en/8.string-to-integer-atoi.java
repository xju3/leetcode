/*
 * @lc app=leetcode id=8 lang=java
 *
 * [8] String to Integer (atoi)
 *
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 * algorithms
 * Medium (18.51%)
 * Likes:    5039
 * Dislikes: 14341
 * Total Accepted:    1.8M
 * Total Submissions: 9.8M
 * Testcase Example:  '"42"'
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit
 * signed integer.
 * 
 * The algorithm for myAtoi(string s) is as follows:
 * 
 * 
 * Whitespace: Ignore any leading whitespace (" ").
 * Signedness: Determine the sign by checking if the next character is '-' or
 * '+', assuming positivity if neither present.
 * Conversion: Read the integer by skipping leading zeros until a non-digit
 * character is encountered or the end of the string is reached. If no digits
 * were read, then the result is 0.
 * Rounding: If the integer is out of the 32-bit signed integer range [-2^31,
 * 2^31 - 1], then round the integer to remain in the range. Specifically,
 * integers less than -2^31 should be rounded to -2^31, and integers greater
 * than 2^31 - 1 should be rounded to 2^31 - 1.
 * 
 * 
 * Return the integer as the final result.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "42"
 * 
 * Output: 42
 * 
 * Explanation:
 * 
 * 
 * The underlined characters are what is read in and the caret is the current
 * reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 * ⁠        ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 * ⁠        ^
 * Step 3: "42" ("42" is read in)
 * ⁠          ^
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = " -042"
 * 
 * Output: -42
 * 
 * Explanation:
 * 
 * 
 * Step 1: "   -042" (leading whitespace is read and ignored)
 * ⁠           ^
 * Step 2: "   -042" ('-' is read, so the result should be negative)
 * ⁠            ^
 * Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
 * ⁠              ^
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1337c0d3"
 * 
 * Output: 1337
 * 
 * Explanation:
 * 
 * 
 * Step 1: "1337c0d3" (no characters read because there is no leading
 * whitespace)
 * ⁠        ^
 * Step 2: "1337c0d3" (no characters read because there is neither a '-' nor
 * '+')
 * ⁠        ^
 * Step 3: "1337c0d3" ("1337" is read in; reading stops because the next
 * character is a non-digit)
 * ⁠            ^
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "0-1"
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * 
 * Step 1: "0-1" (no characters read because there is no leading whitespace)
 * ⁠        ^
 * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
 * ⁠        ^
 * Step 3: "0-1" ("0" is read in; reading stops because the next character is a
 * non-digit)
 * ⁠         ^
 * 
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "words and 987"
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * Reading stops at the first non-digit character 'w'.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), '
 * ', '+', '-', and '.'.
 * 
 * 
 */

// @lc code=start
class Solution {

    /*
     * Key points for String to Integer (atoi):
     * 
     * 1. Handle whitespace:
     *    - Skip leading whitespace characters
     * 
     * 2. Handle signs:
     *    - Check for '+' or '-' sign
     *    - Only first sign is valid
     * 
     * 3. Handle digits:
     *    - Read digits until non-digit character
     *    - Convert string of digits to integer
     * 
     * 4. Handle overflow:
     *    - Check for Integer.MAX_VALUE overflow
     *    - Check for Integer.MIN_VALUE overflow
     * 
     * 5. Handle invalid input:
     *    - Return 0 if first non-whitespace char is not digit or sign
     *    - Return 0 if no digits found
     * 
     * 6. Return rules:
     *    - Clamp to Integer.MAX_VALUE if result > MAX_VALUE
     *    - Clamp to Integer.MIN_VALUE if result < MIN_VALUE
     *    - Return 0 for invalid input
     */
    public int myAtoi(String s) {
        // Handle empty string
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Remove leading whitespace
        s = s.trim();

        // Handle empty string after trim
        if (s.length() == 0) {
            return 0;
        }

        char firstChar = s.charAt(0);
        if (Character.isDigit(firstChar)) {
           return getValue(s, '\0');
        } else {
            if (firstChar == '+' || firstChar == '-') {
                return getValue(s, firstChar);
            }
        }
        return 0;
    }

    private int getValue(String s, char firstChar) {
        boolean positive = firstChar == '\0' || firstChar == '+';
        
        // Find prefix digits starting from index 0 or 1 depending on sign
        int startIndex = firstChar == '\0' ? 0 : 1;
        int endIndex = startIndex;
        
        while (endIndex < s.length() && Character.isDigit(s.charAt(endIndex))) {
            endIndex++;
        }

        // No digits found
        if (endIndex == startIndex) {
            return 0;
        }

        // Convert digits to integer, handling overflow
        try {
            long result = Long.parseLong(s.substring(startIndex, endIndex));
            result = positive ? result : -result;
            
            if (result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            return (int)result;
        } catch (NumberFormatException e) {
            // Handle overflow case
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

    }


}
// @lc code=end

