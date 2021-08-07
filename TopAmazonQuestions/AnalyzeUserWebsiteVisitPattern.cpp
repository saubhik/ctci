#include <map>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
public:
    vector<string> mostVisitedPattern(
        vector<string>& username, vector<int>& timestamp, vector<string>& website) {
        unordered_map<string, map<int, string>> map;
        unordered_map<string, int> counts;
        int max_count = 0;
        string pattern;

        for (auto i = 0; i < username.size(); ++i) {
            map[username[i]][timestamp[i]] = website[i];
        }

        for (auto entry : map) {
            unordered_set<string> set;

            for (auto it = begin(entry.second); it != end(entry.second); ++it) {
                for (auto it1 = next(it); it1 != end(entry.second); ++it1) {
                    for (auto it2 = next(it1); it2 != end(entry.second); ++it2) {
                        set.insert(it->second + "$" + it1->second + "#" + it2->second);
                    }
                }
            }

            for (auto website : set) {
                counts[website]++;
            }
        }

        for (auto entry : counts) {
            if (entry.second >= max_count) {
                pattern = entry.second == max_count ? min(entry.first, pattern) : entry.first;
                max_count = entry.second;
            }
        }

        auto p1 = pattern.find("$");
        auto p2 = pattern.find("#");

        return { pattern.substr(0, p1), pattern.substr(p1 + 1, p2 - p1 - 1), pattern.substr(p2 + 1) };
    }
};
