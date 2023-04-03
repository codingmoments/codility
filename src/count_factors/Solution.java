package count_factors;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/
 * 
 * Idea:
 * This is explained in the learning material at:
 * https://codility.com/media/train/8-PrimeNumbers.pdf
 * 
 * @formatter:on
 */
public class Solution {
  public int solution(int N) {
    int result = 0;
    int divisor = 1;

    // Traverse all the numbers from 1 to square root of N
    while (divisor < Math.sqrt(N)) {
      // If N is divisible by divisor, increase the result by 2 as there is a symmetric divisor
      if (N % divisor == 0) {
        result += 2;
      }
      divisor += 1;
    }

    // If the divisor is square root of N, increase the result by 1
    if (divisor == Math.sqrt(N)) {
      result += 1;
    }

    return result;
  }
}
