import java.util.PriorityQueue;

public class FindMedianFromDataStream {
	class MedianFinder {
		private PriorityQueue<Integer> maxHeap;
		private PriorityQueue<Integer> minHeap;
	
		/** initialize your data structure here. */
		public MedianFinder() {
			maxHeap = new PriorityQueue<>((x,y)->y-x);
			minHeap = new PriorityQueue<>();
		}
		
		public void addNum(int num) {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
			if (minHeap.size() > maxHeap.size()) {
				maxHeap.offer(minHeap.poll());
			}
		}
		
		public double findMedian() {
			if (maxHeap.size() == minHeap.size()) {
				return (maxHeap.peek() + minHeap.peek())*0.5;
			} else {
				return maxHeap.peek();
			}
		}
	}
	
	/**
	 * Your MedianFinder object will be instantiated and called as such:
	 * MedianFinder obj = new MedianFinder();
	 * obj.addNum(num);
	 * double param_2 = obj.findMedian();
	 */
}
