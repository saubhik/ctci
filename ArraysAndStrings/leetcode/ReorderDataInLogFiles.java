import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReorderDataInLogFiles {
	class Solution {
		public String[] reorderLogFiles(String[] logs) {
			List<String> digitLogs;
			List<String> allLogs;
			
			digitLogs = new ArrayList<>();
			allLogs = new ArrayList<>();
			
			for (int i = 0; i < logs.length; ++i) {
				if (isDigitLog(logs[i])) {
					digitLogs.add(logs[i]);
				} else {
					allLogs.add(logs[i]);
				}
			}
			
			Collections.sort(allLogs, comp);
			
			allLogs.addAll(digitLogs);
			
			return allLogs.toArray(new String[0]);
		}
		
		private Comparator<String> comp = new Comparator<>() {
			@Override
			public int compare(String log1, String log2) {
				int i;
				int j;
				int log1EndStart;
				int log2EndStart;
				int endComp;
				int identifierComp;
				String log1End;
				String log2End;
				String log1Identifier;
				String log2Identifier;
	
				for(i = 0; i < log1.length() && log1.charAt(i) != ' '; ++i);
				log1EndStart = i;
	
				for(j = 0; j < log2.length() && log2.charAt(j) != ' '; ++j);
				log2EndStart = j;
	
				log1End = log1.substring(log1EndStart);
				log2End = log2.substring(log2EndStart);
	
				endComp = log1End.compareTo(log2End);
	
				log1Identifier = log1.substring(0, log1EndStart);
				log2Identifier = log2.substring(0, log2EndStart);
	
				identifierComp = log1Identifier.compareTo(log2Identifier);
	
				if (endComp == 0) {
					return identifierComp;
				}
	
				return endComp;
			}
		};
		
		private boolean isDigitLog(String log) {
			int i;
			
			i = 0;
			
			while (i < log.length() && log.charAt(i) != ' ') {
				++i;
			}
			
			return Character.isDigit(log.charAt(i+1));
		}
	}

	class SolutionTwo {
		public String[] reorderLogFiles(String[] logs) {
			Comparator<String> comp = new Comparator<>() {
				@Override
				public int compare(String log1, String log2) {
					String[] split1;
					String[] split2;
					boolean isDigit1;
					boolean isDigit2;
					int comp;
					
					split1 = log1.split(" ", 2);
					split2 = log2.split(" ", 2);
					
					isDigit1 = Character.isDigit(split1[1].charAt(0));
					isDigit2 = Character.isDigit(split2[1].charAt(0));
					
					if (!isDigit1 && !isDigit2) {
						comp = split1[1].compareTo(split2[1]);
						if (comp == 0) {
							return split1[0].compareTo(split2[1]);
						}
						return comp;
					}
					
					if (isDigit1 && !isDigit2) {
						return +1;
					} else if (!isDigit1 && isDigit2) {
						return -1;
					} else {
						return 0;
					}
				}
			};
				
			Arrays.sort(logs, comp);
	
			return logs;
		}
	}
}
