/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (45.10%)
 * Likes:    32661
 * Dislikes: 6550
 * Total Accepted:    5.4M
 * Total Submissions: 11.9M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order, and each of their nodes
 * contains a single digit. Add the two numbers and return the sum as a linked
 * list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have
 * leading zeros.
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    /*
     * To add two numbers represented as linked lists:
     * 
     * 1. Traverse both lists simultaneously node by node
     * 2. Add values at current nodes plus any carry from previous addition
     * 3. If sum >= 10, keep remainder and carry 1 to next addition
     * 4. Create new node with remainder as value
     * 5. Move to next nodes in both lists
     * 6. Handle cases where lists have different lengths
     * 7. Don't forget to add final carry digit if needed
     * 
     * For example with [2,4,3] + [5,6,4]:
     * - 2+5=7  carry=0  [7]
     * - 4+6=10 carry=1  [7,0] 
     * - 3+4+1=8 carry=0 [7,0,8]
     * 
     * Time complexity: O(max(n,m)) where n,m are list lengths
     * Space complexity: O(max(n,m)) for result list
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Input validation
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        int remainder = 0;
        ListNode ans = new ListNode();
        ListNode node1 = l1;
        ListNode node2 = l2;
        int index = 0;
        
        ListNode curr = ans;

        while(true) {

            if (node1 == null && node2 == null) {
                break;
            }

            int n1 = node1.val;
            int n2 = node2.val;
            int total = n1 + n2 + remainder;

            ListNode node = new ListNode();

            if (total >= 10) {
                remainder = 1;
                node.val = total - 10;
            } else {
                node.val = total;
                remainder = 0;
            }

            if (index++ == 0) {
                curr.val = node.val;
            } else {
                curr.next = node;
                curr = node;
            }

         
            node1 = node1.next;
            node2 = node2.next;

            if (node1 == null && node2 == null) {
                // the previous codes Omissed the variable REMAINDER's value.
                if (remainder > 0) {
                    node = new ListNode();
                    node.val = remainder;
                    curr.next = node;
                }
                break;
            }
           

            if (node1 == null) {
                node1 = new ListNode();
                node1.val = 0;
            }

            if (node2 == null) {
                node2 = new ListNode();
                node2.val = 0;
            }

        }
        return ans;
    }
}

class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1: Basic addition
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        ListNode result = solution.addTwoNumbers(l1, l2);
        printList("Test Case 1: 342 + 465 = 807 -> ", result); // Expected: 7->0->8
        
        // Test Case 2: Different lengths
        ListNode l3 = new ListNode(9);
        l3.next = new ListNode(9);
        l3.next.next = new ListNode(9);
        
        ListNode l4 = new ListNode(9);
        
        result = solution.addTwoNumbers(l3, l4);
        printList("Test Case 2: 999 + 9 = 1008 -> ", result); // Expected: 8->9->9->1
        
        // Test Case 3: Zero values
        ListNode l5 = new ListNode(0);
        ListNode l6 = new ListNode(0);
        
        result = solution.addTwoNumbers(l5, l6);
        printList("Test Case 3: 0 + 0 = 0 -> ", result); // Expected: 0
    }
    
    private static void printList(String message, ListNode head) {
        System.out.print(message);
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }
}

// @lc code=end

