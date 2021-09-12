#include <algorithm>
#include <unordered_map>
#include <vector>

class TimeMap {
public:
  std::unordered_map<std::string, std::vector<std::pair<int, std::string>>> mp;

  /** Initialize your data structure here. */
  TimeMap() {}

  void set(std::string key, std::string value, int timestamp) {
    mp[key].push_back({timestamp, value});
  }

  std::string get(std::string key, int timestamp) {
    auto &v = mp[key];
    auto it =
        std::upper_bound(begin(v), end(v), timestamp,
                         [](int val, auto &pr) { return val < pr.first; });
    return it == begin(v) ? "" : prev(it)->second;
  }
};

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap* obj = new TimeMap();
 * obj->set(key,value,timestamp);
 * string param_2 = obj->get(key,timestamp);
 */