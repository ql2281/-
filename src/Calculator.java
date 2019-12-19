import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class Calculator {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a == ' ') {
                continue;
            }
            if (Character.isDigit(a)) {
                num = a - '0';
                while (i < s.length()-1 && Character.isDigit(s.charAt(i+1))) {
                    num = num*10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0;
            } else if (a == '(') {
                ops.push('(');
            } else if (a == ')') {
                while (ops.peek() != '(') {
                    nums.push(operate(nums.pop(), nums.pop(), ops.pop()));
                }
                ops.pop();
            } else if (a == '+' || a == '-' || a== '*' || a == '/') {
                //检查stack中符号的优先级，如果stack中优先级高，就先处理stack中元素，一直到没有优先级高的元素。
                while (!ops.isEmpty() && priorityCheck(ops.peek(), a)) {
                    nums.push(operate(nums.pop(), nums.pop(), ops.pop()));
                }

                // check if string首位是'-'或者括号里首位是'-'，在nums里加0
                if (a == '-') {
                    if (nums.isEmpty()) {
                        nums.push(0);
                    } else {
                        int idx = i-1;
                        while (idx >= 0 && s.charAt(idx) == ' ') {
                            idx--;
                        }
                        if (s.charAt(idx) == '(') {
                            nums.push(0);
                        }
                    }
                }

                ops.push(a);
            }
        }

        while (!ops.isEmpty()) {
            nums.push(operate(nums.pop(), nums.pop(), ops.pop()));
        }

        return nums.pop();
    }

    private int operate(int b, int a, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; //assume b is not 0
        }
        return 0;
    }

    private boolean priorityCheck(char op1, char op2) {
        if (op1 == '(' || op1 == ')') {
            return false;
        }
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        }

        return true;
    }

    @Test
    public void test() {
        String s = "1 + 1"; // = 2
        s = " 6-4 / 2 "; // = 4
        s = "2*(5+5*2)/3+(6/2+8)";  // = 21
        s = "(2+6* 3+5- (3*14/7+2)*5)+3";  // = -12

        int res = calculate(s);

        Assert.assertEquals(-12, res);
        System.out.println(res);
    }
}
