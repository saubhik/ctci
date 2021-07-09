import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {
	class Solution {
		/*
		 * Sort by start times, and maintain heap of
		 * end times, to check if any room is free.
		 * Time: O(nlogn).
		 * Space: O(n).
		 */
		public int minMeetingRooms(int[][] intervals) {
			PriorityQueue<Integer> heap;
			
			heap = new PriorityQueue<>();
			
			Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
			
			for (int[] interval : intervals) {
				if (!heap.isEmpty() && heap.peek() <= interval[0]) {
					heap.poll();
				}
				
				heap.add(interval[1]);
			}
			
			return heap.size();
		}
	}
}
