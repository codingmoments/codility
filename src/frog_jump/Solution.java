package frog_jump;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
 * 
 * Idea: 
 * Let's say the frog requires N number of jumps
 * X + N * D = Y
 * This means:
 * N * D = Y - X
 * N = ( Y - X ) / D
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.solution(10, 85, 30);
  }

  /**
   * Lesson solution
   */
  public int solution(int X, int Y, int D) {
    // N = ( Y - X ) / D
    return (int) Math.ceil(((Y - X) * 1.0) / D);
  }
}