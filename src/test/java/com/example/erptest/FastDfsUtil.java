package com.example.erptest;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author zhoujie
 * @version V1.0
 * @ClassName: FastDfsUtil
 * @Description: FastDfs文件管理简单工具类
 * @date 2018年11月27日 下午6:10:16
 */
public class FastDfsUtil {

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient storageClient = null;

    static {
        try {
            ClientGlobal.init("fdfs_client.conf");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (IOException | MyException e) {
            throw new RuntimeException("FastDfs工具类初始化失败!");
        }
    }

    /**
     * @param @param  inputStream 文件流
     * @param @param  filename 文件名称
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return String 返回文件在FastDfs的存储路径
     * @throws
     * @Title: fdfsUpload
     * @Description: 通过文件流上传文件
     */
    public static String fdfsUpload(InputStream inputStream, String filename) throws IOException, MyException {
        //后缀名
        String suffix = "";
        try {
            suffix = filename.substring(filename.lastIndexOf(".") + 1);
        } catch (Exception e) {
            throw new RuntimeException("参数filename不正确!格式例如：a.png");
        }
        //FastDfs的存储路径
        StringBuilder savepath = new StringBuilder();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buff)) != -1) {
            swapStream.write(buff, 0, len);
        }
        byte[] in2b = swapStream.toByteArray();
        //上传文件
        String[] strings = storageClient.upload_file(in2b, suffix, null);
        for (String str : strings) {
            //拼接路径
            savepath.append("/").append(str);
        }
        return savepath.toString();
    }

    /**
     * @param @param  filepath 本地文件路径
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return String 返回文件在FastDfs的存储路径
     * @throws
     * @Title: fdfsUpload
     * @Description: 本地文件上传
     */
    public static String fdfsUpload(String filepath) throws IOException, MyException {
        //后缀名
        String suffix = "";
        try {
            suffix = filepath.substring(filepath.lastIndexOf(".") + 1);
        } catch (Exception e) {
            throw new RuntimeException("上传的不是文件!");
        }
        //FastDfs的存储路径
        StringBuilder savePath = new StringBuilder();
        //上传文件
        String[] strings = storageClient.upload_file(filepath, suffix, null);
        for (String str : strings) {
            //拼接路径
            savePath.append("/").append(str);
        }
        return savePath.toString();
    }

    /**
     * @param @param  savepath 文件存储路径
     * @param @param  localPath 下载目录
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return boolean 返回是否下载成功
     * @throws
     * @Title: fdfsDownload
     * @Description: 下载文件到目录
     */
    public static boolean fdfsDownload(String savepath, String localPath) throws IOException, MyException {
        //存储组
        String group = "";
        //存储路径
        String path = "";
        try {
            //第二个"/"索引位置
            int secondindex = savepath.indexOf("/", 2);
            //类似：group1
            group = savepath.substring(1, secondindex);
            //类似：M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png
            path = savepath.substring(secondindex + 1);
        } catch (Exception e) {
            throw new RuntimeException("传入文件存储路径不正确!格式例如：/group1/M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png");
        }
        int result = storageClient.download_file(group, path, localPath);
        if (result != 0) {
            throw new RuntimeException("下载文件失败：文件路径不对或者文件已删除!");
        }
        return true;
    }

    /**
     * @param @param  savepath 文件存储路径
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return byte[] 字符数组
     * @throws
     * @Title: fdfsDownload
     * @Description: 返回文件字符数组
     */
    public static byte[] fdfsDownload(String savepath) throws IOException, MyException {
        byte[] bs = null;
        //存储组
        String group = "";
        //存储路径
        String path = "";
        try {
            //第二个"/"索引位置
            int secondindex = savepath.indexOf("/", 2);
            //类似：group1
            group = savepath.substring(1, secondindex);
            //类似：M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png
            path = savepath.substring(secondindex + 1);
        } catch (Exception e) {
            throw new RuntimeException("传入文件存储路径不正确!格式例如：/group1/M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png");
        }
        //返回byte数组
        bs = storageClient.download_file(group, path);
        return bs;
    }

    /**
     * @param @param  savepath 文件存储路径
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return boolean 返回true表示删除成功
     * @throws
     * @Title: fdfsDeleteFile
     * @Description: 删除文件
     */
    public static boolean fdfsDeleteFile(String savepath) throws IOException, MyException {
        //存储组
        String group = "";
        //存储路径
        String path = "";
        try {
            //第二个"/"索引位置
            int secondindex = savepath.indexOf("/", 2);
            //类似：group1
            group = savepath.substring(1, secondindex);
            //类似：M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png
            path = savepath.substring(secondindex + 1);
        } catch (Exception e) {
            throw new RuntimeException("传入文件存储路径不正确!格式例如：/group1/M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png");
        }
        //删除文件，0表示删除成功
        int result = storageClient.delete_file(group, path);
        if (result != 0) {
            throw new RuntimeException("删除文件失败：文件路径不对或者文件已删除!");
        }
        return true;
    }

    /**
     * @param @param  savepath 文件存储路径
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return FileInfo 文件信息
     * @throws
     * @Title: fdfdFileInfo
     * @Description: 返回文件信息
     */
    public static FileInfo fdfdFileInfo(String savepath) throws IOException, MyException {
        //存储组
        String group = "";
        //存储路径
        String path = "";
        try {
            //第二个"/"索引位置
            int secondindex = savepath.indexOf("/", 2);
            //类似：group1
            group = savepath.substring(1, secondindex);
            //类似：M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png
            path = savepath.substring(secondindex + 1);
        } catch (Exception e) {
            throw new RuntimeException("传入文件存储路径不正确!格式例如：/group1/M00/00/00/wKgBaFv9Ad-Abep_AAUtbU7xcws013.png");
        }
        FileInfo fileInfo = storageClient.get_file_info(group, path);
        return fileInfo;
    }

    public static String url(String url) {
//        /group1/M00/00/00/CgQJmmGkTkyABLZuAADFvll9EV4448.png
//        http://10.4.9.154:8888/M00/00/00/CgQJmmGkTkyABLZuAADFvll9EV4448.png
        String substring = url.substring(7);
        String httpUrl = "http://10.4.9.154:8888";
        return httpUrl + substring;
    }

    public static void main(String[] args) throws Exception {
        try {
//            System.out.print("测试上传");
            String s = FastDfsUtil.fdfsUpload("/Users/cchu/归档/1.png");
            System.out.print(s);
//            String s = FastDfsUtil.fdfsUpload("/Users/cchu/Downloads/测试图片.png");

            //上传文件。。。注意路径

//            System.out.println("存储路径："+ FastDfsUtil.url(s));
            //文件信息
//            System.out.println(FastDfsUtil.fdfdFileInfo("/group1/M00/00/00/CgQJmmGkam6AJsHCAADFvll9EV4981.png").toString());
            //System.out.println(FastDfsUtil.fdfsDeleteFile("/group1/M00/00/00/wKgBaFv9HJeAMaseAAIWRj8eKZQ103.jpg")); //删除文件
            //System.out.println(FastDfsUtil.fdfsUpload(new FileInputStream(new File("F:/图片/02.jpg")), "02.jpg"));//通过文件流上传文件
            //System.out.println(FastDfsUtil.fdfsDownload("/group1/M00/00/00/wKgBaFv9acaANK2oAAIWRj8eKZQ339.jpg").length); //获取文件byte[]
            //下载文件到本地(拒绝访问)
//            System.out.println(FastDfsUtil.fdfsDownload("/group1/M00/00/00/CgQJmmGkam6AJsHCAADFvll9EV4981.png", "/Users/cchu/Downloads/1/测试图片.png"));
//            ClientGlobal.init("fdfs_client.conf");
//            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
//            ProtoCommon.activeTest( trackerClient.getConnection().getSocket());
//            ProtoCommon.activeTest(trackerServer.getSocket());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}