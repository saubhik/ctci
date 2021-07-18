import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {
	class Solution {
		private Map<Integer,List<Integer>> graph;
		private boolean[] path;
		private boolean[] checked;
		
		/*
		 * DFS with Backtracking to check if directed graph has a cycle.
		 * i.e., check with graph is a DAG.
		 *
		 * Time: O(|V|+|E|)
		 * Space: O(|V|+|E|)
		 */
		public boolean canFinish(int numCourses, int[][] prerequisites) {
			List<Integer> neighbors;
			
			graph = new HashMap<>();
			path = new boolean[numCourses];
			checked = new boolean[numCourses];
			
			for (int[] p : prerequisites) {
				neighbors = graph.getOrDefault(p[0], new ArrayList<>());
				neighbors.add(p[1]);
				graph.put(p[0], neighbors);
			}
							
			for (int node = 0; node < numCourses; ++node) {
				if (hasCycle(node)) {
					return false;
				}
			}
	
			return true;
		}
		
		public boolean hasCycle(int node) {        
			if (!graph.containsKey(node)) {
				return false;
			}
			
			if (checked[node]) {
				return false;
			}
	
			path[node] = true;
			
			for (int neighbor : graph.get(node)) {
				if (path[neighbor] || hasCycle(neighbor)) {
					return true;
				}
			}
			
			path[node] = false;
			checked[node] = true;
			
			return false;
		}
	}
}
