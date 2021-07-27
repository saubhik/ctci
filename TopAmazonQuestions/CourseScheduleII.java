import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseScheduleII {
	class Solution {
		/*
		 * Get topological sorted order.
		 * We can use DFS with 2 colors.
		 * Grey: Node is in recursion stack. Encountering grey node means
		 *       cycle.
		 * Black: Node is processed. Encountering black node does not
		 *        mean cycle.
		 */
		public int[] findOrder(int numCourses, int[][] prerequisites) {
			Map<Integer,List<Integer>> graph;
			List<Integer> neighbors;
			Deque<Integer> stack;
			int[] color;
			int[] courseOrder;
			
			graph = new HashMap<>();
			stack = new ArrayDeque<>();
			color = new int[numCourses];
			courseOrder = new int[numCourses];
			
			for (int[] edge : prerequisites) {
				neighbors = graph.getOrDefault(edge[1], new ArrayList<>());
				neighbors.add(edge[0]);
				graph.put(edge[1], neighbors);
			}
			
			for (int node = 0; node < numCourses; ++node) {
				if (dfs(graph, stack, color, node) == -1) {
					return new int[0];
				}
			}
			
			for (int i = 0; i < numCourses; ++i) {
				courseOrder[i] = stack.pop();
			}
			
			return courseOrder;
		}
		
		private int dfs(
			Map<Integer,List<Integer>> graph, 
			Deque<Integer> stack, 
			int[] color,
			int node) {
			if (color[node] == 2) {
				return 0;
			}
			
			if (color[node] == 1) {
				return -1;
			}
			
			color[node] = 1;
			
			if (graph.containsKey(node)) {
				for (int nextNode : graph.get(node)) {        
					if (dfs(graph, stack, color, nextNode) == -1) {
						return -1;
					}
				}
			}
			
			color[node] = 2;
			stack.push(node);
			
			return 0;
		}
	}
}