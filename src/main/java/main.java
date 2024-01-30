import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// connect to server: sftp wb@capek.ii.fmph.uniba.sk
//send files scp .\some_file.txt wb@capek.ii.fmph.uniba.sk:backup
public class main {
    public static void main(String[] args) {
        Config c = Config.loadConfig();
        if (c != null)
            c.dumpConfig();
        String destination = c.getStorageServer();
        String email = c.getEmailAdmin();
        List<Site> sites = c.getSites();
        List<String> paths = new ArrayList<>();
        List<String> exclude = new ArrayList<>();
        for(Site s:sites){
            for(String incl:s.getIncludeLocations()){
                paths.add(incl);
            }
            for(String excl:s.getExcludeLocations()){
                exclude.add(excl);
            }
        }

        try{
            FileWriter file = new FileWriter("list_of_files.txt");
            file.close();
        } catch (IOException e){
            email em = new email();
            em.sendError(email,e.getMessage());
            System.out.println(e.getMessage());
        }

        Recursive.ls_recursive(paths,exclude);
        String zip_name = "backup_zip_file.zip";
        zip.zip(zip_name);
        scp.scp(zip_name,destination);
        System.exit(0);
    }
}
/**
 try {
 // Connect to the website and fetch the HTML content
 Document doc = Jsoup.connect(url).get();
 String htmlContent = doc.html();

 // Create a backup folder if it doesn't exist
 File backupFolder = new File(destination);
 if (!backupFolder.exists()) {
 backupFolder.mkdir();
 }

 // Create a timestamp for the backup file
 SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy HH.mm");
 String timestamp = dateFormat.format(new Date());

 String filename = "backup_"+timestamp;
 // Save the fetched HTML content to a file in the destination folder
 File backupFile = new File(backupFolder, filename+ ".html");
 //FileUtils.writeStringToFile(backupFile, htmlContent, "UTF-8");
 zip z = new zip();
 z.main(backupFile,htmlContent,destination+filename);
 backupFile.delete();
 // Write backup details to the log file
 File logFile = new File(backupFolder, "backup_log.txt");
 String logEntry = timestamp + "\t" + filename + "\n";
 FileUtils.writeStringToFile(logFile, logEntry, "UTF-8", true);


 System.out.println("Website backed up successfully to: " + backupFile.getAbsolutePath());
 System.out.println("Backup log updated at: " + logFile.getAbsolutePath());

 // send an email to the user with backup details
 email e = new email();
 e.sendSuccessEmail(email,backupFile.getAbsolutePath(),logFile.getAbsolutePath());

 // Display the list of previous backups
 viewBackupLog(destination);
 } catch (IOException e) {
 System.out.println("Error: " + e.getMessage());
 }
 }

 private static void viewBackupLog(String destination) {
 File backupFolder = new File(destination);
 File logFile = new File(backupFolder, "backup_log.txt");

 System.out.println("Previous Backups:");
 try {
 if (logFile.exists()) {
 // Read and display the contents of the backup log file
 String logContent = FileUtils.readFileToString(logFile, "UTF-8");
 System.out.println(logContent);
 } else {
 System.out.println("No previous backups found.");
 }
 } catch (IOException e) {
 System.out.println("Error reading backup log: " + e.getMessage());
 }*/

/**private static void restoreBackup(String destination, String fileName)
 {

 }*/



/*

 TO EXECUTE IN CLI
javac WebsiteBackupCLI.java
java WebsiteBackupCLI <website_url> <destination_folder>
*/