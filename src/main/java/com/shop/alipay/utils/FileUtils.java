package com.shop.alipay.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.alipay.enums.Constants;
import com.shop.alipay.codec.Base64;
import java.io.*;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.*;

public final class FileUtils {

    /**
     * 文件读取缓冲区大小
     */
    public static final int BUFFER = 8 * 1024;

    /**
     * 获取文件的hash值
     * 
     * @param file 文件
     * @return 文件内容对应的hash值
     */
    public static String encryptFileToSHA(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] b = md.digest();
            return byte2hex(b);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /** 
     * 将二进制转化为16进制字符串 
     * @param b  二进制字节数组 
     * @return 16进制字符串 
     */
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 根据文件内容生产文件
     * 
     * @param filePath
     * @param passData
     * @return
     */
    public static boolean saveFile(String filePath, String passData) {
        FileOutputStream fos = null;
        ByteArrayInputStream bais = null;
        try {
            fos = new FileOutputStream(filePath);
            bais = new ByteArrayInputStream(passData.getBytes(Constants.UTF8.getValue()));

            int bytesRead, bufferSize = 8 * 1024;
            byte[] buffer = new byte[bufferSize];
            while ((bytesRead = bais.read(buffer, 0, bufferSize)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /** 
     *  根据路径删除指定的目录或文件，无论存在与否 
     *@param sPath  要删除的目录或文件 
     *@return 删除成功返回 true，否则返回 false。 
     */
    public static boolean delete(String sPath) {
        if (StringUtil.isBlank(sPath)) {
            return false;
        }
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在  
        if (!file.exists()) { // 不存在返回 false  
            return flag;
        } else {
            // 判断是否为文件  
            if (file.isFile()) { // 为文件时调用删除文件方法  
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法  
                return deleteDirectory(sPath);
            }
        }
    }

    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */
    private static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */
    private static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件  
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } //删除子目录  
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        //删除当前目录  
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文件转换为二进制数组
     * 
     * @param filePath 文件路径
     * @return
     * @throws IOException 
     */
    public static byte[] fileToByte(String filePath) {
        byte[] data = new byte[0];
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream(BUFFER);
                byte[] cache = new byte[BUFFER];
                int nRead = 0;
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                    out.flush();
                }
                data = out.toByteArray();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
        return data;
    }

    /**
     * 二进制数据写文件
     * 
     * @param bytes 二进制数据
     * @param filePath 文件生成目录
     * @throws IOException 
     */
    public static void byteArrayToFile(byte[] bytes, String filePath) throws IOException {
        InputStream in = new ByteArrayInputStream(bytes);
        File destFile = new File(filePath);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        destFile.createNewFile();
        OutputStream out = new FileOutputStream(destFile);
        byte[] cache = new byte[BUFFER];
        int nRead = 0;
        while ((nRead = in.read(cache)) != -1) {
            out.write(cache, 0, nRead);
            out.flush();
        }
        out.close();
        in.close();
    }

    /**
     * 获取单个文件的二进制base64加密字符串
     * 
     * @param passFile
     * @return
     */
    public static String binaryBase64(String passFile) {
        FileInputStream inStream = null;
        ByteArrayOutputStream bos = null;
        if (StringUtil.isNotBlank(passFile)) {
            File file = new File(passFile);
            if (file.exists()) {
                byte[] buffer = new byte[8 * 1024];
                int length = -1;
                try {
                    inStream = new FileInputStream(file);
                    bos = new ByteArrayOutputStream();
                    while ((length = inStream.read(buffer)) != -1) {
                        bos.write(buffer, 0, length);
                    }
                    bos.flush();
                } catch (FileNotFoundException e) {
                    //TODO
                } catch (IOException e) {
                    //TODO
                } finally {
                    try {
                        if (bos != null) {
                            bos.close();
                        }
                        if (inStream != null) {
                            inStream.close();
                        }
                    } catch (IOException e) {
                        //TODO
                    }
                }
            }
        }
        String result = null;

        if (bos != null) {
            result = Base64.encodeBase64String(bos.toByteArray());
        }
        return result;
    }

    /**
     * 通过文件路径读取文件内容,
     * 
     * @param fileName  文件路径
     * @param charSet   读取字符集
     * @return 文件內容
     */
    public static String readFile(String fileName, String charSet) {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(fileName), charSet);

            StringBuffer content = new StringBuffer();

            int line = -1;
            while ((line = in.read()) != -1) {
                content.append((char) line);
            }
            return content.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取系统下文件的绝对路径
     * 
     * @param relativePath
     * @return
     */
    public static String getAbsolutePath(String relativePath) {
        String path = FileUtils.class.getResource(relativePath).getPath();
        try {
            if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
                path = path.substring(1);
            }
            path = URLDecoder.decode(path, Constants.GBK.getValue());//中文解码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            path = null;
        }
        return path;
    }

    /**
     * 对文件列表中的文件取hash后签名
     * 
     * @param list
     * @return
     */
    public static String getHash(List<File> list) {
        Collections.sort(list, new Comparator<File>() {
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                }
                if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (File file : list) {
            map.put(file.getName(), encryptFileToSHA(file));
        }
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        String path = "d:/pass/ttt.txt";
        String logoPath = "d:/pass/boardingPass/logo.png";
        String content = "哈哈，测试内容";

        System.err.println(saveFile(path, content));

        File logFile = new File(logoPath);
        File dataFile = new File(path);

        List<File> list = new ArrayList<File>();
        list.add(logFile);
        list.add(dataFile);

        //createPassFile(list, "d:/pass/", "airchina");

    }

    /**
     * 将file文件复制到path路径，同时重命名
     * @param file
     * @param path
     * @return
     * @throws IOException 
     */
    public static boolean copy(File file, String path) {
        boolean flag = true;
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(path);

            byte[] tempByte = new byte[512];
            int n = 0;

            while ((n = in.read(tempByte)) != -1) {
                out.write(tempByte, 0, n);
            }

        } catch (FileNotFoundException e) {
            flag = false;
        } catch (IOException e) {
            flag = false;
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                flag = false;
            }
        }

        return flag;
    }

    /**
     * 将byte[] 数组保存到path路径
     * @param file
     * @param path
     * @return
     * @throws IOException 
     */
    public static boolean copy(byte[] file, String path) {
        boolean flag = true;
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(file);
        } catch (FileNotFoundException e) {
            flag = false;
        } catch (IOException e) {
            flag = false;
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                flag = false;
            }
        }

        return flag;
    }
}
