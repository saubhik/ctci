import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class KClosestPointsToOrigin {
	class Solution {
		public int[][] kClosest(int[][] points, int k) {
			PriorityQueue<int[]> heap;
			
			heap = new PriorityQueue<>(
				(x, y) -> Integer.compare(dist(y), dist(x)));
			
			for (int[] point : points) {
				heap.add(point);
				if (heap.size() > k) {
					heap.poll();
				}
			}
			
			return heap.toArray(new int[heap.size()][]);
		}
		
		private int dist(int[] x) {
			return x[0]*x[0] + x[1]*x[1];
		}
	}

	class SolutionTwo {
		private int[][] points;
		
		public int[][] kClosest(int[][] points, int k) {
			this.points = points;
			return quickSelect(0, points.length - 1, k);
		}
		
		/*
		 * Using QuickSelect.
		 * Time: O(n) in average case. O(n^2) in worst case.
		 * Space: O(1) space.
		 */
		private int[][] quickSelect(int left, int right, int kSmallest) {
			int pivotIndex;
			Random randomNum;
			
			if (left == right) {
				return Arrays.copyOfRange(points, 0, kSmallest);
			}
			
			randomNum = new Random();
			pivotIndex = left + randomNum.nextInt(right - left);
			
			pivotIndex = partition(left, right, pivotIndex);
			
			if (pivotIndex == kSmallest) {
				return Arrays.copyOfRange(points, 0, pivotIndex);
			} else if (pivotIndex < kSmallest) {
				return quickSelect(pivotIndex + 1, right, kSmallest);
			} else {
				return quickSelect(left, pivotIndex - 1, kSmallest);
			}
		}
		
		private int partition(int left, int right, int pivotIndex) {
			int index;
			
			index = left;
			swap(right, pivotIndex);
			
			for (int i = left; i < right; ++i) {
				if (dist(points[i]) < dist(points[right])) {
					swap(i, index);
					++index;
				}
			}
			
			swap(index, right);
			
			return index;
		}
		
		private void swap(int i, int j) {
			int[] tmp;
			
			tmp = points[i];
			points[i] = points[j];
			points[j] = tmp;
		}
		
		private int dist(int[] point) {
			return point[0]*point[0] + point[1]*point[1];
		}
	}
}
