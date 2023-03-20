package number_of_disc_intersections;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 * 
 * Idea:
 * Mark the start and end points of all the discs on the axis.
 * 
 * Then, start counting intersections. 
 * It is same as passing cars.
 * If the start position of a disk is started, and we come across another start position, that means two discs are intersecting.
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    // Arrays to store start and end positions of the discs
    int[] startMarkers = new int[A.length];
    int[] endMarkers = new int[A.length];

    // Storing start and end positions
    for (int i = 0; i < A.length; i++) {
      // Radius
      int radius = A[i];

      // Start position
      int startPosition = 0;
      if (radius >= i) {
        // If radius is greater than or equal to the index, then start position comes before or at zero
        startPosition = 0;
      }
      else {
        startPosition = i - radius;
      }

      // Mark the start position
      startMarkers[startPosition]++;

      // End position
      int endPosition = A.length - 1;
      if (radius <= (A.length - 1) - i) {
        endPosition = i + radius;
      }
      else {
        // If the radius goes beyond the last index, mark the end position as last index
        endPosition = A.length - 1;
      }

      // Mark the end position
      endMarkers[endPosition]++;
    }

    int intersections = 0;
    int numberOfOpenDiscs = 0;
    // Counting intersections
    // It is same as passing cars.
    // If the start position of a disk is started, and we come across another start position, that means two discs are intersecting.
    for (int i = 0; i < startMarkers.length; i++) {
      for (int j = 1; j <= startMarkers[i]; j++) {
        intersections += numberOfOpenDiscs;

        if (intersections > 10000000) {
          return -1;
        }

        numberOfOpenDiscs++;
      }
      for (int j = 1; j <= endMarkers[i]; j++) {
        numberOfOpenDiscs--;
      }
    }

    return intersections;
  }

}
