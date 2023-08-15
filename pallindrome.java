import java.util.Map;

public class pallindrome {
 
    public static int getLongestPallindrome(String X, int i, int j,
                                        Map<String, Integer> lookup)
    {

        if (i > j) {
            return 0;
        }
 
        if (i == j) {
            return 1;
        }

        String key = i + "|" + j;
 
        if (!lookup.containsKey(key))
        {
  
 
            if (X.charAt(i) == X.charAt(j)) {
                lookup.put(key, getLongestPallindrome(X, i + 1, j - 1, lookup) + 2);
            }
            else {
 
 
            lookup.put(key, Integer.max(getLongestPallindrome(X, i, j - 1, lookup),
                                        getLongestPallindrome(X, i + 1, j, lookup)));
            }
        }
 
        return lookup.get(key);
    }
 
}
