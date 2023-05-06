package min_max_division;

import java.util.Arrays;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/14-binary_search_algorithm/min_max_division/
 * 
 * Idea:
 * 
 * We first need to find boundaries for the sum of elements in a block.
 * 
 * The two boundaries are:
 * 
 * 1. Put all the elements in a single block.
 * In this case, the maximum sum of elements in a block is the total sum of all elements in the given array.
 * 
 * 2. Put only one element in a block.  
 * In this case, the maximum sum of elements in a block is the maximum element in the given array.
 * 
 * Now we know the boundaries, using binary search algorithm, we keep finding the number of blocks 
 * to fit the mid value of the boundaries. Depending on the required number of blocks, we shift the boundaries.
 * 
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    int[] A = { 2, 1, 5, 1, 2, 2, 2 };
    int K = 3;
    int M = 5;

    Solution s = new Solution();
    System.out.println(s.solution(K, M, A));
  }

  public int solution(int K, int M, int[] A) {

    int maxSum = Arrays.stream(A).sum();
    int minSum = Arrays.stream(A).max().getAsInt();
    int minimalLargeSum = minSum;

    while (maxSum >= minSum) {
      int midSum = (minSum + maxSum) / 2;

      if (calculateNumberOfBlocksWithSum(A, midSum) <= K) {
        minimalLargeSum = midSum;
        maxSum = midSum - 1;

      }
      else {
        minSum = midSum + 1;
      }
    }

    return minimalLargeSum;
  }

  private int calculateNumberOfBlocksWithSum(int[] A, int maxSum) {
    int numberOfBlocks = 1;
    int sum = 0;

    for (int n : A) {
      sum += n;
      if (sum > maxSum) {
        numberOfBlocks++;
        sum = n;
      }
    }

    return numberOfBlocks;
  }
}
