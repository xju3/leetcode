/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (42.54%)
 * Likes:    29405
 * Dislikes: 3303
 * Total Accepted:    3.1M
 * Total Submissions: 7.3M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 * the median of the two sorted arrays.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {


    private int[] mergeTwoArray(int[] num1, int[] num2) {
        int m = num1.length;
        int n = num2.length;

        int[] ans = new int[m + n];

        int i = 0, j = 0, k = 0;

        // merge the data with common length.
        while(j < m  && k < n) {
            if (num1[j] < num2[k]) {
                ans[i++] = num1[j++];
            } else {
                ans[i++] = num2[k++];
            }
        }


        //copy remaining elements from num1 if any.
        while(j < m) {
            ans[i++] = num1[j++];
        }

        // copy remaining elements from num2 if any.
        while(k < n) {
            ans[i++] = num2[k++];
        }

        return ans;
    }

    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       double ans = 0.0;
       int m = nums1.length;
       int n = nums2.length;
       int[] merged = mergeTwoArray(nums1, nums2);
       System.out.print(String.format("m: %s, n:%s, total: %s", m, n, merged.length));
       // Find median
       int total = m + n;
       if (total % 2 == 0) {
           // If even length, average of two middle elements
           ans = (merged[total/2 - 1] + merged[total/2]) / 2.0;
       } else {
           // If odd length, middle element
           ans = merged[total/2];
       }
       return ans;
    }
}


class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1: Basic case with equal length arrays
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 4};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("Test Case 1: [1,3] + [2,4] -> " + result + " (Expected: 2.5)");
        
        // Test Case 2: Arrays of different lengths
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4, 5};
        result = solution.findMedianSortedArrays(nums3, nums4);
        System.out.println("Test Case 2: [1,2] + [3,4,5] -> " + result + " (Expected: 3.0)");
        
        // Test Case 3: One empty array
        int[] nums5 = {};
        int[] nums6 = {1};
        result = solution.findMedianSortedArrays(nums5, nums6);
        System.out.println("Test Case 3: [] + [1] -> " + result + " (Expected: 1.0)");
        
        // Test Case 4: Arrays with negative numbers
        int[] nums7 = {-5, 3, 6, 12, 15};
        int[] nums8 = {-12, -10, -6, 0, 4, 10};
        result = solution.findMedianSortedArrays(nums7, nums8);
        System.out.println("Test Case 4: [-5,3,6,12,15] + [-12,-10,-6,0,4,10] -> " + result);
        
        // Test Case 5: Arrays with duplicate values
        int[] nums9 = {1, 1, 3, 3};
        int[] nums10 = {1, 1, 3, 3};
        result = solution.findMedianSortedArrays(nums9, nums10);
        System.out.println("Test Case 5: [1,1,3,3] + [1,1,3,3] -> " + result + " (Expected: 2.0)");
        
        // Test Case 6: Single element arrays
        int[] nums11 = {2};
        int[] nums12 = {1};
        result = solution.findMedianSortedArrays(nums11, nums12);
        System.out.println("Test Case 6: [2] + [1] -> " + result + " (Expected: 1.5)");
    }
}

// @lc code=end
