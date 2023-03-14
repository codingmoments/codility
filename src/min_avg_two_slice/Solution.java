package min_avg_two_slice;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/
 * 
 * Idea:
 * There is a theorem that states that: AVG[0]...A[N] cannot be less than AVG[0]...A[k] and AVG[k+1]...A[N].
 * A composed slice will never have an average sum lower than its components.
 * So, just look for slice with 2 or 3 elements having minimum average.
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {

    int minimumStart = 0;
    // Since the maximum value of an integer in the array A is 10000
    // We are initializing minAverage to 20000.
    double minAverage = 20000;

    for (int i = 0; i < A.length - 1; i++) {
      // Slice with 2 elements
      double average = (A[i] + A[i + 1]) / 2.0;
      if (average < minAverage) {
        minAverage = average;
        minimumStart = i;
      }

      if (i + 2 < A.length) {
        // Slice with 3 elements
        average = (A[i] + A[i + 1] + A[i + 2]) / 3.0;
        if (average < minAverage) {
          minAverage = average;
          minimumStart = i;
        }
      }
    }

    return minimumStart;
  }
}