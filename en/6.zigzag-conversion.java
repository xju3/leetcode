/*
 * @lc app=leetcode id=6 lang=java
 *
 * [6] Zigzag Conversion
 *
 * https://leetcode.com/problems/zigzag-conversion/description/
 *
 * algorithms
 * Medium (50.39%)
 * Likes:    8192
 * Dislikes: 15124
 * Total Accepted:    1.6M
 * Total Submissions: 3.2M
 * Testcase Example:  '"PAYPALISHIRING"\n3'
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * 
 * string convert(string s, int numRows);
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "A", numRows = 1
 * Output: "A"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    /*
     * Solution Approach:
     * 
     * 1. For zigzag pattern, we need to understand the pattern first:
     *    - Characters are written in zigzag on n rows
     *    - We move down n rows, then move up diagonally to row 1
     *    - This creates a cycle pattern that repeats
     * 
     * 2. Key observations:
     *    - The cycle length = 2*numRows - 2 (down + up movement)
     *    - For numRows=1, cycle length should be 1
     *    - First and last rows only get characters at cycle intervals
     *    - Middle rows get 2 characters per cycle:
     *      One during downward movement and one during upward movement
     * 
     * 3. Algorithm:
     *    - Create StringBuilder for each row
     *    - Iterate through string and determine which row each char belongs to
     *    - Track direction (moving down or up diagonally)
     *    - Combine all rows at the end
     * 
     * Time Complexity: O(n) where n is length of input string
     * Space Complexity: O(n) to store the result
     */

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        int len = s.length();
        int groupSize = numRows + (numRows - 2);
        int groups = len / groupSize;
        int remainder = len % groupSize;
        if (remainder > 0) groups += 1;
        int rows = numRows;
        int cols = groups * numRows;

        char[][] arr = new char[rows][cols];
        
        int row = 0;
        int col = 0;

        int direction = 0;

        for (int i=0; i < len; i++) {
            char c = s.charAt(i);
            
            arr[row][col] = c;

            if (row == 0) {
                direction = 0;
            }

            if (row == rows - 1) {
                direction = 1;
            }


            if (direction == 0) {
                row ++;
            }

            if (direction == 1) {
                row --;
                col ++;
            }

        }

        String ans = "";
        for (int m = 0;  m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                char c = arr[m][n];
                if (c == '\0') continue;
                ans += String.valueOf(c);
            }
        }
        return ans;
    }
}
// @lc code=end

