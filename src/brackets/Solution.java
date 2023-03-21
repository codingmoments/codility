package brackets;

import java.util.Stack;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
 * 
 * Idea:
 * Simple! Use Stack to check the matching brackets.
 * 
 * Traverse the characters in the String.
 * When you see the opening bracket, push that in the Stack.
 * When you see the closing bracket, check if stack has matching opening bracket.
 * At the end, stack should be empty. 
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.solution(")(");
  }

  public int solution(String S) {
    Stack<Character> stack = new Stack<>();

    for (char c : S.toCharArray()) {
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      }
      else {
        if (stack.empty()) {
          return 0;
        }

        Character previousChar = stack.pop();

        if ((c == ')' && previousChar != '(') || (c == '}' && previousChar != '{') || (c == ']' && previousChar != '[')) {
          return 0;
        }
      }
    }

    return stack.empty() ? 1 : 0;
  }

}
