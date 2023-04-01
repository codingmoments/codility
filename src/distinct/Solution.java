package distinct;

import java.util.Arrays;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/6-sorting/distinct/
 * 
 * Idea: 
 * Sort the integer array. 
 * Traverse through the sorted array to count distinct values.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    if (A.length == 0) {
      return 0;
    }

    Arrays.sort(A);

    int distinctValues = 1;
    for (int i = 1; i < A.length; i++) {
      if (A[i] != A[i - 1]) {
        distinctValues++;
      }
    }

    return distinctValues;
  }

}
