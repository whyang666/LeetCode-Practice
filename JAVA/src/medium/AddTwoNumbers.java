package medium;
/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers_old(ListNode l1, ListNode l2) { //把高位拆出来很蠢，可以直接当高位为空时填充为0
        ListNode ans = new ListNode(-1);
        ListNode a = l1;
        ListNode b = l2;
        ListNode c = ans;
        //int i = 0;
        int jinwei = 0;
        while (a != null && b != null) {
            int val = a.val + b.val + jinwei;
            jinwei = val/10;
            val = val % 10;
            if (c.val == -1) {
                c.val = val;
            } else {
                ListNode temp = new ListNode(val);
                c.next = temp;
                c = c.next;
            }
            //if(a.next!=null || b.next!=null) {
                a = a.next;
                b = b.next;
           // }
        }
        //ListNode d = a?(a.next!=null):b;
        ListNode d;
        if (a != null) {
            d = a;
        } else
            d = b;
        while (d.next != null) {
            d = d.next;
            int val = d.val + jinwei;
            jinwei = 0;
            while (val > 9) {
                val /= 10;
                jinwei++;
            }
            ListNode temp = new ListNode(val);
            c.next = temp;
            c = c.next;
        }
        if (jinwei != 0) {
            ListNode temp = new ListNode(jinwei);
            c.next = temp;
        }
        return ans;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

public class AddTwoNumbers {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = new Solution().addTwoNumbers(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}

