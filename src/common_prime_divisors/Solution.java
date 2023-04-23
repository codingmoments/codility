package common_prime_divisors;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/12-euclidean_algorithm/common_prime_divisors/
 * 
 * Idea:
 * 
 * The two numbers 'x' and 'y' will have common set of prime divisors 
 * when their GCD contains all other remaining prime divisors for both 'x' and 'y'.
 * 
 * Suppose the GCD of 'x' and 'y' is 'g'. 
 * Then the numbers 'x / g' and 'y / g' should only have prime divisors present in 'g'.
 * 
 * Let, a = x / gcd.
 * 
 * To decide if 'a' only has prime divisors present in 'g', we find GCD 'restGCD' of 'a' and 'g'.
 * If it is 1, then that means 'a' only has prime divisors present in 'g'.
 * If it is not 1, then we find GCD of 'a / restGCD' and 'g'.
 * And we keep continuing this till the final GCD comes to 1.
 * 
 * For optimization, instead of checking this individually and separately for 'a/g' and 'y/g',
 * we just multiply them and check if the product has only prime numbers present in 'g'.
 * 
 * @formatter:on
 */
public class Solution {
  public int solution(int[] A, int[] B) {
    int total = 0;

    for (int i = 0; i < A.length; i++) {
      total += isCPDMatching(A[i], B[i]);
    }

    return total;
  }

  private int isCPDMatching(int x, int y) {
    // Find the GCD. GCD will have all matching prime numbers
    long gcd = gcd(x, y, 1);

    // Find the multiple of remaining divisors
    long restMuliple = (x / gcd) * (y / gcd);

    // Check if the remaining divisors already exist in GCD
    while (true) {
      long restGCD = gcd(gcd, restMuliple, 1);
      if (restGCD == 1 && restGCD != restMuliple) {
        return 0;
      }
      restMuliple = restMuliple / restGCD;
      if (restMuliple == 1) {
        return 1;
      }
    }
  }

  private long gcd(long a, long b, long res) {
    if (a == b) {
      return a * res;
    }
    else if (a % 2 == 0 && b % 2 == 0) {
      return gcd(a / 2, b / 2, res * 2);
    }
    else if (a % 2 == 0) {
      return gcd(a / 2, b, res);
    }
    else if (b % 2 == 0) {
      return gcd(a, b / 2, res);
    }
    else if (a > b) {
      return gcd(a - b, b, res);
    }
    else {
      return gcd(a, b - a, res);
    }
  }
}
