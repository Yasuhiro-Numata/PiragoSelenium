package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
	private static List <String> fileList;
	private static String sourcePath;
    
    public static void zip(String sourcePathDir, String desPath) {
    	fileList = new ArrayList < String > ();
    	sourcePath = sourcePathDir;
    	
    	File node = new File(sourcePath);
    	
    	generateFileList(node);
    	
    	zipIt(desPath);
    }
    

    private static void generateFileList(File node) {
    	 // add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }
    
    private static void zipIt(String zipFile) {
        byte[] buffer = new byte[1024];
        String source = new File(sourcePath).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            Log.info("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file: fileList) {
            
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(sourcePath + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            Log.info("Folder successfully compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateZipEntry(String file) {
        return file.substring(sourcePath.length(), file.length());
    }
}
