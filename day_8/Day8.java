import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day8
{
    //this is seriously one of the most god awful implementations ive ever written. I'm embarrassed to even commit this
    //but im doing it anyways because im lazy. It generates the correct answer, but this code sucks.
    static boolean theWholeWay = false;
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        final List<List<Integer>> forest = new ArrayList<>();
        for(String s: bufferedReader.lines().toList()) {
            final ArrayList<Integer> row = new ArrayList<>();
            for(char c: s.toCharArray()) {
                row.add(Integer.parseInt("" + c));
            }
            forest.add(row);
        }

        problemOne(forest);
        problemTwo(forest);
    }

    public static void problemOne(List<List<Integer>> forest) {
        int visibleCount = 0;
        for(int i = 0, listLen = forest.size(); i < listLen; ++i) {
            for(int j = 0, innerListLen = forest.get(0).size(); j < innerListLen; ++j) {
                if(i == 0 || j == 0 || i == listLen - 1 || j == innerListLen - 1) {
                    ++visibleCount;
                } else if(checkIfVisible(forest, i, j)) {
                    ++visibleCount;
                }
            }
        }

        System.out.println("Problem one: " + visibleCount);
    }

    public static void problemTwo(List<List<Integer>> forest) {
        int biggestVisibleCount = 0;
        for(int i = 0, listLen = forest.size(); i < listLen; ++i) {
            for(int j = 0, innerListLen = forest.get(0).size(); j < innerListLen; ++j) {
                theWholeWay = false;
                int left = checkLeft(forest, i, j);
                left += theWholeWay ? 0 : 1;
                theWholeWay = false;
                int right = checkRight(forest, i, j);
                right += theWholeWay ? 0 : 1;
                theWholeWay = false;
                int up = checkUp(forest, i, j);
                up += theWholeWay ? 0 : 1;
                theWholeWay = false;
                int down = checkDown(forest, i, j);
                down += theWholeWay ? 0 : 1;
                int currentVisibleCount = left * right * up * down;
                if(currentVisibleCount > biggestVisibleCount) {
                    biggestVisibleCount = currentVisibleCount;
                }
            }
        }

        System.out.println("Problem two: " + biggestVisibleCount);
    }

    public static boolean checkIfVisible(List<List<Integer>> forest, int i, int j) {
       return (checkLeft(forest, i, j) == j || checkRight(forest, i, j) == forest.get(i).size()-1 - j
                || checkUp(forest, i, j) == i || checkDown(forest, i, j) == forest.size()-1 - i);
    }

    public static int checkLeft(List<List<Integer>> forest, int i, int j) {
        int currentHeight = forest.get(i).get(j--);
        int totalHeightValue = 0;
        while(j >= 0) {
            if(currentHeight <= forest.get(i).get(j)) {
                return totalHeightValue;
            }
            --j;
            ++totalHeightValue;
        }

        theWholeWay = true;
        return totalHeightValue;
    }

    public static int checkRight(List<List<Integer>> forest, int i, int j) {
        int currentHeight = forest.get(i).get(j++);
        int totalHeightValue = 0;
        while(j < forest.get(i).size()) {
            if(currentHeight <= forest.get(i).get(j)) {
                return totalHeightValue;
            }
            ++j;
            ++totalHeightValue;
        }

        theWholeWay = true;
        return totalHeightValue;
    }

    public static int checkUp(List<List<Integer>> forest, int i, int j) {
        int currentHeight = forest.get(i--).get(j);
        int totalHeightValue = 0;
        while(i >= 0) {
            if(currentHeight <= forest.get(i).get(j)) {
                return totalHeightValue;
            }
            --i;
            ++totalHeightValue;
        }

        theWholeWay = true;
        return totalHeightValue;
    }

    public static int checkDown(List<List<Integer>> forest, int i, int j) {
        int currentHeight = forest.get(i++).get(j);
        int totalHeightValue = 0;
        while(i < forest.size()) {
            if(currentHeight <= forest.get(i).get(j)) {
                return totalHeightValue;
            }
            ++i;
            ++totalHeightValue;
        }

        theWholeWay = true;
        return totalHeightValue;
    }
}
