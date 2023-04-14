package peaks;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
 * 
 * Idea:
 * 
 * First, find the peaks in the given array.
 * 
 * Requirement is that each block should have at least one peak.
 * This means the minimum size of a block should be half of the maximum gap between two peaks.
 * Otherwise, a block with smaller size than that will not have a peak.
 * 
 * So, we should first find the maximum gap between two peaks.
 * This includes:
 * - Gap of the first peak from the beginning of the array (a)
 * - Gap of the last peak from the end of the array.  (b)
 * - Maximum gap between two peaks. (c)
 * 
 * Block size will be maximum of (a), (b) and half of (c).
 * 
 * After we find this block size, we need to ensure that all the blocks are of same size (i.e. each block contains same number of elements).
 * That means, the length of the array should be divisible by block size.
 * So, we keep increasing the block size until we find the block size that can divide the length of the array.
 * 
 * While doing this, we also need to ensure that each block contains a peak. 
 * We cannot assume that the block with size found at line 22 will always have a peak.
 * For example:
 *       2                             12
 * 0  1    3  4  5  6  7  8  9  10 11     13  14
 * 
 * With calculation at line 22, we will get block size as 5. But if we use that as block size, then second block will start from 5 to 9, which does not contain peak.
 * 
 * @formatter:on 
 */
public class Solution {

  public int solution(int[] A) {
    int lastPeakIndex = 0;
    int maxGapBetweenPeaks = 0;
    int maxCornerGap = 0;

    int[] peaks = new int[A.length];

    // Collecting the peak indexes
    // While collecting the peaks, we are also noting down the maxGapBetweenPeaks
    for (int i = 1; i < A.length - 1; i++) {
      if (A[i] > A[i + 1] && A[i] > A[i - 1]) {

        // The gap between two peaks is the difference between their indexes + 1.
        // If we have two peaks - one at index 2 and one at index 10
        // then, the difference will be come 8.
        // If we take block size of 8/2 = 4, then we will get:
        // [2, 3, 4, 5] and [6, 7, 8, 9]
        // See, index 10 (peak) is missed. So, we add one in the difference.
        maxGapBetweenPeaks = Math.max(maxGapBetweenPeaks, i - lastPeakIndex + 1);

        // Gap of first peak from the beginning of the array
        if (lastPeakIndex == 0) {
          maxCornerGap = i + 1;
        }
        lastPeakIndex = i;

        peaks[i] = 1;

        // Skip the next number as it cannot be peak
        i++;
      }
    }

    // Gap of the last peak from the end of the array
    maxCornerGap = Math.max(maxCornerGap, A.length - lastPeakIndex);

    // Minimum block size
    int blockSize = (int) Math.ceil(maxGapBetweenPeaks / 2.0);

    if (blockSize == 0) {
      // This means no peak found.
      return 0;
    }

    blockSize = Math.max(blockSize, maxCornerGap);

    // Find the block size that is divisor of length of the array
    boolean divisorFound = true;
    while (blockSize <= A.length) {
      boolean peakFound = false;
      divisorFound = true;

      if (A.length % blockSize == 0) {
        // Check that each block contains a peak
        for (int i = 0; i < peaks.length; i++) {

          if (peaks[i] == 1) {
            // Peak is found in the block
            peakFound = true;
          }

          // A block is covered.
          if ((i + 1) % blockSize == 0) {
            if (!peakFound) {
              // Since peak is not found in the block, we should increase the block size
              divisorFound = false;
              break;
            }
            // If peak is found, and block is ended, reset the peakNotFound
            peakFound = false;
          }
        }
        if (divisorFound) {
          break;
        }
      }
      blockSize++;
    }

    return divisorFound ? A.length / blockSize : 1;
  }

}
