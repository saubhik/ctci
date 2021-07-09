import java.util.Map;
import java.util.HashMap;

public class CoinChange {
	class Solution {
		private int[] coins;
		private Map<Integer, Map<Integer, Integer>> cache;
		
		/*
		 * If dp[i][j] is the minimum number of coins
		 * from coins[0..i] to make amount j:
		 * dp[i][j] = min(dp[i-1][j], 1 + dp[i][j-coins[i]])
		 *
		 * For coins=[1,2,5] and amount=3:
		 *         j=0:    j=1:    j=2:    j=3:
		 * i=0[1]: 0       1       2       3      
		 * i=1[2]: 0       1       1       2              
		 * i=2[5]: 0       1       1       2   
		 *
		 * Notes:
		 * 1. For out of range, use Integer.MAX_VALUE.
		 * 2. We only need to store current and last rows.
		 */
		public int coinChange(int[] coins, int amount) {
			this.coins = coins;
			this.cache = new HashMap<>();
			return dp(coins.length - 1, amount);
		}
		
		private int dp(int i, int j) {
			int res1;
			int res2;
			int ans;
			
			if (cache.containsKey(i) && cache.get(i).containsKey(j)) {
				return cache.get(i).get(j);
			}
			
			if (i == 0) {
				return (j%coins[i] == 0) ? j/coins[i] : -1; 
			}
			
			res1 = dp(i-1, j);
			res2 = -1;
			
			if (j >= coins[i]) {
				res2 = dp(i, j-coins[i]);
			}
			
			if (res1 == -1 && res2 == -1) {
				ans = -1;
			} else if (res1 == -1) {
				ans = 1+res2;
			} else if (res2 == -1) {
				ans = res1;
			} else {
				ans = Math.min(res1, 1+res2);
			}
			
			if (!cache.containsKey(i)) {
				if (cache.containsKey(i-2)) {
					cache.remove(i-2);
				}
				cache.put(i, new HashMap<>());
			}
			
			cache.get(i).put(j, ans);
			
			return ans;
		}
	}

	class SolutionTwo {
		private int[] cache;
		
		/*
		 * Dynamic Programming.
		 * Time: O(amount * n).
		 * Space: O(amount).
		 */
		public int coinChange(int[] coins, int amount) {
			this.cache = new int[amount+1];
			return dp(coins, amount);
		}
		
		private int dp(int[] coins, int amount) {
			int ans;
			int res;
			
			ans = Integer.MAX_VALUE;
			
			if (amount == 0) {
				return 0;
			} else if (amount < 0) {
				return -1;
			}
			
			if (cache[amount] != 0) {
				return cache[amount];
			}
			
			for (int coin : coins) {
				res = dp(coins, amount - coin);
				if (res >= 0) {
					ans = Math.min(ans, res+1);
				}
			}
			
			cache[amount] = ans == Integer.MAX_VALUE ? -1 : ans;
			return cache[amount];
		}
	}
}