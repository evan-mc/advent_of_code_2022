import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        String input = bufferedReader.readLine();
        System.out.println("Problem one: " + problemOne(input, 4));
        System.out.println("Problem two: " + problemOne(input, 14));
    }

    public static int problemOne(String input, int distinctCount) {
        //stores characters we've seen along with their index. If we see a duplicate start next check at idx past that character
        final Map<Character, Integer> characterIdx = new HashMap<>();
        characterIdx.put(input.charAt(0), 0);
        int start = 0;
        int end = 1;
        while(end < input.length()) {
            if(characterIdx.size() == distinctCount) {
                return end;
            }

            char currentChar = input.charAt(end);
            if(characterIdx.containsKey(currentChar)) {
                int oldStart = start;
                start = characterIdx.remove(currentChar) + 1;
                input.substring(oldStart, start).chars().forEach(c -> characterIdx.remove((char) c));
            }
            characterIdx.put(currentChar, end);

            ++end;
        }

        return -1;
    }
}
