package max_profit;

/**
 * @formatter:off
 * Solution for Codility lesson: 
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/
 * 
 * Idea:
 * 
 * We are interested in finding the maximum profit.
 * To get the maximum profit, we have to buy the stock at lowest price and sell it at a price that gives maximum profit.
 * 
 * So, we traverse the array and find the lowest price of the stock that can lead to maximum profit.
 * For example: 10 20 5 30
 * 
 * We traverse this array in the following way:
 * 10 : We use this as buying price. 
 * 20 : We use this as selling price. This gives us profit of 10. This becomes the maximum profit.
 *  5 : Since this is lower than last buying price, we use 5 as buying price.
 * 30 : We use this as selling price. This gives us profit of 25. This becomes the maximum profit. 
 * 
 * @formatter:on
 */
public class Solution {

  public int solution(int[] A) {
    int minPriceIndex = 0;
    int maxProfit = 0;

    for (int i = 0; i < A.length; i++) {
      // Check if we should buy the stock at the index i
      // We should buy it if it has minimum price
      if (A[minPriceIndex] > A[i]) {
        minPriceIndex = i;
      }

      // Check if should sell the stock at the index i
      // We should sell it if it gives us maximum profit
      int profit = A[i] - A[minPriceIndex];
      maxProfit = Math.max(profit, maxProfit);
    }

    return maxProfit;
  }
}
