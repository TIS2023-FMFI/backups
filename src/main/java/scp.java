import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class scp {
    public static void scp(String filename, String destination){
        List<String> command = new ArrayList<>();
        command.add("scp");
        command.add(filename);
        command.add(destination);
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        try{
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Zip file transferred successfully to: "+destination);
            } else {
                System.out.println("File transfer failed! \nExit code: "+exitCode);
            }
        } catch (IOException | InterruptedException e){
            System.out.println(e);
        }
    }

}
