import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {
	class Solution {
		/*
		 * Time: O(Nlogk).
		 * Space: O(N+k), for hashmap with N elements
		 *   and heap with k elements.
		 */
		public int[] topKFrequent(int[] nums, int k) {
			Map<Integer, Integer> map;
			Queue<Integer> heap;
			int[] ans;
			
			map = new HashMap<>();
			heap = new PriorityQueue<>(new Comparator<>() {
				@Override
				public int compare(Integer i, Integer j) {
					return map.get(i) - map.get(j);
			  }
			});
			ans = new int[k];
			
			if (k == nums.length) {
				return nums;
			}
			
			for (int num : nums) {
				map.put(num, map.getOrDefault(num, 0) + 1);
			}
			
			/* O(Nlogk) time. */
			for (int num : map.keySet()) {
				heap.add(num);
				if (heap.size() > k) {
					heap.poll();
				}
			}
			
			/* O(klogk) time. */
			for (; k > 0; --k) {
				ans[k-1] = heap.poll();
			}
			
			return ans;
		}
	}
}
