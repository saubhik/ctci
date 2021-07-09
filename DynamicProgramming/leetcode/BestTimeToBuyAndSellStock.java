public class BestTimeToBuyAndSellStock {
	class Solution {
		public int maxProfit(int[] prices) {
			int minSoFar;
			int maxProfit;
			
			minSoFar = Integer.MAX_VALUE;
			maxProfit = 0;
			
			for (int price : prices) {
				maxProfit = Math.max(maxProfit, price - minSoFar);
				minSoFar = Math.min(minSoFar, price);
			}
			
			return maxProfit;
		}
	}
}
