package stone_wall;

import java.util.Date;
import java.util.Stack;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
 * 
 * Idea: Let's start with the small case.
 *    _ 
 *  _| |_
 * |_|_|_|
 * 
 * Here, we have three walls - small, big and small. The idea here is that, first and third walls have same heights, meaning we can use same block for all the three walls.
 * In order to track if we can keep using the already used block from the previous walls, we take help of Stack data structure.
 * 
 * Before we push a wall into a stack, we check:
 * - if the height of the previous wall is lower than the height of the current wall, then we need a new block. 
 * - if the height of the previous wall is more than the height of the current wall, then we go backward and check:
 *   - if there is any previous wall of same height, then we do not need a new block.
 * 
 * @formatter:on
 */
public class Solution {

  public int solution(int[] H) {
    // Total number of blocks needed to build all the walls
    int numberOfBlocks = 0;

    // Stack to maintain heights of the wall at previous points
    Stack<Integer> blockStack = new Stack<Integer>();

    // For height of each wall
    for (int height : H) {
      // If stack is empty, add the height & increment number of blocks
      if (blockStack.isEmpty()) {
        blockStack.push(height);
        numberOfBlocks++;
        continue;
      }

      boolean isDecisionMade = false;

      // Looping through the previous walls to find out if there a wall of same height as current wall
      while (!blockStack.isEmpty()) {
        int previousWallHeight = blockStack.pop();

        // If the height of the previous wall is lower than the height of the current wall, then we need a new block.
        if (previousWallHeight < height) {
          blockStack.push(previousWallHeight);
          blockStack.push(height);
          numberOfBlocks++;
          isDecisionMade = true;
          break;
        }
        // If there is any previous wall of same height, then we do not need a new block.
        if (previousWallHeight == height) {
          blockStack.push(height);
          isDecisionMade = true;
          break;
        }

        // If the height of the previous wall is more than the height of the current wall, then we go backward and check:
        if (previousWallHeight > height) {
          // Continue the loop backwards
        }
      }

      // After looping through all the previous walls, if we could not build the current wall
      // using any of the previous blocks then we need a new block
      if (!isDecisionMade) {
        blockStack.push(height);
        numberOfBlocks++;
      }
    }

    return numberOfBlocks;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    // Sample
    int blocks = solution.solution(new int[] { 8, 8, 5, 7, 9, 8, 7, 4, 8 });
    System.out.println("{ 8, 8, 5, 7, 9, 8, 7, 4, 8 } ==> " + blocks);

    // Empty
    blocks = solution.solution(new int[] {});
    System.out.println("{  } ==> " + blocks);

    // Single element
    blocks = solution.solution(new int[] { 10 });
    System.out.println("{ 10 } ==> " + blocks);

    // Two same elements
    blocks = solution.solution(new int[] { 10, 10 });
    System.out.println("{ 10, 10 } ==> " + blocks);

    // Two different elements
    blocks = solution.solution(new int[] { 10, 7 });
    System.out.println("{ 10, 7 } ==> " + blocks);

    // All elements same except last one
    blocks = solution.solution(new int[] { 10, 10, 10, 10, 5 });
    System.out.println("{ 10, 10, 10, 10, 5 } ==> " + blocks);

    // All elements same except first one - first one is smallest
    blocks = solution.solution(new int[] { 5, 10, 10, 10, 10 });
    System.out.println("{ 5, 10, 10, 10, 10} ==> " + blocks);

    // All elements same except first one - first one is largest
    blocks = solution.solution(new int[] { 15, 10, 10, 10, 10 });
    System.out.println("{ 15, 10, 10, 10, 10} ==> " + blocks);

    // Alternate
    blocks = solution.solution(new int[] { 5, 10, 5, 10, 5, 10, 5 });
    System.out.println("{5, 10, 5, 10, 5, 10, 5} ==> " + blocks);

    // Incremental
    blocks = solution.solution(new int[] { 5, 10, 15, 20, 25, 30 });
    System.out.println("{5, 10, 15, 20, 25, 30} ==> " + blocks);

    // Decremental
    blocks = solution.solution(new int[] { 30, 25, 20, 15, 10, 5 });
    System.out.println("{30, 25, 20, 15, 10, 5} ==> " + blocks);

    // Performance
    int[] H = new int[100000];
    for (int i = 0; i < H.length; i++) {
      H[i] = (i + 1) * 10;
    }
    long startTime = new Date().getTime();
    blocks = solution.solution(H);
    System.out.println("Performance ==> " + blocks);
    System.out.println("Time taken (ms) ==> " + (new Date().getTime() - startTime));
  }
}
