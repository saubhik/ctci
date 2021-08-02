import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {
    class Solution {
        public int connectSticks(int[] sticks) {
            PriorityQueue<Integer> sticksQ;
            int sum;
            int tmp;
            
            sticksQ = new PriorityQueue<>();
            sum = 0;
            
            for (int stick : sticks) {
                sticksQ.add(stick);
            }
            
            while (sticksQ.size()>1) {
                tmp = sticksQ.poll() + sticksQ.poll();
                sum += tmp;
                sticksQ.offer(tmp);
            }
            
            return sum;
        }
    }
}
