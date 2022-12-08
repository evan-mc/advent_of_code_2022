/**
 * represents a directory. contains the total size of files in this directory (directly or indirectly)
 * and its parent directory
 */
public class Directory
{
    private final Directory parentDirectory;
    private long totalDirSize;

    public Directory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
        totalDirSize = 0;
    }

    public Directory getParentDirectory() {
            return parentDirectory;
    }

    public long getTotalDirSize() {
        return totalDirSize;
    }

    public void updateDirSize(long dirSize) {
        totalDirSize += dirSize;
        if(parentDirectory != null) {
            parentDirectory.updateDirSize(dirSize);
        }
    }
}
