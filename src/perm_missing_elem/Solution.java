package perm_missing_elem;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
 * 
 * Idea:
 * Calculate sum of all numbers in the array.
 * The expected sum of consecutive numbers from 1 to N is N(N+1)/2.
 * Find the difference between the actual sum and expected sum.
 * Missing number = array length + 1 - difference between sums.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    // Worst case
    int[] array = new int[100000];
    // Adding numbers from 1 to 100000
    for (int i = 0; i < array.length; i++) {
      array[i] = i + 1;
    }
    // Shuffling the array
    shuffleArray(array);
    // Replace an existing element with 100001
    System.out.println("Expected missing number - " + array[100]);
    array[100] = 100001;
    int missingNumber = solution.solution(array);
    System.out.println("Found missing number - " + missingNumber);
  }

  private static void shuffleArray(int[] ar) {
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }

  /**
   * Lesson solution
   */
  public int solution(int[] A) {
    long actualSum = 0;
    for (int i : A) {
      actualSum += i;
    }
    long expectedSum = (A.length * 1L * (A.length * 1L + 1)) / 2L;
    long difference = actualSum - expectedSum;

    return (int) (A.length + 1 - difference);
  }
}
