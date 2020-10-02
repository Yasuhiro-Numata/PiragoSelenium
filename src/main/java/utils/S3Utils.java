package utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import controllers.InitMethod;

import java.io.File;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class S3Utils {
//	public static void main(String[] args) throws IOException {
//        String bucket_name = "longkyo1988-test";
//        String file_path = "report/data_1578284509075.zip";
//        String path = putObject(bucket_name, file_path);
//        System.out.println("Done!=" + path);
//    }
	
	public static String putObject(String bucket_name, String file_path) {
        String key_name = Paths.get(file_path).getFileName().toString();

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(InitMethod.AwsS3Region).build();
        s3.putObject(bucket_name, key_name, new File(file_path));
        
        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 7);
        
        String presignUrl = s3.generatePresignedUrl(bucket_name, key_name, c.getTime()).toString();
        return presignUrl;
	}
}
