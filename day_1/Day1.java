import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));

        final List<Integer> highestThreeCalories = new ArrayList<>();
        highestThreeCalories.add(0);
        highestThreeCalories.add(0);
        highestThreeCalories.add(0);

        int lowestValIdx = 0;
        int sum = 0;
        String s;
        while((s = bufferedReader.readLine()) != null) {
            if(s.isEmpty()) {
                if(sum > highestThreeCalories.get(lowestValIdx)) {
                    highestThreeCalories.set(lowestValIdx, sum);
                    for(int i = 0; i < 3; ++i) {
                        if(highestThreeCalories.get(i) < highestThreeCalories.get(lowestValIdx)) {
                            lowestValIdx = i;
                        }
                    }
                }

                sum = 0;
            } else {
                sum += Integer.parseInt(s);
            }
        }
        
        System.out.println("problem 1: " + highestThreeCalories.stream().max(Integer::compare).get());
        System.out.println("problem 2: " + highestThreeCalories.stream().mapToInt(Integer::intValue).sum());
    }
}
