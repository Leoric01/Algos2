package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class EasyTenToTwenty {

    //    https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return result;
        }
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int i = 0;
        int j = 0;
        int savedIndexI = 0;
        int savedIndexJ = 0;
        boolean loopedAll = false;
        int nextMinBorder = Integer.MAX_VALUE;
        while (k > 0) {
            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[i]);
            pair.add(nums2[j]);
            result.add(pair);
            k--;
            boolean incrementi = false;
            boolean incrementj = false;
            if (nums1[i] + nums2[j + 1] < nums1[i + 1] + nums2[j]) {
                incrementj = true;
            } else incrementi = true;
            if (incrementi && j != nums2.length - 1) {
                savedIndexI = i;
                savedIndexJ = j;
                nextMinBorder = nums1[i] + nums2[j + 1];
            }
            if (nextMinBorder < nums1[i + 1] + nums2[j]) {
                incrementi = false;
                incrementj = true;
                i = savedIndexI;
                j = savedIndexJ;
            }

            if (j == nums2.length - 1) {
                loopedAll = true;
            }
        }
        return result;
    }

    // 1 https://leetcode.com/problems/excel-sheet-column-number/
    public int titleToNumber(String columnTitle) {
        int column = 0;
        for (char c : columnTitle.toCharArray()) {
            column += c - 'A' + 1;
        }
        return column;
    }

    //  2  https://leetcode.com/problems/number-of-1-bits/
    //    way to transform int into binary
    public int hammingWeight(int n) {
        int value = n;
        int weight = 0;
        while (value != 0) {
            if (value % 2 == 1) {
                weight++;
            }
            value /= 2;
        }
        return weight;
    }

    //  3  https://leetcode.com/problems/reverse-string/description/
    public void reverseString(char[] s) {
        char[] temp = new char[s.length];
        System.arraycopy(s, 0, temp, 0, s.length);
        for (int i = 0; i < s.length; i++) {
            s[s.length - 1 - i] = temp[i];
        }
    }

    //  4  https://leetcode.com/problems/contains-duplicate/
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> list = new HashSet<>();
        for (int num : nums) {
            if (list.contains(num)) {
                return true;
            }
            list.add(num);
        }
        return false;
    }

    //    https://leetcode.com/problems/pascals-triangle/
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();

        }
        return result;
    }

    //    https://leetcode.com/problems/valid-perfect-square/
    public boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    //    https://leetcode.com/problems/water-and-jug-problem/
    public boolean canMeasureWater(int x, int y, int target) {
        if (target > x + y) {
            return false;
        }
        if (x == 0) return target == y;
        if (y == 0) return target == x;
        return target % gcd(x, y) == 0;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    //    https://leetcode.com/problems/intersection-of-two-arrays/description/
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> uniques1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> resultSet = Arrays.stream(nums2).boxed().filter(uniques1::contains).collect(Collectors.toSet());
        return resultSet.stream().mapToInt(i -> i).toArray();
    }

    //    https://leetcode.com/problems/find-k-pairs-with-smallest-sums/submissions/1476500044/
    public List<List<Integer>> kSmallestPairsVeryBruteForce(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int value : nums1) {
            for (int i : nums2) {
                List<Integer> pairs = new ArrayList<>();
                pairs.add(value);
                pairs.add(i);
                pairs.add(value + i);
                result.add(pairs);
            }
        }
        result.sort(Comparator.comparingInt(list -> list.get(2)));
        while (result.size() > k) {
            result.removeLast();
        }
        for (List<Integer> pair : result) {
            pair.removeLast();
        }
        return result;
    }
}