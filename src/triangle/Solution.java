package triangle;

import java.util.Arrays;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/6-sorting/triangle/
 * 
 * Idea:
 * Sort the array.
 * 
 * After sorting, let's say we have a, b, c. 
 * In a sequence of increasing order, it will be always like: a > b > c .
 * By default this means: 
 * a + c > b
 * b + c > a
 * So, we only need to check if: a + b > c
 * 
 * Let's take an example. Let's say we have a SORTED array : n1, n2, n3, n4, n5, n6.
 *
 * We start from the last element and moves towards left of the array.
 *
 * We first check if n4 > n5 + n6.
 * If this is true, then we have found the triangle.
 * If this is false, then it also means that n3 > n5 + n6 is also false, as n3 is less than n4. Same is true for all remaining elements.
 * So, we then check if n3 > n4 + n5. We do not check n3 > n4 + n6. Because, n5 < n6. So n3 > n4 + n6 can be false but n3 > n4 + n5 can be true.
 * 
 * Hence, to improve performance and to get time complexity of O(1), we just compare: A[i] > A[i + 2] - A[i + 1]
 * To keep program simple, we traverse from first element towards right.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    // Sort the array
    Arrays.sort(A);

    for (int i = 0; i < A.length - 2; i++) {
      // It is not possible to achieve the solution with negative number in the triplet.
      // This is because adding negative only reduces the value instead of increasing the value, while we want the sum should be greater than the third value.
      if (A[i] >= 0) {
        if (A[i] > A[i + 2] - A[i + 1]) {
          return 1;
        }
      }
    }

    return 0;
  }

}
