package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class EasyTenToTwenty {

    public static List<List<Integer>> kSmallestPairsTRAGEDIE(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // Edge case
        if (nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return result;
        }

        // Ensure nums1 is always the longer (or equal) array
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int i = 0, j = 0;
        int savedIndexI = 0, savedIndexJ = 0;

        while (k > 0) {
            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[i]);
            pair.add(nums2[j]);
            result.add(pair);
            k--;
            boolean incrementI = false, incrementJ = false;

            if (j + 1 < nums2.length && i + 1 < nums1.length) {
                if (nums1[i] + nums2[j + 1] <= nums1[i + 1] + nums2[j]) {
                    incrementJ = true;
                } else {
                    incrementI = true;
                }
            } else if (j + 1 < nums2.length) {
                incrementJ = true;
            } else if (i + 1 < nums1.length) {
                incrementI = true;
            }

            // Increment i or j as decided
            if (incrementI) {
                // Save the current j before incrementing i
                savedIndexI = i;
                savedIndexJ = j;
                i++; // Increment i
            } else if (incrementJ) {
                j++; // Increment j
            }

            // Handle backtracking logic: When j is exhausted, revert to saved indices
            if (j >= nums2.length - 1 && incrementI) {
                i = savedIndexI;
                j = savedIndexJ + 1;
            }

            // If both indices are exhausted, stop
            if (i >= nums1.length || j >= nums2.length) {
                break;
            }
        }

        return result;
    }

    //    https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>(k);
        int[] indexes = new int[nums1.length];
        while (k-- > 0) {
            int minSum = Integer.MAX_VALUE;
            int nums1Id = -1;
            int nums2Id = -1;
            for (int i = 0; i < nums1.length; i++) {
                if (indexes[i] == nums2.length) {
                    continue;
                }
                int sum = nums1[i] + nums2[indexes[i]];
                if (sum < minSum) {
                    minSum = sum;
                    nums1Id = i;
                    nums2Id = indexes[i];
                }
                if (indexes[i] == 0) {
                    break;
                }
            }
            if (nums1Id < 0) {
                break;
            }
            ans.add(Arrays.asList(nums1[nums1Id], nums2[nums2Id]));
            indexes[nums1Id]++;
        }
        return ans;
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