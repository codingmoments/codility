package max_slice_sum;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
 * 
 * Idea:
 * This is basically the same proble explained in learning material:
 * https://codility.com/media/train/7-MaxSlice.pdf
 * 
 * @formatter:on
 */
public class Solution {
  public int solution(int[] A) {
    int maxSliceSum = A[0];
    int maxEnding = A[0];

    for (int i = 1; i < A.length; i++) {
      // If we assume that the maximum sum of a slice ending in position i equals maxEnding, 
      // then the maximum slice ending in position [i+1] equals maxEnding + A[i + 1].
      // But if this result is less than the value at A[i + 1], then maxEnding becomes A[i].
      maxEnding = Math.max(maxEnding + A[i], A[i]);

      // Maximum slice sum so far is maximum of slice sums at all indices
      maxSliceSum = Math.max(maxSliceSum, maxEnding);
    }

    return maxSliceSum;
  }
}
