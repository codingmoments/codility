package min_perimeter_rectangle;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/min_perimeter_rectangle/
 * 
 * Idea:
 * For the given area, square has the smallest perimeter. You can find the proof below:
 * 
 * https://www.stumblingrobot.com/2015/10/01/prove-that-the-square-has-the-smallest-perimeter-of-all-rectangles-of-a-given-area/
 * 
 * If it is not a square, then we find for the length that is closest to the square. 
 * 
 * @formatter:on
 */
public class Solution {
  public int solution(int N) {

    int startingDivisor = (int) Math.ceil(Math.sqrt(N));

    // We start from the integer closes to the square root of N (side of the square).
    // And go backwards till we find the divisor.
    for (int divisor = startingDivisor; divisor > 0; divisor--) {
      if (N % divisor == 0) {
        return 2 * (divisor + (N / divisor));
      }
    }

    return 1;
  }
}
