import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    private int n;
    private List<String> combinations;
    
    public List<String> generateParenthesis(int n) {
        StringBuilder currentComb;
        
        this.n = n;
        currentComb = new StringBuilder();
        combinations = new ArrayList<>();
        
        calcCombinations(0, 0, currentComb);
        
        return combinations;
    }
    
    private void calcCombinations(int open, int closed, StringBuilder currentComb) {
        if (open < n) {
            currentComb.append('(');
            calcCombinations(open + 1, closed, currentComb);
            currentComb.setLength(currentComb.length() - 1);
        }
        
        if (closed < open) {
            currentComb.append(')');
            calcCombinations(open, closed + 1, currentComb);
            currentComb.setLength(currentComb.length() - 1);
        }
        
        
        if (open == n && closed == open) {
            combinations.add(currentComb.toString());
        }
    }
}