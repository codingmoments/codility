package tape_equilibrium;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
 * 
 * Idea:
 * Calculate sum of all numbers in the array.
 * Start traversing the array.
 * In every iteration, calculate the sum of left side of the array.
 * Sum of the right side of the array will be total sum - left side sum.
 * Keep track of minimum difference between LHS and RHS.
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();

    int diff = solution.solution(new int[] { -1000, 1000 });

    System.out.println(diff);
  }

  public int solution(int[] A) {

    long totalSum = 0;
    for (int i : A) {
      totalSum += i;
    }

    long sumLHS = A[0];
    long sumRHS = totalSum - sumLHS;
    long minDifference = Math.abs(sumRHS - sumLHS);

    // We traverse the array
    // We keep storing the sum of left side of the array in sumLHS
    for (int i = 1; i < A.length - 1; i++) {
      sumLHS += A[i];
      // We calculate the difference between left and right sides of the array
      sumRHS = totalSum - sumLHS;
      long difference = Math.abs(sumRHS - sumLHS);
      // We keep track of minimum difference
      if (minDifference > difference) {
        minDifference = difference;
      }
    }

    return (int) minDifference;
  }
}
