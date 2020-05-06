import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UploadController {

    String SFTPHOST = "fenrir.info.uaic.ro";
    int SFTPPORT = 22;
    String SFTPUSER = "ada.mocanu";
    String SFTPPASS = "";
    String SFTPWORKINGDIR = "/";

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;

public UploadController(){
    try {
        JSch jsch = new JSch();
        session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
        session.setPassword(SFTPPASS);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        System.out.println("Host connected.");
        channel = session.openChannel("sftp");
        channel.connect();
        System.out.println("sftp channel opened and connected.");
        channelSftp = (ChannelSftp) channel;
        channelSftp.cd(SFTPWORKINGDIR);

    } catch (Exception ex) {
        System.out.println("Exception found while tranfer the response.");
        ex.printStackTrace();
    } }


    public void urcare(String fileName) throws FileNotFoundException, SftpException {

        File f = new File(fileName);
        channelSftp.put(new FileInputStream(f), f.getName());

    }
}
