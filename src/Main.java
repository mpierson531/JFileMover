import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new RuntimeException("args was expected to have a length of 3");
        }

        final String sourcePathString = args[0].trim();
        final String targetDirPath = args[1].trim();

        final File sourceFile = new File(sourcePathString);

        if (!sourceFile.exists()) {
            throw new RuntimeException("Source did not exist");
        }

        final String fullTargetPath = targetDirPath + File.separator + sourceFile.getName();

        final File targetDir = new File(targetDirPath);
        final File targetFile = new File(fullTargetPath);

        if (targetDir.exists() && !targetDir.isDirectory()) {
            throw new RuntimeException("Expected the path to copy to to be a directory");
        }

        if (targetFile.exists()) {
            targetFile.delete();
        }

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile))) {
            try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile))) {
                int nextByte = input.read();

                while (nextByte != -1) {
                    output.write(nextByte);
                    nextByte = input.read();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}