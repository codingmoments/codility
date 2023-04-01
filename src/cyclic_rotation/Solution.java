package cyclic_rotation;

/**
 * @formatter:off
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 * 
 * Idea: For given array A, when we do A.length cyclic rotations, output will be same array.
 * So, we reduce the given number of cyclic rotations to effective number of cyclic rotations by using formula:
 * K = K % A.length
 * 
 * And then we rotate the elements K number of times.
 * @formatter:on
 */
public class Solution {

  public static void main(String[] args) {

    int[] array = { 2, 3, 4 };
    int numberOfRotations = 10;

    Solution solution = new Solution();
    int[] rotatedArray = solution.solution(array, numberOfRotations);
    for (int i : rotatedArray) {
      System.out.printf(" %s", i);
    }
  }

  public int[] solution(int[] A, int K) {
    if (A == null || A.length == 0) {
      return A;
    }

    // For given array A, when we do A.length cyclic rotations, output will be same array.
    // So, we reduce the given number of cyclic rotations to effective number of cyclic rotations.
    K = K % A.length;

    // We shift the array element to the right by 1
    // And we repeat it K times
    while (K > 0) {
      int previousElement = 0;

      for (int i = 0; i < A.length; i++) {
        int currentElement = A[i];

        if (i == 0) {
          A[i] = A[A.length - 1];
        }
        else {
          A[i] = previousElement;
        }
        previousElement = currentElement;
      }
      K--;
    }

    return A;
  }
}
