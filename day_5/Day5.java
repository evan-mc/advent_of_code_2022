import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Day5
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        List<String> lines = bufferedReader.lines().toList();
        List<ArrayDeque<Character>> stacks = createInitialStackList(lines);
        //have to make copy to re-use list but too lazy so just comment / uncomment the problem to solve
        //problemOne(lines, stacks);
        problemTwo(lines, stacks);
    }

    public static void problemOne(List<String> lines, List<ArrayDeque<Character>> stacks) {
        for(String line: lines) {
            if(line.startsWith("m")) {
                moveSingleCrate(line, stacks);
            } else if(line.startsWith("[") || line.startsWith("  ")) {
                createStack(line, stacks);
            }
        }

        for(ArrayDeque<Character> stack: stacks) {
            System.out.print(stack.peek());
        }
        System.out.println();
    }

    public static void problemTwo(List<String> lines, List<ArrayDeque<Character>> stacks) {
        for(String line: lines) {
            if(line.startsWith("m")) {
                moveMultipleCrates(line, stacks);
            } else if(line.startsWith("[") || line.startsWith("  ")) {
                createStack(line, stacks);
            }
        }

        for(ArrayDeque<Character> stack: stacks) {
            System.out.print(stack.peek());
        }
        System.out.println();
    }

    public static void moveMultipleCrates(String line, List<ArrayDeque<Character>> stacks) {
        List<Integer> movement = Arrays.stream(line.split(" "))
                .filter(s -> !s.startsWith("m") && !s.startsWith("f") && !s.startsWith("t"))
                .map(Integer::parseInt)
                .toList();

        int moveAmt = movement.get(0);
        //substract one from movement as input index starts at one instead of zero
        final ArrayDeque<Character> moveFromStack = stacks.get(movement.get(1) - 1);
        final ArrayDeque<Character> moveToStack = stacks.get(movement.get(2) - 1);
        final ArrayDeque<Character> tempStack = new ArrayDeque<>();
        while(moveAmt != 0) {
            tempStack.addFirst(moveFromStack.pop());
            --moveAmt;
        }

        while(!tempStack.isEmpty()) {
            moveToStack.addFirst(tempStack.pop());
        }
    }

    public static void moveSingleCrate(String line, List<ArrayDeque<Character>> stacks) {
        List<Integer> movement = Arrays.stream(line.split(" "))
                .filter(s -> !s.startsWith("m") && !s.startsWith("f") && !s.startsWith("t"))
                .map(Integer::parseInt)
                .toList();

        int moveAmt = movement.get(0);
        //substract one from movement as input index starts at one instead of zero
        final ArrayDeque<Character> moveFromStack = stacks.get(movement.get(1) - 1);
        final ArrayDeque<Character> moveToStack = stacks.get(movement.get(2) - 1);
        while(moveAmt != 0) {
            moveToStack.addFirst(moveFromStack.pop());
            --moveAmt;
        }
    }

    public static void createStack(String line, List<ArrayDeque<Character>> stacks) {
        //each actual character we are interested in would be 4 spaces apart. if empty, then no entry on that stack
        for(int i = 1; i < line.length(); i += 4) {
            char c = line.charAt(i);
            if(c != ' ') {
                stacks.get(i / 4).addLast(c);
            }
        }
    }

    public static List<ArrayDeque<Character>> createInitialStackList(List<String> lines) {
        int numOfStacks = lines.stream()
                .filter(s -> s.startsWith(" 1"))
                .map(s -> s.stripTrailing().split(" "))
                .map(s -> s[s.length - 1])
                .map(Integer::parseInt).findFirst().get();
        return IntStream.range(0, numOfStacks)
                .mapToObj(ArrayDeque<Character>::new).toList();
    }

}
