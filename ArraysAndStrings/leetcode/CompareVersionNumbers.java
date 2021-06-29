class Pair<T, U> {
	private T key;
	private U value;

	Pair(T key, U value) {
		this.key = key;
		this.value = value;
	}

	public T getKey() {
		return this.key;
	}

	public U getValue() {
		return this.value;
	}
}

public class CompareVersionNumbers {
	class SolutionOne {
		/*
		 * Single Pass.
		 * Time: O(max(M,N))
		 * Space: O(max(M,N))
		 */
		public int compareVersion(String version1, String version2) {
			Pair<Integer, Integer> pair1;
			Pair<Integer, Integer> pair2;
			
			for (int i = 0, j = 0; i < version1.length() || j < version2.length();) {
				pair1 = getNextInteger(version1, i);
				pair2 = getNextInteger(version2, j);
				
				if (pair1.getKey() < pair2.getKey()) {
					return -1;
				} else if (pair1.getKey() > pair2.getKey()) {
					return +1;
				}
				
				i = pair1.getValue() + 1;
				j = pair2.getValue() + 1;
			}
			
			return 0;
		}
		
		private Pair<Integer, Integer> getNextInteger(String version, int startIndex) {
			int endIndex;
			
			endIndex = startIndex;
			while (endIndex < version.length() && version.charAt(endIndex) != '.') {
				++endIndex;
			}
			
			if (endIndex == startIndex) {
				return new Pair(0, startIndex);
			}
			
			return new Pair(
				Integer.parseInt(version.substring(startIndex, endIndex)), endIndex);
		}
	}

	class SolutionTwo {
		/*
		 * Time: O(N + M + max(N,M))
		 * Space: O(N + M)
		 */
		public int compareVersion(String version1, String version2) {
			int i;
			int j;
			int revision1;
			int revision2;
			
			i = 0;
			j = 0;
			
			String[] v1Revisions = version1.split("\\.");
			String[] v2Revisions = version2.split("\\.");
			
			while (i < v1Revisions.length && j < v2Revisions.length) {
				revision1 = Integer.parseInt(v1Revisions[i++]);
				revision2 = Integer.parseInt(v2Revisions[j++]);
				
				if (revision1 < revision2) {
					return -1;
				} else if (revision1 > revision2) {
					return 1;
				}
			}
			
			while (i < v1Revisions.length) {
				if (Integer.parseInt(v1Revisions[i++]) > 0) {
					return 1;
				}
			}
			
			while (j < v2Revisions.length) {
				if (Integer.parseInt(v2Revisions[j++]) > 0) {
					return -1;
				}
			}
			
			return 0;
		}
	}
}
