#include <cstring>
#include <string>

class Solution {
public:
  // Store the size of the set as you go left.
  // Store the size of the set as you go right.
  int numSplits(std::string s) {
    int n = s.length();
    bool vis[26];
    int left[n], right[n];
    int cnt = 0;

    memset(vis, false, sizeof vis);

    left[0] = 1;
    vis[s[0] - 'a'] = true;

    for (int i = 1; i < n; ++i) {
      left[i] = left[i - 1];
      if (!vis[s[i] - 'a'])
        ++left[i], vis[s[i] - 'a'] = true;
    }

    memset(vis, false, sizeof vis);

    right[n - 1] = 1;
    vis[s[n - 1] - 'a'] = true;

    for (int i = n - 2; i >= 0; --i) {
      right[i] = right[i + 1];
      if (!vis[s[i] - 'a'])
        ++right[i], vis[s[i] - 'a'] = true;
      if (left[i] == right[i + 1])
        ++cnt;
    }

    return cnt;
  }
};