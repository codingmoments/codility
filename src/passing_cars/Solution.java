package passing_cars;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
 * 
 * Idea:
 * Use two counters.
 * One counter to store the number of cars passing east.
 * The other counter to store the number of times the cars passing east cross the a car passing west.
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    int carsPassingEast = 0;
    int passingCars = 0;

    for (int i : A) {
      if (i == 0) {
        carsPassingEast++;
      }
      else {
        passingCars += carsPassingEast;
        if (passingCars > 1_000_000_000) {
          return -1;
        }
      }
    }
    return passingCars;
  }
}
