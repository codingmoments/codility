package max_double_slice_sum;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 * 
 * Idea:
 * Refer https://rafal.io/posts/codility-max-double-slice-sum.html
 * 
 * First, we safely ignore A[0] and A[N-1] since by definition they cannot be part of any double-slice sum.
 * 
 * Read the description: "The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y - 1] + A[Y + 1] + A[Y + 2] + ... + A[Z - 1]."
 * 
 * We find the maximum slice sum from two directions. 
 * maxSliceLeft[i] is the maximum slice sum ending at index i.
 * maxSliceRight[i] is the maximum slice sum starting at index i.
 * 
 * Then, we find the maximum sum of maxSliceLeft[i] + maxSliceRight[i].
 * 
 * @formatter:on
 */
public class Solution {
  public int solution(int[] A) {
    int[] maxSliceLeft = new int[A.length];
    int[] maxSliceRight = new int[A.length];

    // Calculating maximum slice sum ending at index i
    // Unlike the problem max_slice_sum, we do not use Math.max(maxSliceLeft[i - 1] + A[i], A[i]).
    // This is because, the sum of double slice (x, y, z) is: A[x+1] + A[x+2] + ... + A[y-1] + A[y+1] + A[y+2] + ... + A[z-1]
    // This means in worst case, the maxSlice will be 0 by using double slice (n, n+1, n+2)
    for (int i = 1; i < A.length - 1; i++) {
      maxSliceLeft[i] = Math.max(maxSliceLeft[i - 1] + A[i], 0);
    }

    // Calculating maximum slice sum starting at index i
    for (int i = A.length - 2; i > 0; i--) {
      maxSliceRight[i] = Math.max(+ A[i] + maxSliceRight[i + 1], 0);
    }

    int maxSlice = 0;

    // Find the index where sum of left and right slices is max
    for (int i = 1; i < A.length - 1; i++) {
      maxSlice = Math.max(maxSlice, maxSliceLeft[i - 1] + maxSliceRight[i + 1]);
    }

    return maxSlice;
  }

}
