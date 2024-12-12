import static leetcode.EasyTwentyToThirty.*;

public class Main {
    public static void main(String[] args) {
        int[][] arr1 = new int[][]{{3, 4}, {2, 3}, {1, 2}};
        int[][] arr2 = new int[][]{{1, 2}, {2, 3}, {0, 1}, {3, 4}};
        int[][] arr3 = new int[][]{{1, 1}, {3, 4}};
        int[][] floods = new int[][]{
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}};
        System.out.println(pacificAtlantic(floods));
    }

}