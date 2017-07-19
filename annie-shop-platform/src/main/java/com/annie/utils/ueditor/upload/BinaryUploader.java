package com.annie.utils.ueditor.upload;

import com.annie.config.AnnieProperties;
import com.annie.constant.Constant;
import com.annie.utils.FileUtil;
import com.annie.utils.ueditor.PathFormat;
import com.annie.utils.ueditor.define.AppInfo;
import com.annie.utils.ueditor.define.BaseState;
import com.annie.utils.ueditor.define.FileType;
import com.annie.utils.ueditor.define.State;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class BinaryUploader {

    private static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);

    @Autowired
    private AnnieProperties sAnnieProperties;

    private static AnnieProperties annieProperties;

    @PostConstruct
    public void init() {
        BinaryUploader.annieProperties = sAnnieProperties;
    }

    public static final State save2(HttpServletRequest request,
                                    Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {
                fileStream = iterator.next();

                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }

            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = fileStream.getName();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);

            String physicalPath = (String) conf.get("rootPath") + savePath;

            InputStream is = fileStream.openStream();
            State storageState = StorageManager.saveFileByInputStream(is,
                    physicalPath, maxSize);
            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (FileUploadException e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (IOException e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    /**
     * 重写ueditor提供的方法
     * spring mvc 在进行请求的时候，把request进行封装
     * 封装成MultipartHttpServletRequest
     * 所以，针对原始的servlet方法，需要重写
     *
     * @param request
     * @param conf
     * @return
     */
    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
            if (!ServletFileUpload.isMultipartContent(multipartRequest)) {
                return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
            }
            String savePath = (String) conf.get("savePath");
            String originFileName = multipartFile.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
            // 定义到保存的文件路径与文件名
            savePath = savePath + suffix;

            logger.info("savePath:" + savePath + "===originFileName:" + originFileName + "===suffix:" + suffix);

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            //自定义
            savePath = PathFormat.parse(savePath, originFileName);

            logger.info("savePath:" + savePath);
            String[] savePathBySplit_temp = savePath.split("/");

            String fileName = savePathBySplit_temp[savePathBySplit_temp.length - 1];

            // 换到指定存放目录
            String pathTemp = annieProperties.getFilePath() + Constant.IMG_FILE_PATH;
            // 目录不存在，则创建
            FileUtil.createDir(pathTemp);

            // 文件保存到指定路径
            State storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),  pathTemp + "/" + fileName, maxSize);

            if (storageState.isSuccess()) {
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;

        } catch (Exception e) {
            logger.error("ueditor 上传图片失败", e);
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
