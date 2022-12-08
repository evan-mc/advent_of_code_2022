import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        List<String> lines = bufferedReader.lines().toList();
        computeAnswers(lines);
    }

    public static void computeAnswers(List<String> lines) {
        final Map<String, Directory> directories = new HashMap<>();
        Directory currentDirectory = new Directory(null);
        directories.put("/", currentDirectory);
        for(String s: lines) {
            if(s.startsWith("$")) {
                currentDirectory = parseCommand(s.split(" "), directories, currentDirectory);
            } else if(!s.startsWith("d")) {
                long fileSize = Long.parseLong(s.substring(0, s.indexOf(" ")));
                currentDirectory.updateDirSize(fileSize);
            }
        }

        final long partOne = directories.values().stream()
                .map(Directory::getTotalDirSize)
                .filter(size -> size <= 100000)
                .mapToLong(Long::valueOf)
                .sum();
        System.out.println("Problem one: " + partOne);

        final long freeSpaceNeeded = 30000000 - (70000000 - directories.get("/").getTotalDirSize());
        final long partTwo = directories.values().stream()
                .map(Directory::getTotalDirSize)
                .filter(size -> size >= freeSpaceNeeded)
                .mapToLong(Long::valueOf)
                .sorted()
                .findFirst()
                .getAsLong();
        System.out.println("Problem two: " + partTwo);
    }

    /**
     * parses command line command
     * @param commands array of commands
     * @param directories map of directories
     * @param currentDirectory current directory
     * @return the current directory after executing the command
     */
    public static Directory parseCommand(String[] commands, Map<String, Directory> directories, Directory currentDirectory) {
        if(commands[1].equals("cd")) {
            final String dirName = commands[2];
            if(dirName.equals("..")) {
                return currentDirectory.getParentDirectory();
            } else {
                if(!directories.containsKey(currentDirectory + "/" + dirName)) {
                    directories.put(currentDirectory + "/" + dirName, new Directory(currentDirectory));
                    Directory newDir = directories.get(currentDirectory + "/" + dirName);
                    return newDir;
                }
                return directories.get(currentDirectory + "/" + dirName);
            }
        }
        return currentDirectory;
    }
}
