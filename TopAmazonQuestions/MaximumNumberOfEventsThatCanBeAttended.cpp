#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class Solution {
public:
    /*
     * At each day, I will attend a valid event which ends first.
     * Sort by start date, with min-heap of end date.
     */
    int maxEvents(vector<vector<int>>& events) {
        int max_events;
        priority_queue<int, vector<int>, greater<int>> pq;

        max_events = 0;

        sort(begin(events), end(events));

        for (int i = 0, day; !pq.empty() || i < events.size();) {
            if (pq.empty()) {
                day = events[i][0];
            }

            for (; i < events.size() && events[i][0] == day; ++i) {
                pq.push(events[i][1]);
            }

            if (!pq.empty()) {
                pq.pop();
                ++max_events;
                ++day;
            }

            /* Remove invalid events */
            while (!pq.empty() && pq.top() < day) {
                pq.pop();
            }
        }

        return max_events;
    }
};

