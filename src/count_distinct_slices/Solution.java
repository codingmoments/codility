package count_distinct_slices;

import java.util.HashSet;
import java.util.Set;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/
 * 
 * Idea:
 * 
 * Start the slice from 0th element.
 * 
 * Increment the front (end) of the slice by 1.
 * If the slice already does not contain the front element, add the number of slices that could be formed.
 * If the slice already contains the front element, shift the back of the slice ahead until it crosses the earlier existence of the front element.
 * 
 * @formatter:on
 */
public class Solution {
  public static void main(String[] args) {
    int[] A = { 3, 4, 5, 5, 2 };
    Solution s = new Solution();
    System.out.println(s.solution(6, A));
  }

  public int solution(int M, int[] A) {
    Set<Integer> slice = new HashSet<>();

    int sliceStart = 0;
    slice.add(A[sliceStart]);
    int distinctSliceCount = 1;

    int sliceEnd = 1;

    while (sliceEnd < A.length && sliceStart < A.length) {

      if (!slice.contains(A[sliceEnd])) {
        slice.add(A[sliceEnd]);
        sliceEnd++;
        distinctSliceCount += slice.size();
        if (distinctSliceCount > 1_000_000_000) {
          return 1_000_000_000;
        }
      }
      else {
        slice.remove(Integer.valueOf(A[sliceStart]));
        sliceStart++;
      }
    }

    return distinctSliceCount;
  }
}
