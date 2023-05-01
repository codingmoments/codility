package ladder;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/13-fibonacci_numbers/ladder/
 * 
 * Idea:
 * 
 * The number of different ways of climbing to the top of the ladder with N rings 
 * is F(N+1) i.e. the (N+1)th Fibonacci number.
 * 
 * How?
 * You can climb to a rung at position 'p' from either the rung at position 'p-1' 
 * or the rung at position 'p-2'.
 * 
 * This means, the number of different ways to reach 'p' is sum of 
 * number of different ways to reach 'p-1' and number of different ways to reach 'p-2'.
 * 
 * N(p) = N(p-1) + N(p-2)
 * 
 * This is a Fibonacci series.
 * 
 * For example, let's say that the ladder has 3 rungs.
 * You can reach to rung 1 in 1 way (0 -> 1)
 * You can reach to rung 2 in 2 ways (0 -> 2, 1 -> 2)
 * You can reach to rung 3 as below:
 * - If you reach rung 3 from rung 1, then total number of ways = 1 (0 -> 1 -> 3)
 * - If you reach rung 3 from rung 2, then total number of ways = 2 (0 -> 2 -> 3, 1 -> 2 -> 3)
 * 
 * That means,
 * Total number of ways to reach 3 = Total number of ways to reach 1 + Total number of ways to reach 2
 * 
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    Solution sol = new Solution();

    int[] A = { 4, 4, 5, 5, 1 };
    int[] B = { 3, 2, 4, 3, 1 };

    int[] result = sol.solution(A, B);

    for (int i : result) {
      System.out.print(i + ", ");
    }
  }

  public int[] solution(int[] A, int[] B) {
    int maxRung = A[0];

    for (int i : A) {
      if (maxRung < i) {
        maxRung = i;
      }
    }

    int maxModulo = B[0];

    for (int i : B) {
      if (maxModulo < i) {
        maxModulo = i;
      }
    }

    int[] fibonacciArray = generateFibonacciModulo(maxRung + 1, maxModulo);

    for (int index = 0; index < A.length; index++) {
      //@formatter:off
      // maxModulo = Math.pow(2, 30)
      // anyOtherModulo = Math.pow(2, 7)
      // Math.pow(2, 30) will always be divisible by Math.pow(2, 7)

      // This means that, N % Math.pow(2, 30) will either be 0 or
      // any other number whose modulo of Math.pow(2, 7) is same as N % Math.pow(2, 7).
      // N % Math.pow(2, 7) = N % Math.pow(2, 30) % Math.pow(2, 7)

      // For example:
      // 107 % Math.pow(2, 2) = 107 % Math.pow(2, 4) % Math.pow(2, 2)
      //              107 % 4 = 107 % 16 % 4
      //                    3 = 11 % 4
      //                    3 = 3
      //@formatter:on
      A[index] = fibonacciArray[A[index] - 1] % (int) Math.pow(2, B[index]);
    }

    return A;
  }

  private int[] generateFibonacciModulo(int rungSize, int maxModulo) {
    // Since the Fibonacci numbers can become too large,
    // it might overflow the range of integer.
    // We use one trick here to avoid this problem.
    // Instead of storing fibonacci number 'f' as it is,
    // we store 'f' % maxModulo

    int[] fibonacciArray = new int[rungSize];

    fibonacciArray[0] = 1 % (int) Math.pow(2, maxModulo);
    fibonacciArray[1] = 2 % (int) Math.pow(2, maxModulo);

    int index = 2;
    while (index < rungSize) {
      // Math rule for modulo:
      // (a + b) % c = ((a % c) + (b % c)) % c
      // So:
      // F(n) % maxModulo = ((F(n-1) % maxModulo) + (F(n-2) % maxModulo)) % modulo
      int nextFibonnaci = (fibonacciArray[index - 1] + fibonacciArray[index - 2]);
      fibonacciArray[index] = nextFibonnaci % (int) Math.pow(2, maxModulo);
      index++;
    }

    return fibonacciArray;
  }
}
