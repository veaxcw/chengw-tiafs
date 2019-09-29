package com.chengw.tiafs.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {

    public static String filePath = "D:/veax/java_OOP/java_OOP/webssm-Project/demo-1/src/webapp/upload/";

    public static final int MEMORY_THRESHOLD = 1024*1024*3;
    public static final int MAX_FILE_SIZE = 1024*1024*30;
    public static final int MAX_REQUEST_SIZE = 1024*1024*50;

    /**首先解决使用设置表单属性enctype="multipart/form-data"之后传值问题**/
    @SuppressWarnings("Duplicates")
    public static Map<String,String> saveForm(HttpServletRequest request, String leafPath){
        //用来存储form表单中的数据
        Map<String,String> info = new HashMap<String, String>();

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //设置临时文件目录
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        //设置临界内存值
        diskFileItemFactory.setSizeThreshold(CommonUtils.MEMORY_THRESHOLD);
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        //设置最大文件上传值
        upload.setSizeMax(CommonUtils.MAX_REQUEST_SIZE);
        //设置最大请求值
        upload.setFileSizeMax(CommonUtils.MAX_FILE_SIZE);
        upload.setHeaderEncoding("utf-8");
        //设置文件目录
        String uploadPath = CommonUtils.filePath + leafPath;
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for(FileItem fileItem:fileItems){
                //判断表单是普通类型还是文件类型
                if(fileItem.isFormField()){
                    info.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
                }else{
                    //文件类型则读取文件
                    try {
                        String fileName = new File(fileItem.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        fileItem.write(storeFile);
                        info.put("rpath",storeFile.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return info;
    }


    public  static <T> T toBean(Map map,Class<T> clazz){//将map中的数据分装到对象中区
        try {
            T bean = clazz.newInstance();
            ConvertUtils.register((Converter) new DateConverter(),java.util.Date.class);
            BeanUtils.populate(bean,map);
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }




}
