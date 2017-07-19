package com.annie.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 15:51 2017/7/17 0017
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    // URL位置资源 前缀
    private static final String FILE_LOCATION = "file:";

    //　ClassPath位置资源　前缀
    private static final String CLASSPATH_LOCATION = "classpath:";

    // 相对路径资源 前缀
    private static final String WEB_INF_LOCATION = "WEB-INF:";

    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * 创建文件夹
     * @param dirPath
     * @return
     */
    public static boolean createDir(String dirPath){
        File dir = new File(dirPath);
        if(dir.exists()){
            logger.info("文件目录："+dirPath+"已存在。");
            return false;
        }

        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }

        // 创建目录
        if (dir.mkdirs()) {
            logger.info("创建目录：" + dirPath + "成功！");
            return true;
        } else {
            logger.info("创建目录：" + dirPath + "失败！");
            return false;
        }
    }

    /**
     * 通过spring 网页上传文件
     * @param path
     * @param file
     * @return
     */
    public static String uploadFile(String path, MultipartFile file) throws IOException{
        String newFileName = null;
        try {
            createDir(path);
            newFileName = newFileNameByTime(file.getOriginalFilename());
            Files.copy(file.getInputStream(), Paths.get(path, newFileName));
        } catch (IOException e) {
            logger.error("上传文件："+file.getOriginalFilename()+"失败", e);
        }
        return newFileName;
    }

    /**
     * 根据时间，文件重新命名
     * @param oldName
     * @return
     */
    public static String newFileNameByTime(String oldName){
        String newFileName = "";
        String suffix = suffix(oldName);
        Date date = new Date();
        newFileName = date.getTime() + suffix;
        return newFileName;
    }

    /**
     * 获取文件的后缀
     * @param fileName
     * @return null or suffix
     */
    public static String suffix(String fileName){
        String suffix = null;
        int start = fileName.lastIndexOf('.');
        if(start != -1){
            suffix = fileName.substring(start, fileName.length());
            logger.info("文件："+fileName+" 的后缀是："+suffix);
        }
        return suffix;
    }

    /**
     * 获取文件资源
     * @param location
     * @param fileName
     * @return
     */
    public static Resource getFileResource(String location, String fileName){
        Resource resource = resourceLoader.getResource(FILE_LOCATION + Paths.get(location, fileName).toString());
        return resource;
    }

}
