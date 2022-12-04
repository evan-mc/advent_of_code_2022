import javax.naming.NameClassPair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        final List<Integer> lines = bufferedReader.lines()
                .map(s -> s.split(",|-"))
                .flatMap(Stream::of)
                .map(Integer::parseInt)
                .toList();
        computeOverlap(lines);
    }

    public static void computeOverlap(List<Integer> lines) {
        int fullyCoveredCount = 0;
        int anyCoveredCount = 0;
        for(int i = 0; i < lines.size(); i+=4) {
            int firstStart = lines.get(i);
            int firstEnd = lines.get(i + 1);
            int secondStart = lines.get(i + 2);
            int secondEnd = lines.get(i + 3);

            if((firstStart <= secondStart && firstEnd >= secondEnd) ||
               (secondStart <= firstStart && secondEnd >= firstEnd)) {
                ++fullyCoveredCount;
            }

            final Set<Integer> firstNums = IntStream.range(firstStart, firstEnd + 1).boxed().collect(Collectors.toSet());
            if(IntStream.range(secondStart, secondEnd + 1).anyMatch(firstNums::contains)) {
                ++anyCoveredCount;
            }
        }

        System.out.println("Problem 1: " + fullyCoveredCount);
        System.out.println("Problem 2: " + anyCoveredCount);
    }
}
