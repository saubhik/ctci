import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyStack {
	class FreqStack {
		private Map<Integer,Integer> valueToFreq;
		private Map<Integer,Deque<Integer>> freqToValues;
		private int maxFreq;
		
		/*
		 * Maintain a map of val to frequencies.
		 * Maintain the maximum frequency.
		 * For each frequency, we want the values with that frequency.
		 * Maintain a map of frequency to stack of values.
		 */
		public FreqStack() {
			valueToFreq = new HashMap<>();
			freqToValues = new HashMap<>();
			maxFreq = 0;
		}
		
		/*
		 * Increment frequency of val.
		 * Push val to stack corresponding to new frequency.
		 * Update maxFreq if necessary.
		 */
		public void push(int val) {
			int freq;
			
			valueToFreq.put(val, valueToFreq.getOrDefault(val,0)+1);
			freq = valueToFreq.get(val);
			freqToValues.computeIfAbsent(freq, x -> new ArrayDeque<>()).push(val);
			maxFreq = Math.max(maxFreq, freq);
		}
		
		/*
		 * Using maxFreq, get stack of values.
		 * Pop the stack. Update frequency.
		 */
		public int pop() {
			int elem;
			Deque<Integer> stack;
			
			stack = freqToValues.get(maxFreq);
			elem = stack.pop();
			valueToFreq.put(elem, maxFreq-1);
			
			if (stack.isEmpty()) {
				maxFreq--;
			}
			
			return elem;
		}
	}
	
	/**
	 * Your FreqStack object will be instantiated and called as such:
	 * FreqStack obj = new FreqStack();
	 * obj.push(val);
	 * int param_2 = obj.pop();
	 */
}