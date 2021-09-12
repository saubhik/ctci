#include <string>
#include <vector>

class Solution {
public:
  std::vector<std::string> fullJustify(std::vector<std::string> &words,
                                       int maxWidth) {
    std::vector<std::string> res;

    /* There are k words, and l alphabets in each line. */
    for (int i = 0, k, l; i < words.size(); i += k) {
      for (k = l = 0;
           i + k < words.size() && l + words[i + k].size() + k <= maxWidth;
           ++k) {
        l += words[i + k].size();
      }

      /* Last line is left justified. */
      bool last = i + k == words.size();

      /* There are k words and l non-space characters in the line.
       * We need to have maxWidth characters.
       * (maxWidth - l) spaces need to be distributed.
       * There are k words. So (k - 1) gaps.
       * E.g.: 5 spaces and 3 gaps means:
       * 5%3 gets (5/3+1) spaces.
       * Rest gets (5/3) spaces.
       */
      std::string tmp = words[i];
      int spaces = maxWidth - l;
      int gaps = k - 1;
      for (int j = 1; j < k; ++j) {
        if (last) {
          tmp += " ";
        } else {
          tmp += std::string(spaces / gaps, ' ');
          if (j <= (spaces % gaps)) {
            tmp += " ";
          }
        }
        tmp += words[i + j];
      }
      tmp += std::string(maxWidth - tmp.size(), ' ');

      res.push_back(tmp);
    }

    return res;
  }
};
