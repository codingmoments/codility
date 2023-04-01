package genomic_range_query;

import java.util.Arrays;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/5-prefix_sums/genomic_range_query/
 * 
 * Idea:
 * Populate the presence of impact factors into individual arrays.
 * Build prefix sums for these impact factors arrays.
 * Idea is to use prefix sum to check the presence of impact factor in give slice.
 * If presenceA[j] - presenceA[i] > 0, that means A is present between i and j.
 * 
 * Special Note:
 * If S = "AC", then in presenceA, we will have:
 * presenceA = {0, 1, 0}
 * Here, the first index is reserved to ensure that we always check the real first index.
 * 
 * After prefix sum:
 * presenceA = {0, 1, 1}
 * 
 * When we want to check existence of A between p and q, we will check the range:
 * presenceA[p]..presenceA[q + 1]
 * 
 * This way, we are going one index before in order to check presence of A from real index p.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] result = solution.solution("AC", new int[] { 0, 0, 1 }, new int[] { 0, 1, 1 });

    System.out.println(Arrays.toString(result));
  }

  public int[] solution(String S, int[] P, int[] Q) {
    // Arrays for impact factors presence
    int[] presenceA = new int[S.length() + 1];
    int[] presenceC = new int[S.length() + 1];
    int[] presenceG = new int[S.length() + 1];
    int[] presenceT = new int[S.length() + 1];

    // Populate the presence of impact factors into individual arrays.
    for (int i = 0; i < S.length(); i++) {
      switch (S.charAt(i)) {
        case 'A':
          presenceA[i + 1] = 1;
          break;
        case 'C':
          presenceC[i + 1] = 1;
          break;
        case 'G':
          presenceG[i + 1] = 1;
          break;
        case 'T':
          presenceT[i + 1] = 1;
          break;
        default:
          break;
      }
    }

    // Build prefix sums for these impact factors arrays.
    // Idea is to use prefix sum to check the presence of impact factor in give slice
    // If presenceA[j] - presenceA[i] > 0, that means A is present between i and j
    prefixSum(presenceA);
    prefixSum(presenceC);
    prefixSum(presenceG);
    prefixSum(presenceT);

    int[] result = new int[P.length];

    for (int i = 0; i < P.length; i++) {
      // Check if A exists between P[i] and Q[i]
      if (presenceA[Q[i] + 1] - presenceA[P[i]] > 0) {
        result[i] = 1;
      }
      // Check if C exists between P[i] and Q[i]
      else if (presenceC[Q[i] + 1] - presenceC[P[i]] > 0) {
        result[i] = 2;
      }
      // Check if G exists between P[i] and Q[i]
      else if (presenceG[Q[i] + 1] - presenceG[P[i]] > 0) {
        result[i] = 3;
      }
      // This means only T exists
      else {
        result[i] = 4;
      }
    }

    return result;
  }

  private void prefixSum(int[] array) {
    int prefixSum = 0;
    for (int i = 0; i < array.length; i++) {
      array[i] += prefixSum;
      prefixSum = array[i];
    }
  }
}

