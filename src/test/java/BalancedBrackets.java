import java.util.List;
import java.util.Stack;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BalancedBrackets {

  static String isBalanced(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '{' || c == '[' || c == '(') {
        stack.push(c);
      }
      if (c == '}' || c == ']' || c == ')') {
        if (!stack.isEmpty()) {
          final Character pop = stack.pop();
          if (c == '}' && !pop.equals('{')) {
            return "NO";
          }
          if (c == ']' && !pop.equals('[')) {
            return "NO";
          }
          if (c == ')' && !pop.equals('(')) {
            return "NO";
          }
        } else {
          return "NO";
        }
      }
    }
    return stack.isEmpty() ? "YES" : "NO";
  }

  @Test
  public void test1() {
    assertEquals("YES", isBalanced("{[()]}"));
  }

  @Test
  public void test2() {
    assertEquals("NO", isBalanced("{[(])}"));
  }

  @Test
  public void test3() {
    assertEquals("YES", isBalanced("{{[[(())]]}}"));
  }

  @Test
  public void test4() {
    assertEquals("YES", isBalanced("{(([])[])[]}"));
  }

  @Test
  public void test5() {
    assertEquals("NO", isBalanced("{(([])[])[]]}"));
  }

  @Test
  public void test6() {
    assertEquals("YES", isBalanced("{(([])[])[]}[]"));
  }

  @Test
  public void test7() {
    assertEquals("YES", isBalanced("[()][{}()][](){}([{}(())([[{}]])][])[]([][])(){}{{}{[](){}}}()[]({})[{}{{}([{}][])}]"));
  }

  @Test
  public void test9() {
    assertEquals("NO", isBalanced("[(({"));
  }

  @Test
  public void test10() {
    final List<String> inputs = FileUtil.readFile("BalancedBracketsInput.txt");
    final List<String> outputs = FileUtil.readFile("BalancedBracketsOutput.txt");
    for (int i = 0; i < inputs.size(); i++) {
      try {
        assertEquals(outputs.get(i), isBalanced(inputs.get(i)));
      } catch (AssertionError e) {
        System.out.println("iteration: " + i);
        System.out.println("input: " + inputs.get(i));
        System.out.println("output: " + outputs.get(i));
        throw e;
      }
    }
  }
}
