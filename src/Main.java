import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Solution solution = new Solution();
        int[] array = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        solution.twoSum(array, 3);
//        solution.isPalindrome(-12121);
//        solution.romanToInt("MCMXCIV");
//        String[] strs = {"test", "te", "testtest"};
//        solution.longestCommonPrefix(strs);
//        solution.isValid("()[]{}");
//        solution.removeDuplicates(array);
//        solution.removeElement(array, 1);
//        solution.strStr("saddd", "dd");
//        solution.lengthOfLastWord(" d sdf fdf  ");
//        solution.lengthOfLastWord("    day");
//        solution.plusOne(new int[]{9, 9, 9});
//        solution.addBinary("11", "01");
//        solution.mySqrt(2147395599);
        solution.climbStairs(44);
    }


    static class Solution {
        //两数之和
        public int[] twoSum(int[] nums, int target) {
            int[] array = new int[2];
            HashMap<Integer, Integer> hashMap = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                hashMap.put(nums[i], i);
                System.out.println(hashMap.toString());
            }
            for (int i = 0; i < nums.length; i++) {
                if (hashMap.containsKey(target - hashMap.get(i))) {
                    return new int[]{hashMap.get(target - hashMap.get(i)), i};
                }
            }
            return null;
        }

        //回文数
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            } else {
                String a = String.valueOf(x);
                int num = a.length() / 2;
                for (int i = 0; i < num; i++) {
                    System.out.println(a.charAt(i));
                    System.out.println(a.charAt(a.length() - 1 - i));
                    if (a.charAt(i) == a.charAt(a.length() - 1 - i)) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

        //罗马数字转整数
        public int romanToInt(String s) {
            int length = s.length();
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                if (s.charAt(i) == 'I') {
                    array[i] = 1;
                }
                if (s.charAt(i) == 'V') {
                    array[i] = 5;
                }
                if (s.charAt(i) == 'X') {
                    array[i] = 10;
                }
                if (s.charAt(i) == 'L') {
                    array[i] = 50;
                }
                if (s.charAt(i) == 'C') {
                    array[i] = 100;
                }
                if (s.charAt(i) == 'D') {
                    array[i] = 500;
                }
                if (s.charAt(i) == 'M') {
                    array[i] = 1000;
                }
//                System.out.println(array[i]);
            }

            int j = 0;
            int nums = 0;
            while (j < length) {
                if (j + 1 < length && array[j] < array[j + 1]) {
                    nums = nums + array[j + 1] - array[j];
//                    System.out.println(nums+":");
                    j = j + 2;
                } else {
                    nums = nums + array[j];
//                    System.out.println(nums+"#");
                    j = j + 1;
                }
            }
            System.out.println(nums);
            return nums;
        }

        //        最长公共前缀
        public String longestCommonPrefix(String[] strs) {
            int strs_length = strs.length;
            String str0 = strs[0];
            int str0_length = str0.length();
            fortag:
            for (int i = 0; i < str0_length; i++) {
                for (int j = 1; j < strs_length; j++) {
                    if ((i >= strs[j].length()) || (str0.charAt(i) != strs[j].charAt(i))) {
                        System.out.println(str0.substring(0, i));
                        return str0.substring(0, i);
                    }
                }
            }
            return str0;
        }

        //        有效的括号
        public boolean isValid(String s) {
            int max = 0;
            HashMap<Integer, Character> hashMap = new HashMap<Integer, Character>();
            hashMap.put(0, s.charAt(0));
            if (s.length() % 2 != 0) {
                return false;
            } else {
                for (int i = 1; i < s.length(); i++) {
//                    System.out.println("max="+max);
                    if (hashMap.isEmpty() && s.charAt(i) == ')') {
                        return false;
                    }
                    if (hashMap.isEmpty() && s.charAt(i) == ']') {
                        return false;
                    }
                    if (hashMap.isEmpty() && s.charAt(i) == '}') {
                        return false;
                    }
                    if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                        max = max + 1;
                        hashMap.put(max, s.charAt(i));
//                        System.out.println(hashMap.entrySet()+"1");
                    } else {
                        if (s.charAt(i) == ')' && hashMap.get(max) == '(') {
                            hashMap.remove(max);
                            max = max - 1;
//                            System.out.println(hashMap.entrySet()+"2");
                        } else if (s.charAt(i) == ']' && hashMap.get(max) == '[') {
                            hashMap.remove(max);
                            max = max - 1;
//                            System.out.println(hashMap.entrySet()+"3");
                        } else if (s.charAt(i) == '}' && hashMap.get(max) == '{') {
                            hashMap.remove(max);
                            max = max - 1;
//                            System.out.println(hashMap.entrySet()+"3");
                        } else {
//                            System.out.println("false");
//                            System.out.println(hashMap.entrySet()+"4");
                            return false;
                        }
                    }
                }
            }
            if (!hashMap.isEmpty()) {
//                System.out.println("false5");
//                System.out.println(hashMap.entrySet()+"5");
                return false;
            }
            return true;
        }

        //        合并两个有序链表
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }
            if (list2.val < list1.val) {
                list2.next = mergeTwoLists(list2.next, list1);
                return list2;
            }
            return list1;
        }

        //        删除有序数组中的重复项
        public int removeDuplicates(int[] nums) {
            Set<Integer> set = new LinkedHashSet<>();
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }
            System.out.println(set.size());
            System.out.println(set.toString());
            int i = 0;
            for (Integer integer : set) {
                nums[i] = integer;
                i++;
            }
            return set.size();
        }

        //移除元素
        public int removeElement(int[] nums, int val) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                if (nums[left] != val) {
                    ++left;
                } else {
                    if (nums[right] != val) {
                        nums[left] = nums[right];
                        --right;
                    } else {
                        --right;
                    }
                }
            }
            System.out.println(left);
            return left;
        }

        //        找出字符串中第一个匹配项的下标
        public int strStr(String haystack, String needle) {
            int length2 = needle.length();
            for (int i = 0; i <= haystack.length() - length2; i++) {
                String aim = haystack.substring(i, i + length2);
                if (aim.equals(needle)) {
                    System.out.println(i);
                    return i;
                }
            }
            System.out.println(-1);
            return -1;
        }

        //        搜索插入位置，二分查找法
        public int searchInsert(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

        //        最后一个单词的长度
        public int lengthOfLastWord(String s) {
            String s1 = s.trim();
            int i = 0;
            int length = s1.length();
            for (int j = length - 1; j >= 0; j--) {
                if (s1.charAt(j) != ' ') {
                    i++;
                } else {
                    System.out.println(i);
                    return i;
                }
            }
            System.out.println(i);
            return i;
        }

        //        加一
        public int[] plusOne(int[] digits) {
            int length = digits.length;
            for (int i = length - 1; i >= 0; i--) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                    continue;
                }
                if (digits[i] != 9) {
                    digits[i] += 1;
                    break;
                }

            }
            int[] temp = new int[length + 1];
            temp[0] = 1;
            if (digits[0] == 0) {
//                System.out.println(Arrays.toString(temp));
//                for (int i : temp) {
//                    System.out.println(i);
//                }

                return temp;
            } else return digits;
        }

        //        二进制求和
        public String addBinary(String a, String b) {
            BigInteger bigInteger1 = new BigInteger(a, 2);
            BigInteger bigInteger2 = new BigInteger(b, 2);
            BigInteger bigIntegern = bigInteger1.add(bigInteger2);
            String n = bigIntegern.toString(2);
            System.out.println(n);
            return n;
        }
        //平方根
        public int mySqrt(int x) {
            double left = 0;
            double right = x/2;
            while (left < right) {
                double save_right = right;
                double save_left = left;
                if (left * left <= x && right * right >= x) {;
                    right = (left+right)/2;
                    System.out.println("right:"+right);
                }
                if (left * left <= x && right * right <= x) {
                    left = right;
                    if ((left+1)*(left+1)==x) {
                        System.out.println(left+1);
                        return (int) (left+1);
                    }
                    if((left+1)*(left+1)>x) {
                        System.out.println(left);
                        return (int) left;
                    } else {
                        right = save_right;
                    }
                    System.out.println("left"+left);
                }
            }
            return 0;
        }
        //爬楼梯
        public int climbStairs(int n) {;
            //递归解法   重复计算过多，耗时
//            int num = 0;
//            if (n > 2) {
//                num = climbStairs(n - 1) + climbStairs(n - 2);
//                System.out.println("n:"+n+"num:"+num);
//            }
//            if (n == 1) {
//                num = 1;
//                return num;
//            }
//            if (n == 2) {
//                num = 2;
//                return num;
//            }
//            return num;
            //递归demo
//            int num = 1;
//            if (n > 1) {
//                num += climbStairs(n - 1);
//            }
//
//            if (n == 1) {
//                num = 1;
//                return num;
//            }
//            System.out.println(num);
//            return num;
            int present = 2;
            int before = 1;
            int sum = 0;
            for (int i = n; i >= 3; i--) {
                sum = present+before;
                int temp = present;
                present = sum;
                before = temp;
            }
            System.out.println(sum);
            return sum;
        }
    }

    //    * Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}