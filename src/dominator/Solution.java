package dominator;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/8-leader/dominator/
 * 
 * Idea: This program is already explained in the learning material-
 * https://codility.com/media/train/6-Leader.pdf
 * 
 * We push the integers into a stack. Before pushing we check:
 * - If the previous integer in the stack is same as current integer, we push the integer.
 * - If the previous integer in the stack is not same as current integer, we remove the previous integer.
 * 
 * At the end, stack will have all equal integers.
 * We then count them, if total count is greater than half the size of the array, then that integer is the denominator.
 * 
 * Since we only need to keep track of previous element, we actually do not require the stack data structure.
 * @formatter:on
 */
public class Solution {
  public int solution(int[] A) {
    int stackSize = 0;
    int lastElementInStack = 0;

    for (int a : A) {
      // If stack is empty, add the element into the stack
      if (stackSize == 0) {
        lastElementInStack = a;
        stackSize++;
      }
      else {
        // If the previous integer in the stack is same as current integer, we push the integer.
        if (a == lastElementInStack) {
          // If it matches, then add the element into the stack
          stackSize++;
        }
        else {
          // If the previous integer in the stack is not same as current integer, we remove the previous integer.
          stackSize--;
        }
      }
    }

    if (stackSize > 0) {
      int count = 0;
      for (int i = 0; i < A.length; i++) {
        if (A[i] == lastElementInStack) {
          count++;

          if (count > A.length / 2) {
            return i;
          }
        }
      }
    }

    return -1;
  }
}
