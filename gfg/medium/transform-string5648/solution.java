class Solution {
	// code here
	int transform(String s1, String s2) {

		if (s1.length() != s2.length()) {
			return - 1;
		}

		HashMap<Character, Integer> map = new HashMap<>();

		for (char c : s1.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (char c : s2.toCharArray()) {
			if (!map.containsKey(c)) {
				return - 1;
			}

			map.put(c, map.get(c) - 1);

			if (map.get(c) < 0) {
				return - 1;
			}
		}
		int n = s1.length() - 1;
		int m = s2.length() - 1;
		int count = 0;
		while (n >= 0 && m >= 0) {
			char one = s1.charAt(n);
			char two = s2.charAt(m);
			if (one == two) {
				n--;
				m--;
			} else {
				while ( n >= 0 && s1.charAt(n) != s2.charAt(m) ) {
					count++;
					n--;
				}
			}
		}

		return count;
	}
}