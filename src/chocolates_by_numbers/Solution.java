package chocolates_by_numbers;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/12-euclidean_algorithm/chocolates_by_numbers/
 * 
 * Idea:
 * 
 * Let's take a simple example to start with.

 * The chocolates are arranged in a circle, so we will eat them in the below order.
 * 
 * (N = 10) 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 ......
 * (M = 3)  x     x     x     x     x     x     x     x     x     x     x
 * (M = 4)  x       x       x       x       x       x
 * (M = 5)  x         x         x
 * 
 * We stop eating when we reach the same location we visited before.
 * 
 * The key point here is that the location from where we start eating chocolates
 * will be the first location that will be visited twice.
 * 
 * The number of locations that we travel through to reach the starting location 
 * again will be the lowest common denominator of total number of locations (N)
 * and the size of each step (M).
 * 
 * Number of locations traveled = LCM(N, M)
 * 
 * And, number of chocolates eaten = number of locations traveled / size of each step
 *                                 = LCM(N, M) / M
 *                                 
 * For example, if total number of locations are 20, and size of each step is 4,
 * then you will eat 20/4 chocolates.
 * 
 * @formatter:on
 */
public class Solution {

  public int solution(int N, int M) {
    return (int) lcm(N, M) / M;
  }

  private long lcm(int N, int M) {
    long gcd = gcd(N, M, 1);

    // You can also write it like:
    // (M * N) / gcd
    // But M * N can go beyond the limit of integer
    return (M / gcd) * N;
  }

  private long gcd(long N, long M, long res) {
    if (N == M) {
      return N * res;
    }
    else if (N % 2 == 0 && M % 2 == 0) {
      return gcd(N / 2, M / 2, res * 2);
    }
    else if (N % 2 == 0) {
      return gcd(N / 2, M, res);
    }
    else if (M % 2 == 0) {
      return gcd(N, M / 2, res);
    }
    else if (N > M) {
      return gcd(N - M, M, res);
    }
    else {
      return gcd(N, M - N, res);
    }
  }
}
