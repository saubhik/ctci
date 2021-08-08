#include <vector>

using namespace std;

class Solution {
public:
    /*
     * This is leading to TLE.
     * Remove an edge each time, and do DFS.
     * We can also try to find the MST using Kruskal's algorithm?
     */
    vector<vector<int>> criticalConnections(
        int n,
        vector<vector<int>>& connections
    ){
        vector<vector<int>> graph(n);
        vector<vector<int>> critical_connections;

        for (auto connection : connections) {
            graph[connection[0]].push_back(connection[1]);
            graph[connection[1]].push_back(connection[0]);
        }

        for (auto connection : connections) {
            if (!isConnected(graph, connection)) {
                critical_connections.push_back(connection);
            }
        }

        return critical_connections;
    }

private:
    bool isConnected(vector<vector<int>>& graph, vector<int>& connection)
    {
        int n = graph.size();
        vector<bool> visited(n);
        int count = 0;
        traverse(0, graph, visited, connection, count);
        return count == n;
    }

    void traverse(
        int node,
        vector<vector<int>>& graph,
        vector<bool>& visited,
        vector<int>& connection,
        int& count
    ){
        visited[node] = true;
        count++;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor] &&
                !(node == connection[0] && neighbor == connection[1]) &&
                !(node == connection[1] && neighbor == connection[0])) {
                traverse(neighbor, graph, visited, connection, count);
            }
        }
    }
};

class SolutionTwo {
public:
    /*
     * Using DFS with ranks to find cycles, and ignore edges which are part of
     * a cycle. We could use Tarjan's algorithm.
     *
     * Time: O(V+E). Since graph is connected, E >= V. So O(E).
     * Space: O(V+E). Since graph is connected, E >= V. So O(E).
     */
    vector<vector<int>> criticalConnections(
        int n, vector<vector<int>>& connections
    ){
        graph.resize(n);
        for (vector<int> connection : connections) {
            graph[connection[0]].push_back(connection[1]);
            graph[connection[1]].push_back(connection[0]);
        }

        rank.resize(n);
        for (int i = 0; i < n; ++i) {
            rank[i] = -1;
        }

        rank[0] = 0;
        dfsWithRank(0);

        return crit_conns;
    }

private:
    vector<vector<int>> graph;
    vector<vector<int>> crit_conns;
    vector<int> rank;

    int dfsWithRank(int node) {
        int min_rank;
        int min_rank_nbr;

        min_rank = rank[node];

        for (int neighbor : graph[node]) {
            if (rank[neighbor] == -1) {
                rank[neighbor] = rank[node] + 1;

                min_rank_nbr = dfsWithRank(neighbor);
                if (min_rank_nbr > rank[node]) {
                    crit_conns.push_back({node, neighbor});
                }

                min_rank = min(min_rank, min_rank_nbr);
            } else if (rank[neighbor] != rank[node] - 1) {
                min_rank = min(min_rank, rank[neighbor]);
            }
        }

        return min_rank;
    }
};

