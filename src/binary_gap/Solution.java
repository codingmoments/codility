package binary_gap;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 * 
 * Idea: This is a straight solution where we traverse the digits in 
 * binary representations and count the number of 0s between two 1s.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.solution(2147483647);
  }

  /**
   * Lesson solution
   */
  public int solution(int N) {
    // Get the binary representation for given number
    int[] binaryArray = decimal2Binary(N);

    int maxBinaryGap = 0, binaryGap = 0;
    boolean gapStarted = false;

    // Traverse the binary array to find the maximum sequence of 0s
    for (int digit : binaryArray) {
      // We start counting 0s only after first 1 is found
      if (!gapStarted) {
        if (digit == 1) {
          gapStarted = true;
        }
      }
      else {
        if (digit == 0) {
          binaryGap++;
        }
        else {
          // We have come across 1
          // We store the maximum occurrence in maxBinaryGap and reset the binaryGap to 0
          maxBinaryGap = Math.max(maxBinaryGap, binaryGap);
          binaryGap = 0;
        }
      }
    }

    return maxBinaryGap;
  }

  /**
   * Method to convert a decimal number into a binary representation
   */
  private int[] decimal2Binary(int decimal) {
    int k = (int) (Math.log(decimal) / Math.log(2)) + 1;
    int[] binaryArray = new int[k];

    while (decimal > 0) {
      binaryArray[--k] = decimal % 2;
      decimal /= 2;
    }
    return binaryArray;
  }
}
