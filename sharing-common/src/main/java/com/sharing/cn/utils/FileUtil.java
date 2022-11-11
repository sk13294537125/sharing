package com.sharing.cn.utils;

import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class FileUtil {

    /**
     * 文件复制
     * @param srcPathStr 原文件路径
     * @param desPathStr 目标文件夹
     */
    public static void fileMove(String srcPathStr,String desPathStr,String newFileName){

        //获取源文件的名称
        String suffix = srcPathStr.substring(srcPathStr.lastIndexOf("."));
        String destStr = desPathStr+File.separator+newFileName+suffix; //源文件地址

        try {
            File file = new File(destStr);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();// 能创建多级目录
            }
            if(!file.exists()){
                file.createNewFile();
            }
            InputStream fis = getInputStreamFromIntel(srcPathStr);
            FileOutputStream fos = new FileOutputStream(destStr); //创建输出流对象
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(destStr+"下载失败");
        }
    }


    /**
     * 创建临时文件夹
     * @return
     */
    public static String createTmpDir(){
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"temp";
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }/*else{
            deleteFileAll(file);
            file.mkdir();
        }*/
        return path;
    }


    /**
     * 删除文件下所有文件夹和文件
     * file：文件名
     * */
    public static void deleteFileAll(File file) {
        if (file.exists()) {
            File files[] = file.listFiles();
            int len = files.length;
            for (int i = 0; i < len; i++) {
                if (files[i].isDirectory()) {
                    deleteFileAll(files[i]);
                } else {
                    files[i].delete();
                }
            }
            file.delete();
        }
    }


    /**
     * 获取网络图片转换为输入流包括http&https
     * @param url
     * @return
     * @throws Exception
     */
    public static InputStream getInputStreamFromIntel(String url) throws Exception{
        InputStream in;
        URL u = new URL(url);
        if (u.getProtocol().toLowerCase().equals("https")) {
            HttpsURLConnection https = (HttpsURLConnection)u.openConnection();
            https.setSSLSocketFactory(createSSL());
            https.setConnectTimeout(5000);
            https.setReadTimeout(5000);
            https.setDoOutput(true);
            https.setRequestMethod("GET");
            https.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
            https.connect();
            System.out.println(https.getResponseCode() + " " + https.getResponseMessage());
            in = https.getInputStream();
        } else {
            HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.connect();
            System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());
            in = conn.getInputStream();
        }
        return in;
    }



    static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier(){

        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            return true;
        }
    };


    public static SSLSocketFactory createSSL() throws Exception{
        TrustManager[] tm =new TrustManager[]{
                myTrustManager
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(null, tm, null);
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        return ssf;
    }

    public static TrustManager myTrustManager = new X509TrustManager()
    {
        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1){}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    /**
     * 获取文件扩展名类型
     *
     * @param fileFullName
     * @return
     */
    public static String getFileExtension(final String fileFullName) {
        if (StringUtils.isBlank(fileFullName)) {
            return "";
        }
        int flag = fileFullName.lastIndexOf(".");
        if (flag != -1) {
            return fileFullName.substring(flag + 1).toLowerCase();
        }
        return "";
    }

}
