import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignInMemoryFileSystem {
    class FileSystem {
        class File {
            String content;
            
            File() {
                this.content = "";
            }
        }
        
        class Directory {
            Map<String, File> files;
            Map<String, Directory> directories;
            List<String> names;
            
            Directory() {
                this.files = new HashMap<>();
                this.directories = new HashMap<>();
                this.names = new ArrayList<>();
            }
        }
        
        private Directory root;
    
        public FileSystem() {
            root = new Directory(); 
        }
        
        public List<String> ls(String path) {
            String[] names;
            Directory curr;
            
            curr = this.root;
            names = path.split("/");
                
            for (int i = 1; i < names.length; ++i) {
                if (curr.directories.get(names[i]) == null) {
                    if (i == names.length-1 && 
                        curr.files.containsKey(names[i])) {
                        return Arrays.asList(new String[] {names[i]});
                    }
                    return new ArrayList<>();
                }
                curr = curr.directories.get(names[i]);
            }
            
            return curr.names;
        }
        
        public void mkdir(String path) {
            String[] names;
            Directory curr;
            
            names = path.split("/");
            curr = this.root;
            
            for (int i = 1; i < names.length; ++i) {
                if (!curr.directories.containsKey(names[i])) {
                    curr.directories.put(names[i], new Directory());
                    insert(curr.names, names[i]);
                }
                curr = curr.directories.get(names[i]);
            }        
        }
        
        public void addContentToFile(String filePath, String content) {
            String[] names;
            Directory curr;
            File file;
            int i;
            
            names = filePath.split("/");
            curr = this.root;
            
            for (i = 1; i < names.length-1; ++i) {
                curr = curr.directories.get(names[i]);
            }
            
            if (!curr.files.containsKey(names[i])) {
                curr.files.put(names[i], new File());
                insert(curr.names, names[i]);
            }
            
            file = curr.files.get(names[i]);
            file.content += content;
        }
        
        public String readContentFromFile(String filePath) {
            String[] names;
            Directory curr;
            int i;
            
            names = filePath.split("/");
            curr = this.root;
            
            for (i = 1; i < names.length-1; ++i) {
                curr = curr.directories.get(names[i]);
            }
            
            return curr.files.get(names[i]).content;
        }
        
        private void insert(List<String> list, String element) {
            int index = Collections.binarySearch(list, element);
            if (index < 0) {
                index = -index - 1;
            }
            list.add(index, element);
        }
    }
    
    /**
     * Your FileSystem object will be instantiated and called as such:
     * FileSystem obj = new FileSystem();
     * List<String> param_1 = obj.ls(path);
     * obj.mkdir(path);
     * obj.addContentToFile(filePath,content);
     * String param_4 = obj.readContentFromFile(filePath);
     */
}
