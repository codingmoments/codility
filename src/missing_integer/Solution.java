package missing_integer;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/
 * 
 * Idea:
 * Use array representing existing of a number in given array.
 * Traverse given array and update existenceArray.
 * Check existenceArray for index with value 0.
 * That index is the missing integer.
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    // Array representing existing of a number in given array A
    int[] existenceArray = new int[1000001];

    // Traverse given array A and update existenceArray
    for (int number : A) {
      if (number >= 0) {
        existenceArray[number] = 1;
      }
    }

    // Check existenceArray for index with value 0.
    // That index is the missing integer.
    int missingInteger = 1;

    for (int i = 1; i < existenceArray.length; i++) {
      if (existenceArray[i] == 0) {
        missingInteger = i;
        break;
      }
    }

    return missingInteger;
  }
}
