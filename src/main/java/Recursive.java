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
    public static void main(String[] args) {
        Path directory = Paths.get("C:\\Users\\mayoz\\Desktop\\test_backup");
        String no_include = "C:\\Users\\mayoz\\Desktop\\test_backup\\no_include";
        List<Path> paths = listFiles(directory);
        //List<Path> paths = listDirectories(directory);
        //paths.forEach(x -> System.out.println(x));
        try{
            FileWriter file = new FileWriter("list_of_files.txt");
            for(Path p: paths){
                if(!p.toString().contains(no_include)){
                    file.write(p.toString()+"\n");
                }
                else{
                    System.out.println(p);
                }

            }
            file.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        /**for(Path p: paths){
            System.out.println(p);
            List<Path> files = listFiles(p);
            files.forEach(x -> System.out.println(x));
        }*/
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

