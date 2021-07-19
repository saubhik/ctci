import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {
	class Solution {
		/*
		 * Simulation with Fast Forward.
		 * Time: O(min(n,2^k))
		 * Space: O(2^k)
		 */
		public int[] prisonAfterNDays(int[] cells, int n) {
			boolean fastForward;
			int state;
			Map<Integer,Integer> visited;
			
			visited = new HashMap<>();
			fastForward = false;
			state = cellsToBitMap(cells);
			
			while (n > 0) {
				if (!fastForward) {
					if (visited.containsKey(state)) {
						n %= visited.get(state)-n;
						fastForward = true;
					} else {
						visited.put(state, n);
					}
				}
				
				if (n > 0) {
					--n;
					state = next(state);
				}
			}
			
			return bitMapToCells(state);
		}
		
		private int next(int state) {
			state = ~(state<<1)^(state>>1);
			state = state & 0x7e;
			return state;
		}
		
		private int cellsToBitMap(int[] cells) {
			int state;
			
			state = 0x0;
			for (int cell : cells) {
				state <<= 1;
				state = (state | cell);
			}
			
			return state;
		}
		
		private int[] bitMapToCells(int state) {
			int[] newCells;
			
			newCells = new int[8];
			
			for (int i = 7; i >= 0; --i) {
				newCells[i] = state&0x1;
				state = state>>1;
			}
			
			return newCells;
		}
	}
}
