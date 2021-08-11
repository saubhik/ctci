#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    /*
     * Time: O(HlogH + VlogV) where
     *      H is the number of horizontal cuts, and
     *      V is the number of vertical cuts.
     * Space: O(1).
     */
    int maxArea(
        int h, int w, vector<int>& horizontalCuts, vector<int>& verticalCuts) {
        sort(begin(horizontalCuts), end(horizontalCuts));
        sort(begin(verticalCuts), end(verticalCuts));

        auto max_h = max(horizontalCuts[0], h - horizontalCuts.back());
        auto max_v = max(verticalCuts[0], w - verticalCuts.back());

        for (auto i = 1; i < horizontalCuts.size(); ++i) {
            max_h = max(max_h, horizontalCuts[i]-horizontalCuts[i-1]);
        }

        for (auto i = 1; i < verticalCuts.size(); ++i) {
            max_v = max(max_v, verticalCuts[i]-verticalCuts[i-1]);
        }

        return (long)max_h * max_v % 1000000007;
    }
};

