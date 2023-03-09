package max_counters;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
 * 
 * Idea:
 * Logic is straight -- keep increasing the counter at given index by one as per provided operations.
 * 
 * The trick here is - instead of setting all counter values to the maximum value when the operation is max_operation, we re-instantiate the counter array.
 * And we maintain that maximum value by keeping it adding into a base value.
 * 
 * At the end, we just add this base value to all counters.
 */
public class Solution {

  public static void main(String[] args) {
  }

  /**
   * 
   * @param N - Number of counters
   * @param A - Array of operations
   * @return Array of counters
   */
  public int[] solution(int N, int[] A) {
    // Create array of counters
    int[] counters = new int[N];
    // Base counter value
    int baseValue = 0;
    // Maintain maximum value
    int maxValue = 0;

    // Traverse the array of operations
    for (int operation : A) {
      if (operation <= N) {
        // Increase the counter at index 'operation'
        counters[operation - 1]++;
        // Update maxValue
        maxValue = maxValue > counters[operation - 1] ? maxValue : counters[operation - 1];
      }
      else {
        if (maxValue > 0) { // Added this IF condition to gain the performance in case all operations are max operations.
          // We reset the counters array back instead of setting counters to maxValue to gain performance
          counters = new int[N];
          // Add maxValue to the base value
          baseValue += maxValue;
          // Reset the maximum value
          maxValue = 0;
        }
      }
    }

    // Adjust counter values by adding base value
    for (int i = 0; i < counters.length; i++) {
      counters[i] += baseValue;
    }

    return counters;
  }
}

