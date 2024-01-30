import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zip {
//zip zip_name.zip @ < list_of_files.txt
    /**public static void main(String[] args){
        zip();
    }*/
    public static void zip(){
        String filename = "backup_zip_file.zip";
        try (BufferedReader reader = new BufferedReader(new FileReader("list_of_files.txt"));
             ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(filename))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String fileName = line.trim();

                //ZipEntry zipEntry = new ZipEntry(fileName); //this way it creates all the folders
                ZipEntry zipEntry = new ZipEntry(new File(fileName).getName()); //this way just the files are added

                zipOutputStream.putNextEntry(zipEntry);

                try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, bytesRead);
                    }
                }
                zipOutputStream.closeEntry();
            }

            System.out.println("ZIP file created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void main(File backupFile,String htmlContent, String filename) throws IOException {
        FileUtils.writeStringToFile(backupFile,htmlContent,"UTF-8");
        String sourceFile = backupFile.getAbsolutePath();
        FileOutputStream fos = new FileOutputStream(filename+".zip");

        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();
    }
}

