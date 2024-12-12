package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class EasyTwentyToThirty {
    //    https://leetcode.com/problems/pacific-atlantic-water-flow/
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 1) {
            return List.of(List.of(1));
        }
        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacificReach = new boolean[rows][cols];
        boolean[][] atlanticReach = new boolean[rows][cols];
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            pacificReach[i][0] = atlanticReach[i][cols - 1] = true;
        }
        for (int j = 0; j < cols; j++) {
            pacificReach[0][j] = atlanticReach[rows - 1][j] = true;
        }

        for (int row = 0; row < heights.length; row++) {
            for (int col = 0; col < heights[row].length; col++) {
                if (pacificReach[row][col]) {
                    processMatrix(heights, pacificReach);
                }
                if (atlanticReach[heights.length - 1 - row][heights[row].length - col - 1]) {
                    processMatrix(heights, atlanticReach);
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (pacificReach[row][col] && atlanticReach[row][col]) {
                    result.add(List.of(row, col));
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print((pacificReach[i][j] && atlanticReach[i][j]) + ",");
            }
            System.out.println();
        }
        return result;
    }

    public static void processMatrix(int[][] integerMatrix, boolean[][] booleanMatrix) {
        int rows = booleanMatrix.length;
        int cols = booleanMatrix[0].length;
        boolean[][] updatedBoolMatrix = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(booleanMatrix[i], 0, updatedBoolMatrix[i], 0, cols);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (booleanMatrix[i][j]) {
                    if (i > 0 && integerMatrix[i - 1][j] > integerMatrix[i][j]) {
                        updatedBoolMatrix[i - 1][j] = true;
                    }
                    if (i < rows - 1 && integerMatrix[i + 1][j] > integerMatrix[i][j]) {
                        updatedBoolMatrix[i + 1][j] = true;
                    }
                    if (j > 0 && integerMatrix[i][j - 1] >integerMatrix[i][j]) {
                        updatedBoolMatrix[i][j - 1] = true;
                    }
                    if (j < cols - 1 &&integerMatrix[i][j + 1] >integerMatrix[i][j]) {
                        updatedBoolMatrix[i][j + 1] = true;
                    }
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            System.arraycopy(updatedBoolMatrix[i], 0, booleanMatrix[i], 0, cols);
        }
    }

    //    https://leetcode.com/problems/reconstruct-original-digits-from-english/
    public static String originalDigits(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        int[] digitCount = new int[10];
        digitCount[0] = charCount['z' - 'a'];
        digitCount[2] = charCount['w' - 'a'];
        digitCount[4] = charCount['u' - 'a'];
        digitCount[6] = charCount['x' - 'a'];
        digitCount[8] = charCount['g' - 'a'];
        digitCount[3] = charCount['h' - 'a'] - digitCount[8];
        digitCount[5] = charCount['f' - 'a'] - digitCount[4];
        digitCount[7] = charCount['s' - 'a'] - digitCount[6];
        digitCount[9] = charCount['i' - 'a'] - digitCount[5] - digitCount[6] - digitCount[8];
        digitCount[1] = charCount['o' - 'a'] - digitCount[0] - digitCount[2] - digitCount[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(String.valueOf(i).repeat(digitCount[i]));
        }
        return sb.toString();
    }

    //    https://leetcode.com/problems/find-right-interval/
    public static int[] findRightInterval(int[][] intervals) {
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i][1];
            result[i] = -1;
            int startDiff = Integer.MAX_VALUE;
            for (int j = 0; j < intervals.length; j++) {
                int start = intervals[j][0];
                if (start >= end && Math.abs(end - start) <= startDiff) {
                    result[i] = j;
                    startDiff = Math.abs(end - start);
                }
            }
        }
        return result;
    }

    //    https://leetcode.com/problems/arranging-coins/description/
    public static int arrangeCoins(int n) {
        int row = 0;
        while (n > row) {
            row++;
            n = n - row;
        }
        return row;
    }

    //  https://leetcode.com/problems/coin-change/
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    //    https://leetcode.com/problems/ransom-note/
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null || ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] charOccurrences = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            if (i < ransomNote.length()) {
                charOccurrences[ransomNote.charAt(i) - 'a']++;
            }
            charOccurrences[magazine.charAt(i) - 'a']--;
        }
        for (int i : charOccurrences) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }

    //    https://leetcode.com/problems/longest-increasing-subsequence/
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //    https://leetcode.com/problems/battleships-in-a-board/
    public int countBattleships(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X') {
                    if ((i == 0 || board[i - 1][j] == '.') &&
                        (j == 0 || board[i][j - 1] == '.')) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //    https://leetcode.com/problems/increasing-triplet-subsequence/
    public boolean increasingTriplet(int[] nums) {
        int firstNum = Integer.MAX_VALUE;
        int secondNum = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= firstNum) {
                firstNum = num;
            } else if (num <= secondNum) {
                secondNum = num;
            } else {
                return true;
            }
        }
        return false;
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