package nailing_planks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/14-binary_search_algorithm/nailing_planks/
 * 
 * Idea:
 * 
 * The minimum number of nails can be 0 and maximum number of nails can be total elements in the given array C.
 * 
 * Using binary search algorithm, we try to find the mid number where all the planks can be nailed with mid number of nails.
 * 
 * For ensuring that we are nailing the planks in sequentially (in the order of their positions), we sort the planks and nails.
 * 
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {
    int[] A = { 1, 4, 5, 8 };
    int[] B = { 4, 5, 9, 10 };
    int[] C = { 4, 6, 7, 10, 2 };

    Solution s = new Solution();
    System.out.println(s.solution(A, B, C));
  }

  public int solution(int[] A, int[] B, int[] C) {
    // Sort the pairs of plans in arrays A and B
    List<Plank> planks = getSortedPlanks(A, B);

    int minNails = 0;
    int maxNails = C.length;
    int result = -1;

    while (maxNails >= minNails) {
      int midNail = (minNails + maxNails) / 2;
      if (allPlanksCanBeNailed(planks, C, midNail)) {
        result = midNail;
        maxNails = midNail - 1;
      }
      else {
        minNails = midNail + 1;
      }
    }

    return result;
  }

  private boolean allPlanksCanBeNailed(List<Plank> planks, int[] allNails, int midNail) {
    int lastNailedPlank = 0;

    int[] nails = Arrays.copyOfRange(allNails, 0, midNail);
    Arrays.sort(nails);

    for (int i = 0; i < nails.length; i++) {
      for (int k = lastNailedPlank; k < planks.size(); k++) {
        Plank plank = planks.get(k);
        if (plank.startPosition <= nails[i] && nails[i] <= plank.endPosition) {
          lastNailedPlank++;
          if (lastNailedPlank >= planks.size()) {
            return true;
          }
        }
        else {
          break;
        }
      }
    }
    return false;
  }

  private List<Plank> getSortedPlanks(int[] A, int[] B) {
    List<Plank> planks = new ArrayList<>();
    for (int i = 0; i < A.length; i++) {
      Plank plank = new Plank(A[i], B[i]);
      planks.add(plank);
    }
    Collections.sort(planks);
    return planks;
  }

  private class Plank implements Comparable<Plank> {
    private int startPosition;
    private int endPosition;

    public Plank(int startPosition, int endPosition) {
      super();
      this.startPosition = startPosition;
      this.endPosition = endPosition;
    }

    @Override
    public int compareTo(Plank o) {
      if (this.startPosition > o.startPosition)
        return 1;
      if (this.startPosition < o.startPosition)
        return -1;
      if (this.endPosition > o.endPosition)
        return 1;
      return -1;
    }
  }
}
