package perm_check;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/
 * 
 * Idea:
 * 
 * Use an array representing existence of a number in given array A.
 * Traverse given array A and update existenceArray.
 * Check existenceArray for index with value 0.
 * That index is the missing integer.
 * 
 * If missing integer exists, then it is not a permutation. 
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    // Array representing existence of a number in given array A
    int[] existenceArray = new int[A.length];

    // Traverse given array A and update existenceArray
    for (int number : A) {
      if (number <= A.length) {
        existenceArray[number - 1] = 1;
      }
    }

    // Check existenceArray for index with value 0.
    // That index is the missing integer.
    int missingInteger = 0;

    for (int i = 0; i < existenceArray.length; i++) {
      if (existenceArray[i] == 0) {
        missingInteger = i + 1;
        break;
      }
    }

    return missingInteger == 0 ? 1 : 0;
  }
}
