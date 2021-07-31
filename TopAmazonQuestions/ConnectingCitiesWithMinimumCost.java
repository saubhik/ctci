import java.util.Arrays;

public class ConnectingCitiesWithMinimumCost {
    class Solution {
        class DisjointSet {
            private int[] weights;
            private int[] parents;
            
            public DisjointSet(int N) {
                this.parents = new int[N+1];
                this.weights = new int[N+1];
                
                for (int i=1; i<=N; ++i) {
                    this.parents[i] = i;
                    this.weights[i] = 1;
                }
            }
            
            public void union(int a, int b) {
                int rootA = this.find(a);
                int rootB = this.find(b);
                
                if (rootA == rootB) {
                    return;
                }
                
                if (this.weights[rootA] >= this.weights[rootB]) {
                    this.parents[rootB] = rootA;
                    this.weights[rootA] += this.weights[rootB];
                } else {
                    this.parents[rootA] = rootB;
                    this.weights[rootB] += this.weights[rootA];
                }
            }
            
            public int find(int a) {
                while (a != this.parents[a]) {
                    this.parents[a] = this.parents[this.parents[a]];
                    a = this.parents[a];
                }
                return a;
            }
        }
        
        /*
         * Kruskal's algorithm for MST.
         * Time: O(N+MlogM+Mlog*N), where M is the number of connections.
         * Space: O(N).
         */
        public int minimumCost(int n, int[][] connections) {
            DisjointSet disjointSet;
            int cost;
            int total;
            int a;
            int b;
            
            disjointSet = new DisjointSet(n);
            cost = 0;
            total = 0;
            
            Arrays.sort(connections, (x,y)->x[2]-y[2]);
            
            for (int i = 0; i < connections.length; ++i) {
                a = connections[i][0];
                b = connections[i][1];
                
                if (disjointSet.find(a) != disjointSet.find(b)) {
                    disjointSet.union(a,b);
                    cost += connections[i][2];
                    total++;
                }
            }
            
            return total==n-1 ? cost : -1;
        }
    }
}