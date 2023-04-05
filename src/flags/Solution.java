package flags;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/
 * 
 * Idea: 
 * 
 * It's simple. We just need to keep iterating the number of flags starting from 1 flag
 * and then check what can be the maximum number of flags that we can put with given conditions.
 * 
 * However, to optimize the program, we do not need to iterate the number of flags for all the peaks.
 * 
 * Let's ignore the peaks for a moment. Say we have N points in the given array A.
 * 
 * By definition, we cannot put flags at first and last points (as they can never be peaks).
 * So, total number of available points become N - 2.
 * 
 * If we just think that we can place flags at all these points, and assume that the total number of flags that we can
 * put is F with distance of F between two consecutive flags.
 * 
 * Then, F * F = N - 2.
 *       F = SQRT(N - 2)
 * This is the maximum number of flags that we can put for N number of points.
 * 
 * However, because square root will not always return an integer, we can try to check if we can put one more additional flag.
 * So,  F <= SQRT(N - 2) + 1
 * 
 * For example, if N = 16.
 * 
 * 0---1---2---3---4---5---6---7---8---9---10---11---12---13---14---15
 * 
 * We cannot put flags at 0 and 15. So, the available points become:
 * 
 * 1---2---3---4---5---6---7---8---9---10---11---12---13---14
 * 
 * Then, we can place maximum of SQRT(14-2) + 1 = SQRT(12) + 1 = 4 flags
 * These four flags will be put at position 1, 5, 9 and 13.
 * 
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] A = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
    s.solution(A);
  }

  public int solution(int[] A) {

    int[] peakIndexes = new int[A.length];

    // Collecting the peak indexes
    for (int i = 1; i < A.length - 1; i++) {
      if (A[i] > A[i + 1] && A[i] > A[i - 1]) {
        peakIndexes[i] = 1;
        // Skip the next number as it cannot be peak
        i++;
      }
    }

    int[] neaxtPeakIndexes = new int[peakIndexes.length];

    int lastPeakIndex = peakIndexes.length;

    // Storing index of next peak for given point
    // We will use this array for quick navigation from one peak to next peak.
    for (int i = peakIndexes.length - 1; i >= 0; i--) {
      neaxtPeakIndexes[i] = lastPeakIndex;
      if (peakIndexes[i] == 1) {
        lastPeakIndex = i;
      }
    }

    int maxFlags = 0;

    // Let's start with 1 flag
    int flagCount = 1;

    // Starting with 1 flagCount, check how many flags can we host
    // The maximum number of flags cannot be more than SQRT(N-2) + 1
    while (flagCount <= Math.sqrt(A.length - 2) + 1) {

      int flagsHosted = 0;
      boolean maxFlagFound = true;

      // For each peak index, host the flag if the difference is more than flagCount
      for (int i = 0; i < peakIndexes.length;) {
        if (peakIndexes[i] == 1) {
          flagsHosted++;
          i += flagCount;

          if (flagsHosted > flagCount) {
            // This means you can host more flags than flagCount
            // Let's increase flagCount by 1
            flagCount++;
            maxFlagFound = false;

            maxFlags = Math.max(maxFlags, flagsHosted - 1);

            break;
          }
        }
        else {
          i = neaxtPeakIndexes[i];
        }
      }

      if (maxFlagFound) {
        maxFlags = Math.max(maxFlags, flagsHosted);
        return maxFlags;
      }
    }

    return maxFlags;
  }
}
