package equi_leader;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/8-leader/equi_leader/
 * 
 * Idea:
 * 
 * Let's say we have an array A which has a leader x.
 * Let's separate this array A into two arrays A[0]..A[i] and A[i+1]..A[n].
 * Let's assume that these two arrays have same leader y.
 * This means, y occurs more than (i+1)/2 times in first array, and y occurs more than (n-(i+1)+1)/2 times in second array.
 * This means, y occurs more than (i+1)/2 + (n-(i+1)+1)/2 times in whole array A.
 * This means, y occurs more than (i+1+n-i-1+1)/2 => (n+1/2) in whole array A.
 * This means, y = x.
 * 
 * This proves that if an array A has a leader x, and if two separation of array has same leader, then that leader is x.
 * 
 * After understanding this, we start with finding a leader for the given array.
 * And then we simply loop through array, keeping track of counter for the occurrences of leader in the two parts of the array to find the equi-leader. 
 * 
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
      int leaderCount = 0;

      // Counting the number of leader occurrences
      for (int i = 0; i < A.length; i++) {
        if (A[i] == lastElementInStack) {
          leaderCount++;
        }
      }

      // Return 0, if occurrences is less than n/2
      if (leaderCount < A.length / 2) {
        return 0;
      }

      int equiLeaders = 0;
      int prevLeadersCount = 0;

      // Loop through array, keeping track of counter for the occurrences of leader in the two parts of the array to find the equi-leader. 
      for (int i = 0; i < A.length; i++) {
        if (A[i] == lastElementInStack) {
          prevLeadersCount++;
        }

        if (prevLeadersCount > (i + 1) / 2 && (leaderCount - prevLeadersCount) > (A.length - i - 1) / 2) {
          equiLeaders++;
        }
      }

      return equiLeaders;
    }

    return 0;
  }
}
