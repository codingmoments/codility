package count_semiprimes;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_semiprimes/
 * 
 * Idea:
 * We are going to calculate subprimes by multiplying each prime number with rest of prime number
 * from the given array.
 * 
 * For optimization, we are going to use multiple techniques -- sieve of Eratosthenes, counter array, and prefix sum.
 * 
 * At a high level:
 * - We first find all the prime factors till the given number N, using sive of Eratosthenes technique.
 * - We store these prime factors using counter array technique.
 * - We then calculate subprimes by multiplying each primer factor with other prime factors.
 * - We store these these subprimes using counter array technique.
 * - We then calculate prefix sum for subprimes using prefix sum technique.
 * - We then get the final result using prefix sum.
 * 
 * Thought process:
 * 1. We want to calculate the number of sub-primes between two numbers P[i] and Q[i].
 * 2. This means we want to calculate all sub-primes.
 * 3. This means we should calculate all prime numbers first.
 * 4. Then, using combination of two prime numbers, we calculate sub-primes.
 * 5. We then count the number of sub-primes between two numbers P[i] and Q[i].
 * 
 * Then we introduce optimizations and apply the learned techniques at each step.
 * 
 * @formatter:on
 */
public class Solution {

  public int[] solution(int N, int[] P, int[] Q) {
    // Array containing the results
    // The length of the array should be equal to length of P (or Q),
    // as we are going to find subprime for every element from array P.
    int[] result = new int[P.length];

    // We first find all the prime factors till the given number N, using sive of Eratosthenes technique.
    // Actually, we do not need to find all prime numbers till N, we only need to find prime numbers till N / 2.
    // Why? We want to find subprimes that are multiple of two primes. And that subprime should be less than or equal to N.
    // The lowest prime factor of N can be 2. That means the highest prime factor can be N / 2.
    // For example, if N = 100. Then the highest prime factor can be 47.
    // If we use the prime factor 53, then multiplying it with 2 will result in 106, which is greater than 100.

    // We store these prime factors using counter array technique.
    // The array 'primeNumberCounterArray' is the counter array for storing prime numbers.
    int[] primeNumberCounterArray = new int[(int) Math.ceil(N / 2.0) + 1];
    for (int i = 0; i < primeNumberCounterArray.length; i++) {
      primeNumberCounterArray[i] = 1;
    }

    // Applying sieve of Eratosthenes
    primeNumberCounterArray[0] = primeNumberCounterArray[1] = 0;
    int i = 2;
    while (i * i < primeNumberCounterArray.length) {
      if (primeNumberCounterArray[i] == 1) {
        int k = i * i;
        while (k < primeNumberCounterArray.length) {
          primeNumberCounterArray[k] = 0;
          k += i;
        }
      }
      i += 1;
    }

    // We then calculate subprimes by multiplying each primer factor with other prime factors.
    // We store these subprimes using counter array technique.
    int[] subprimesCounterArray = new int[N + 1];

    for (int p1 = 2; p1 < primeNumberCounterArray.length; p1++) {

      // When we reach a prime number whose square is greater than N, then we exit the loop.
      // Since the primeNumberCounterArray by nature is sorted (by index),
      // the lower numbers are already processed.
      // So, p1 * (p1 - x) was already processed when the loop was running for (p1 - x).
      if (p1 * p1 > N) {
        break;
      }

      if (primeNumberCounterArray[p1] == 1) {
        subprimesCounterArray[p1 * p1] = 1;

        // Since the primeNumberCounterArray by nature is sorted (by index),
        // the lower numbers are already processed.
        // So, p1 * (p1 - x) was already processed when the loop was running for (p1 - x).
        for (int p2 = p1 + 1; p2 < primeNumberCounterArray.length; p2++) {
          if (p1 * p2 > N) {
            break;
          }
          if (primeNumberCounterArray[p2] == 1) {
            subprimesCounterArray[p1 * p2] = 1;
          }
        }
      }
    }

    // We then calculate prefix sum for subprimes using prefix sum technique.
    int[] prefixSum = new int[N + 1];
    for (int k = 1; k < prefixSum.length; k++) {
      prefixSum[k] = prefixSum[k - 1] + subprimesCounterArray[k];
    }

    // We then get the final result using prefix sum.
    for (int k = 0; k < P.length; k++) {
      result[k] = prefixSum[Q[k]] - prefixSum[P[k]];
      if (subprimesCounterArray[P[k]] == 1) {
        result[k] += 1;
      }
    }

    return result;
  }
}
