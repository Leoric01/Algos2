import static leetcode.EasyTenToTwenty.kSmallestPairs;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {1, 2, 3};
        int k = 2;
        System.out.println(kSmallestPairs(nums1, nums2, k)); // [[1,1],[1,1]]

        // Example 2
        nums1 = new int[]{1, 2, 4, 5, 6};
        nums2 = new int[]{3, 5, 7, 9};
        k = 3;

        System.out.println(kSmallestPairs(nums2, nums1, k)); // [[1,3],[2,3],[1,5]]

        // Example 3
        nums1 = new int[]{1, 2};
        nums2 = new int[]{3};
        k = 1;
        System.out.println(kSmallestPairs(nums1, nums2, k)); // [[1,3],[2,3]]

        // Example 4: Edge case
        nums1 = new int[]{1, 2, 4, 5};
        nums2 = new int[]{3};
        k = 2;
        System.out.println(kSmallestPairs(nums1, nums2, k)); // [[1,3],[2,3]]

    }
}