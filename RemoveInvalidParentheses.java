// Time Complexity : O(n^n)
// Space Complexity :  O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes, had to learn

/*
 * Problem# : 301
 * 
 * Return a list of unique strings with minimum number of removals. 
 * if we dont have to remove any - level 0
 * if we have to remove one -> level 1
 * if we have to remove two -> level 2
 * 
 * So, we can think of the problem as BFS, if we find a valid string at one level we need not go the next level.
 * 
 * for a BFS we need Queue and we have to process all elements in a level so we have to get the size of elements at each level
 * 
 * we keep track of the resultFound variable, if we find result at any level we terminate the loop and return result.
 * 
 */

import java.util.*;

public class RemoveInvalidParentheses {

    // Space complexity:
    // Time complexity:
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> alreadyVisited = new HashSet<>();
        queue.add(s);
        alreadyVisited.add(s);

        boolean resultFound = false;
        while (!queue.isEmpty() && !resultFound) {
            // process all items in the level
            int sizeOfItems = queue.size(); // helps process only children of that level, not the next level
            for (int i = 0; i < sizeOfItems; i++) {
                String currString = queue.poll();
                if (isValid(currString)) {
                    resultFound = true;
                    result.add(currString);
                } else {
                    if (!resultFound) { // if a result is found at a level, this will stop further processing.
                        for (int k = 0; k < currString.length(); k++) {
                            if (Character.isAlphabetic(currString.charAt(k)))
                                continue; // ignore any alphabets
                            String babyString = currString.substring(0, k) + currString.substring(k + 1);

                            if (!alreadyVisited.contains(babyString)) { // check if its already processed at any level
                                                                        // or the same level
                                queue.add(babyString);
                                alreadyVisited.add(babyString);
                            }

                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            // avoid alphabets
            if (Character.isAlphabetic(c))
                continue;
            if (c == '(') {
                count++;
            } else {
                if (count == 0) // if the count is already 0, then its invalid
                    return false;
                count--;
            }
        }

        return count == 0;
    }
}
