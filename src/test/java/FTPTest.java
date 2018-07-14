import com.google.common.collect.Lists;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPTest {


    @Test
    public void test1() throws IOException {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis=null;
        try {
            ftpClient.connect("192.168.5.105");
            ftpClient.login("ftpuser","123456");
            ftpClient.changeWorkingDirectory("image");
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();

            List<File> fileList= Lists.newArrayList(new File("E:\\IDEAWorkSpace\\mmall\\target\\mmall\\upload\\01.jpg"));
            for(File fileItem : fileList){
                fis = new FileInputStream(fileItem);
                boolean b = ftpClient.storeFile(fileItem.getName(), fis);
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            ftpClient.disconnect();
        }
    }

}
