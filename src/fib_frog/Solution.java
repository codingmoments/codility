package fib_frog;

import java.util.ArrayList;
import java.util.List;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/13-fibonacci_numbers/fib_frog/
 * 
 * Idea:
 * 
 * This is case of dynamic programming. We divide the bigger problem into smaller sub-problems.
 * And we memoize the results of smaller sub-problems.
 * 
 * Minimum number of steps required to reach index 'y' = Minimum number of steps required to reach the earlier index 'x' + 1
 * 
 * For example, minimum number of steps required to reach a position with leaf at index 2 will be 1. 
 * You can jump 3 steps (which is Fibonacci) from the starting position to this position. So minimum steps for index 2 will be 1.
 * 
 * And, if you can jump with Fibonacci steps from index 2 to the end position, then the number of steps to reach the end position would be 1 (from index 2) + 1 = 2. 
 * 
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    int[] A = { 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 };

    Solution sol = new Solution();
    System.out.println(sol.solution(A));
  }

  public int solution(int[] A) {

    Integer[] fibonacciNumbers = generateFibonacciNumbers(A.length + 1);

    // Since the frog is starting from the position before the first element of the array,
    // and wants to reach to the position after the last element in the array,
    // we create a new array to maintain these extra positions
    int[] positions = new int[A.length + 2];

    for (int index = 0; index < A.length; index++) {
      positions[index + 1] = A[index];
    }

    // Counter array for storing the minimum number of steps needed to reach a leaf at specific index
    int[] minimumStepsCounterArray = new int[positions.length];

    // Traversing the leaves starting from current position of the frog to end position
    for (int sourceIndex = 0; sourceIndex < minimumStepsCounterArray.length; sourceIndex++) {
      // Frog can jump from sourceIndex only when
      // either it is a starting position or it has reached to this index from previous indexes
      if (sourceIndex == 0 || minimumStepsCounterArray[sourceIndex] > 0) {
        // Frog can jump only when the distance is a Fibonacci number
        for (int fibIndex = 0; fibIndex < fibonacciNumbers.length; fibIndex++) {
          int destIndex = sourceIndex + fibonacciNumbers[fibIndex];
          // If the destination index has a leaf
          // or the destination index is the end position
          if (destIndex < positions.length && (positions[destIndex] == 1 || destIndex == positions.length - 1)) {
            // If the frog is reaching this destination first time,
            // then the number of steps will be the number of steps from sourceIndex plus one
            if (minimumStepsCounterArray[destIndex] == 0) {
              minimumStepsCounterArray[destIndex] = minimumStepsCounterArray[sourceIndex] + 1;
            }
            // Else the number of steps will be the minimum of existing and the new ones
            else {
              minimumStepsCounterArray[destIndex] = Math.min(minimumStepsCounterArray[destIndex], minimumStepsCounterArray[sourceIndex] + 1);
            }

            // If the frog has crossed the river in one jump, then we are done!
            if (destIndex == minimumStepsCounterArray.length - 1 && minimumStepsCounterArray[minimumStepsCounterArray.length - 1] == 1) {
              return 1;
            }
          }
        }
      }
    }

    return minimumStepsCounterArray[minimumStepsCounterArray.length - 1] == 0 ? -1 : minimumStepsCounterArray[minimumStepsCounterArray.length - 1];
  }

  private Integer[] generateFibonacciNumbers(int maxNumber) {
    List<Integer> fibonacciList = new ArrayList<>();

    fibonacciList.add(0);
    fibonacciList.add(1);

    int index = 1;
    while (fibonacciList.get(index) < maxNumber) {
      int nextFibonnaci = fibonacciList.get(index) + fibonacciList.get(index - 1);
      if (nextFibonnaci <= maxNumber) {
        fibonacciList.add(nextFibonnaci);
        index++;
      }
      else {
        break;
      }
    }

    return fibonacciList.toArray(new Integer[fibonacciList.size()]);
  }
}
