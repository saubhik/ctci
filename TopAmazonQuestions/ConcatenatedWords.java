import java.util.ArrayList;
import java.util.List;

public class ConcatenatedWords {
    class Solution {
        private class Trie {
            class TrieNode {
                TrieNode[] children;
                boolean isEnd;
                
                public TrieNode() {
                    this.children = new TrieNode[26];
                    this.isEnd = false;
                }
            }
            
            TrieNode root;
            
            public Trie() {
                this.root = new TrieNode();
            }
            
            public void insert(String word) {
                TrieNode curr;
                
                curr = this.root;
                
                for (char ch : word.toCharArray()) {
                    if (curr.children[ch-'a'] == null) {
                        curr.children[ch-'a'] = new TrieNode();
                    }
                    
                    curr = curr.children[ch-'a'];
                }
                
                curr.isEnd = true;
            }
    
            public boolean isConcatenated(
                String word, TrieNode node, int count, int index) {
                if (index == word.length()) {
                    return count > 1 && node.equals(this.root);
                }
                
                node = node.children[word.charAt(index)-'a'];
                if (node == null) {
                    return false;
                }
                
                return isConcatenated(word, node, count, index+1) || 
                    (node.isEnd && 
                     isConcatenated(word, this.root, count+1, index+1));
            }
        }
        
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Trie trie;
            List<String> result;
            
            trie = new Trie();
            result = new ArrayList<>();
            
            for (String word : words) {
                trie.insert(word);
            }
            
            for (String word : words) {
                if (trie.isConcatenated(word, trie.root, 0, 0)) {
                    result.add(word);
                }
            }
            
            return result;
        }
    }
}
