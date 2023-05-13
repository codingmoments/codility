package abs_distinct;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/abs_distinct/
 * 
 * Idea:
 * This problem can be solved easily with single line of code:
 * 
 * Arrays.stream(A).map(i -> i > 0 ? i : -i).distinct().count()
 * 
 * But, we want to use caterpillar method here.
 * We keep the two pointers - one at the beginning and other at the end of the array.
 * We keep comparing the absolute value of the elements at two ends.
 * If the head element is larger than the tail element, we increment the head element.
 * If the tail element is larger than the head element, we decrement the tail element.
 * If both are same, we move both the pointers.
 * 
 * In addition to this, if the consecutive elements are same, then we keep moving the pointers.
 * 
 * @formatter:on
 */
public class Solution {
  public static void main(String[] args) {
    int[] A = { 1, 2, 3, 4, 4 };

    Solution solution = new Solution();
    System.out.println(solution.solution(A));
  }

  public int solution(int[] A) {
    int headPointer = 0;
    int tailPointer = A.length - 1;

    int distinctCount = 0;

    while (tailPointer >= headPointer) {
      long headValue = Math.abs((long) A[headPointer]);
      long tailValue = Math.abs((long) A[tailPointer]);

      distinctCount++;

      if (headValue == tailValue) {
        if (A[headPointer] == A[tailPointer]) {
          return distinctCount;
        }
        int prevValue = A[tailPointer];
        tailPointer--;
        while (tailPointer > 0 && prevValue == A[tailPointer]) {
          tailPointer--;
        }

        prevValue = A[headPointer];
        headPointer++;
        while (headPointer < A.length && prevValue == A[headPointer]) {
          headPointer++;
        }
      }
      else if (headValue < tailValue) {
        int prevValue = A[tailPointer];
        tailPointer--;
        while (tailPointer >= 0 && prevValue == A[tailPointer]) {
          tailPointer--;
        }
      }
      else if (headValue > tailValue) {
        int prevValue = A[headPointer];
        headPointer++;
        while (headPointer < A.length && prevValue == A[headPointer]) {
          headPointer++;
        }
      }
    }

    return distinctCount;
  }
}
