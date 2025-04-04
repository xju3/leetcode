/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 *
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (42.98%)
 * Likes:    7360
 * Dislikes: 0
 * Total Accepted:    1.2M
 * Total Submissions: 2.8M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 
 * 
 * 
 * 
 * 
 * 
 * 提示：
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
    private double s1(int[] nums1, int[] nums2) {
        double ans = 0.0;
        int m = nums1.length;
        int n = nums2.length;
        int[] combined = new int[m+n];
        int i = 0, j = 0, k = 0;

        while(j < m && k < n ) {
            if (nums1[j] < nums2[k]) {
                combined[i++] = nums1[j++]; 
            } else {
                combined[i++] = nums2[k++];
            }
        }

        while( j < m) {
            combined[i++] = nums1[j++];
        }

        while (k < n) {
            combined[i++] = nums2[k++];
        }

        boolean isEven = (m + n) % 2 == 0;
        int middle = (m + n) / 2;

        if (isEven) {
            ans = (combined[middle] + combined[middle -1]) / 2.0;
        } else {
            ans = combined[middle];
        }

        return ans;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       return s1(nums1, nums2);
    }
}
// @lc code=end

