package odd_occurrences_in_array;

import java.util.Arrays;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 * 
 * Idea: Simple. Sort the given array. Traverse the array, count the repetitions 
 * and stop as soon as you find an element which is repeated odd number of times.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();

    int arr[] = new int[1000000];
    int value = 1;

    // Preparing test data
    for (int i = 0; i < arr.length - 1; i++) {
      arr[i] = value;
      if ((i + 1) % 2 == 0) {
        value++;
      }
    }
    arr[arr.length - 1] = value + 10;

    solution.solution(arr);
  }

  public int solution(int[] A) {
    // We sort the array in ascending order
    Arrays.sort(A);

    int count = 0;
    int previous = -1;

    // We traverse the sorted array and return as soon as we find the first odd occurrences
    for (int i = 0; i < A.length; i++) {
      if (previous == A[i]) {
        count++;
      }
      else {
        if (count % 2 == 1) {
          return previous;
        }
        previous = A[i];
        count = 1;
      }
    }

    if (count % 2 == 1) {
      return previous;
    }

    return 0;
  }
}
