public class LeastNumberOfUniqueIntegersAfterKRemovals {
  class Solution {
      /*
       * Greedy.
       * Get the frequencies of the elements.
       * Sort based on asc order of frequencies.
       * Remove first k elements.
       * Get the number of unique elements.
       */
      public int findLeastNumOfUniqueInts(int[] arr, int k) {
          Map<Integer, Integer> map;
          PriorityQueue<Integer> heap;
          int key;

          map = new HashMap<>();
          heap = new PriorityQueue<>((x,y) -> map.get(x) - map.get(y));

          // O(n) time, O(n) space.
          for (int num : arr) {
              map.put(num, map.getOrDefault(num, 0) + 1);
          }

          // O(n) time, O(n) space.
          heap.addAll(map.keySet());

          // O(klogn) time.
          while (k > 0) {
              key = heap.poll();
              k -= map.get(key);
          }

          return k == 0 ? heap.size() : heap.size() + 1;
      }
  }

  class SolutionTwo {
    /*
     * counts keep track of the number of times an occurrence occurs.
     * Sum of all values in the counts array is the number of unique
     * elements.
     * Time: O(n).
     * Space: O(n).
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map;
        int[] counts;
        int sum;
        int numRemoved;

        map = new HashMap<>();
        counts = new int[arr.length+1];
        sum = 0;

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int value : map.values()) {
            counts[value]++;
            ++sum;
        }

        // If I take 1 from 3*2 ([1,1,2,2,3,3]). Somehow, I need to
        // make sum = 3.
        // If k < i, then return sum till now. There is no decrease.
        // Otherwise, e.g. k=3, floor(k/i) unique numbers are removed.
        // min(floor(k/i), counts[i]) unique numbers are removed.
        for (int i=1; i<counts.length && k >= i; ++i) {
            numRemoved = Math.min(k/i, counts[i]);
            sum -= numRemoved;
            k -= numRemoved * i;
        }

        return sum;
    }
}