package count_div;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/
 * 
 * Idea:
 * Find the first integer >= A that is divisible by K.
 * Find the difference between firstDivisible and B.
 * The number of integers divisible by K = (difference / K) + 1
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int A, int B, int K) {
    // Find the first integer >= A that is divisible by K
    int firstDivisible = A;

    if (A % K != 0) {
      firstDivisible = A + (K - A % K);
    }

    if (firstDivisible > B) {
      return 0;
    }

    // Find the difference between firstDivisible and B
    int difference = B - firstDivisible;

    // The number of integers divisible by K
    return difference / K + 1;
  }
}
