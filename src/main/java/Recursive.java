import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.FileVisitOption;
//import java.nio.file.FileVisitOption.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Recursive {
    public static void ls_recursive(List<String> path, List<String> exclude) {
        for(String p:path){
            Path directory = Paths.get(p);
            List<Path> files = listFiles(directory);
            try{
                FileWriter file = new FileWriter("list_of_files.txt", true);
                for(Path filepath: files){
                    Boolean excluded = false;
                    for(String ex:exclude){
                        if(filepath.toString().contains(ex)) excluded = true;
                    }
                    if(!excluded){
                        file.write(filepath.toString()+"\n");
                    }
                }
                file.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Path> listFiles(Path path){

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());

        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    public static List<Path> listDirectories(Path path) {

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isDirectory)
                    .collect(Collectors.toList());
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
        return result;

    }
/**
        try {
            Files.walk(directory, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // Handle failure
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // Optionally, you can perform actions after visiting each directory
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

