import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	class Solution {
		/*
		 * DFS.
		 * Time: O(V+E)
		 * Space: O(V+E)
		 */
		public int countComponents(int n, int[][] edges) {
			Map<Integer,List<Integer>> graph;
			boolean[] visited;
			int ans;
			
			graph = new HashMap<>();
			visited = new boolean[n];
			ans = 0;
			
			for (int[] edge : edges) {
				graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
				graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
			}
			
			for (int i = 0; i < n; ++i) {
				if (!visited[i]) {
					dfs(graph, visited, i);
					ans++;
				}
			}
			
			return ans;
		}
		
		private void dfs(Map<Integer,List<Integer>> graph, boolean[] visited, int node) {
			if (visited[node]) {
				return;
			}
			
			visited[node] = true;
			
			if (graph.containsKey(node)) {
				for (int nextNode : graph.get(node)) {
					dfs(graph, visited, nextNode);
				}
			}
		}
	}
}
