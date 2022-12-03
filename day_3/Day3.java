import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day3
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        List<String> lines = bufferedReader.lines().toList();
        printProblem1(lines);
        printProblem2(lines);
    }

   public static void printProblem1(List<String> lines) {
        System.out.println(lines.stream().map(Day3::findDuplicateChar)
                .map(Day3::getCharValue)
                .mapToInt(Integer::intValue)
                .sum()
        );
   }

   public static void printProblem2(List<String> lines) {
        final List<List<String>> groups = new ArrayList<>();
        for(int i = 0; i < lines.size(); i += 3) {
            List<String> groupedStrings = new ArrayList<>();
            groupedStrings.add(lines.get(i));
            groupedStrings.add(lines.get(i+1));
            groupedStrings.add(lines.get(i+2));
            groups.add(groupedStrings);
        }
        System.out.println(groups.stream().map(Day3::findSameChar)
                .map(Day3::getCharValue)
                .mapToInt(Integer::intValue)
                .sum()
        );
   }

    /**
     * finds and returns the first character that is a duplicate in both halfs of the string
     * @param l the string to search for
     * @return the duplicate character
     */
   public static char findDuplicateChar(String l) {
       final Set<Character> charsSeen = new HashSet<>();
       int splitPoint = l.length() / 2;

       for(int i = 0; i < l.length(); ++i) {
           if(i < splitPoint) {
               charsSeen.add(l.charAt(i));
           } else {
               char seenChar = l.charAt(i);
               if(charsSeen.contains(seenChar)) {
                   return seenChar;
               }
           }
       }

       return 'a';
   }

    /**
     * finds duplicate character within all strings in the list
     * @param l list contains 3 strings
     * @return char found in all 3 strings
     */
   public static char findSameChar(List<String> l) {

       final List<Set<Character>> list = l.stream()
               .map(
                       s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet())
               ).toList();

       list.get(0).retainAll(list.get(1));
       list.get(0).retainAll(list.get(2));
       return list.get(0).stream().findAny().get();
   }

    /**
     * returns the point value of a character in ascii form. Switches upper and lower case because the instructions have them
     * in opposite positions. If the character is lower case, switch it to uppercase and subtract 64 to get the ascii value.
     * If the character is upper case, switch it to lower case and subtract 64 - 6 (because there are 6 non-alphabetical
     * values between upper and lower case ascii characters).
     * @param c the upper or lower-case character
     * @return the ascii value associated with the character
     */
   public static int getCharValue(char c) {
       //greater than 90 is lower case, below is upper case
       //return c > 90 ? (Character.toUpperCase(c) - 64) : (Character.toLowerCase(c) - 70);
       int a = c > 90 ? (Character.toUpperCase(c) - 64) : (Character.toLowerCase(c) - 70);
       return a;
   }
}
