package frog_river_one;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
 * 
 * Idea:
 * Use counter array to represent the steps.
 * In that array, we maintain the existence of a leave for a given step i.e. index.
 * 
 * As soon as, we get all the leaves at all the indexes (steps), that time is the minimum time
 * required for the frog to cross the river.
 * @formatter:on  
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int time = solution.solution(5, new int[] { 1, 3, 1, 4, 2, 3, 5, 4 });
    System.out.println(time);
  }

  public int solution(int X, int[] A) {

    // Total number of steps
    int totalSteps = X;

    // Array to maintain whether we have got the leaf for given step
    int steps[] = new int[X];

    // Traversing the timeline for falling leaves
    for (int time = 0; time < A.length; time++) {
      // Step for which the leaf fell
      int step = A[time] - 1;
      // If the leaf does not already exist on that step
      if (steps[step] == 0) {
        // Mark that the leaf exists on that step
        steps[step] = 1;
        // Reduce the number of steps without leaves
        totalSteps--;
        // If all steps got the leaves
        if (totalSteps == 0) {
          return time;
        }
      }
    }

    return -1;
  }
}
