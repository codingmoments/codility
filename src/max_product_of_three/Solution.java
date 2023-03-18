package max_product_of_three;

import java.util.Arrays;

/**
 * Solution for Codility lesson: https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/
 * 
 * Idea: Sort the array. If all the elements in the array are negative or all are positive, then max product is the product of last 3 integers.
 * 
 * If the array contains both positive and negative numbers, then compare the product A[N - 1] * A[N - 2] * A[N - 3] with the product A[0] * A[1] * A[N -1].
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {

    int N = A.length;

    Arrays.sort(A);

    // If all the elements in the array are negative or all are positive, then max product is the product of last 3 integers.
    if (A[N - 1] < 0 || A[0] >= 0) {
      return A[N - 1] * A[N - 2] * A[N - 3];
    }
    // If the array contains both positive and negative numbers,
    // then compare the product A[N - 1] * A[N - 2] * A[N - 3] with the product A[0] * A[1] * A[N -1].
    else {
      return Math.max(A[N - 1] * A[N - 2] * A[N - 3], A[0] * A[1] * A[N - 1]);
    }

  }

}
