import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    char type;
    int position;

    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }


}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        Stack<Bracket> stack = new Stack<>();

        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                Bracket bracket = new Bracket(next,position);
                stack.push(bracket);
                if(text.length() == 1){
                    // error
                    System.out.println("1");
                    return;
                }
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if(text.length() == 1){
                    // error
                    System.out.println("1");
                    return;
                }
                if(stack.isEmpty()){
                    //i have closing bracket and the stack is empty
                    //error
                    System.out.println(position+1);
                    return;
                }else {
                    if(stack.peek().Match(next)){
                        stack.pop();
                    }else {
                        System.out.println(position+1);
                        return;
                    }
                }
            }
        }
        if(!stack.isEmpty() ){
            System.out.println(stack.peek().position+1);
        }else {
            System.out.println("Success");
        }
        // Printing answer, write your code here
    }
}


