import java.util.*;

public class Brainfuck {

    public void print(String input) {
        final Scanner sc = new Scanner(System.in);
        //    In the classic distribution, the array has 30,000 cells. (Source: Wikipedia)
        final int arrayLength = 30000;
        final byte[] memory = new byte[arrayLength];
        int pointer = 0;
        int loop = 0;
        for(int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '>':
                    pointer = (pointer == arrayLength-1) ? 0 : ++pointer;
                    break;
                case '<':
                    pointer = (pointer == 0) ? arrayLength-1 : --pointer;
                    break;
                case '+':
                    memory[pointer]++;
                    break;
                case '-':
                    memory[pointer]--;
                    break;
                case '.':
                    System.out.print((char) memory[pointer]);
                    break;
                case ',':
                    memory[pointer] = (byte) sc.next().charAt(0);
                    break;
                case '[':
                    if(memory[pointer] == 0) {
                        i++;
                        while(loop > 0 || input.charAt(i) != '[') {
                            if(input.charAt(i) == '[')
                                loop++;
                            if(input.charAt(i) == ']')
                                loop--;
                            i++;
                        }
                    }
                    break;
                case ']':
                    if(memory[pointer] != 0) {
                    i--;
                    while(loop > 0 || input.charAt(i) != '[') {
                        if(input.charAt(i) == ']')
                            loop++;
                        if(input.charAt(i) == '[')
                            loop--;
                        i--;
                    }
                    i--;
                }
                    break;
                default:
                    System.out.println(input.charAt(i) + ": Invalid Input");
                }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Input String:");
        String inputString = sc.next();
        new Brainfuck().print(inputString);
    }

}

//Input String : "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."