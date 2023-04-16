package count_non_divisible;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_non_divisible/
 * 
 * Idea:
 * 
 * Though this program comes under the section Sieve of Eratosthenes, 
 * we do not require to apply that sieve logic in this program.
 * 
 * We use straightforward approach of calculating the number of non-divisors 
 * for each element in the array by checking all other elements in the array.
 * 
 * However, we will use some known techniques to optimize this counting. 
 * Here is how we will write the program:
 * 
 * We will first find the maximum integer 'maxInteger' from the given array A.
 * This is needed to decide the size of the counter array.
 * 
 * Then we will count the elements and store the counters in the 'counterArray'.
 * 
 * During calculation, we will be using the 'counterArray' to determine the number of non-divisors.
 * To store the non-divisors count for 'counterArray', we will create an array 'nonDivisorsCountPerCounterArray'.
 * 
 * Similarly, to store the number of divisors for 'counteryArray', we will create an array 'divisorsCountPerCounterArray'.
 * 
 * Then we traverse 'counterArray' and 
 * - For each number 'i' we process below steps:
 *   - If the index number is present in given array A
 *     - For each divisor 'divisorIndex' from 1 to SQRT(i)
 *       - We check if 'i' is divisible by 'divisorIndex'.
 *       - If yes, then we check if 'divisorIndex' exists in given array A
 *         - If it exists
 *           - Then we increment the corresponding index value in the array 'divisorsCountPerCounterArray' 
 *             by the number of occurrences of 'divisorIndex' in array A.
 *           - We then check if the symmetric divisor also exists in the given array A,
 *             except in the situations where the 'divisorIndex' is a square root of 'i' or 'divisorIndex' is 1.
 *           - If symmetric divisor exits
 *             - Then we increment the corresponding index value in the array 'divisorsCountPerCounterArray' 
 *               by the number of occurrences of symmetric divisor in array A.
 *   - At this point, 'divisorsCountPerCounterArray' will contain total number of divisors for 'i'
 *   - To calculate number of non-divisors, we subtract total number of divisors 
 *     and the occurrences of 'i' from total number of elements in the given array A.
 *   - We store that result in the array 'nonDivisorsCountPerCounterArray'
 *   
 * - Then, to generate the final result, we create an array 'finalResult'
 * - For each element in array A, we populate the corresponding element in 'finalResult' 
 *   using the values from 'nonDivisorsCountPerCounterArray'.
 * 
 * @formatter:on
 */
public class Solution {

  public int[] solution(int[] A) {
    // We will first find the maximum integer 'maxInteger' from the given array A
    int maxInteger = 0;
    for (int a : A) {
      maxInteger = Math.max(maxInteger, a);
    }

    // We will count the elements and store the counters in the 'counterArray'.
    int[] counterArray = new int[maxInteger + 1];
    for (int a : A) {
      counterArray[a] += 1;
    }

    // To store the non-divisors count for 'counterArray',
    // we will create an array 'nonDivisorsCountPerCounterArray'.
    int[] nonDivisorsCountPerCounterArray = new int[maxInteger + 1];

    // To store the number of divisors for 'counteryArray',
    // we will create an array 'divisorsCountPerCounterArray'.
    int[] divisorsCountPerCounterArray = new int[maxInteger + 1];

    // For each element in counteryArray
    for (int i = 1; i < counterArray.length; i++) {
      // If the index number is present in given array A
      if (counterArray[i] > 0) {

        // Start with divisor 1
        int divisorIndex = 1;

        // For each divisor 'divisorIndex' from 1 to SQRT(i)
        while (divisorIndex * divisorIndex <= i) {
          if (i % divisorIndex == 0) {
            // Check if 'divisorIndex' exists in given array A
            if (counterArray[divisorIndex] > 0) {
              // We increment the corresponding index value in the array 'divisorsCountPerCounterArray'
              // by the number of occurrences of 'divisorIndex' in array A.
              if (divisorIndex != i) {
                divisorsCountPerCounterArray[i] += counterArray[divisorIndex];
              }
            }
            // We then check if the symmetric divisor also exists in the given array A,
            // except in the situations where the 'divisorIndex' is a square root of 'i' or 'divisorIndex' is 1.
            if (divisorIndex != 1 && divisorIndex * divisorIndex != i && counterArray[i / divisorIndex] > 0) {
              // Then we increment the corresponding index value in the array 'divisorsCountPerCounterArray'
              // by the number of occurrences of symmetric divisor in array A.
              divisorsCountPerCounterArray[i] += counterArray[i / divisorIndex];
            }
          }
          divisorIndex++;
        }

        // To calculate number of non-divisors, we subtract total number of divisors
        // and the occurrences of 'i' from total number of elements in the given array A.
        nonDivisorsCountPerCounterArray[i] = A.length - divisorsCountPerCounterArray[i] - counterArray[i];
      }
    }

    int[] finalResult = new int[A.length];

    for (int i = 0; i < A.length; i++) {
      finalResult[i] = nonDivisorsCountPerCounterArray[A[i]];
    }
    return finalResult;
  }

}
