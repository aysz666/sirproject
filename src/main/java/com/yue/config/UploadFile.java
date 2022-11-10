package com.yue.config;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class UploadFile {
//    上传文件
    public static Boolean httpUpload(String name,MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        //保存上传的资源文件路径，路径在部署jar包同级目录。
        String path = System.getProperty("user.dir")+"/static/upload/";
        File dir = new File(path);
        // 如果不存在则创建目录
        if(!dir.exists()){
            dir.mkdirs();
        }

//        本地下载文件
//        String uploadFilePath= null;
//        try {
//            uploadFilePath = ResourceUtils.getURL("src/main/resources/static").getPath();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        File dest = new File(uploadFilePath + '/' + name +fileName);


        File dest = new File(dir.getAbsolutePath() + '/' + name +fileName);
        System.out.println(dest.getParentFile());

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    文件下载
public static Boolean fileDownLoad(HttpServletResponse response, String fileName){


    String uploadFilePath = System.getProperty("user.dir")+"/static/upload/";
    File dir = new File(uploadFilePath);

//    本地下载
//    String uploadFilePath= null;
//    try {
//        uploadFilePath = ResourceUtils.getURL("src/main/resources/static").getPath();
//    } catch (FileNotFoundException e) {
//        throw new RuntimeException(e);
//    }
//    File file = new File(uploadFilePath + fileName);

    File file = new File(dir.getAbsolutePath() +'/'+ fileName);
    if(!file.exists()){
        return false;
    }
    response.reset();
    response.setContentType("application/octet-stream");
    response.setCharacterEncoding("utf-8");
    response.setContentLength((int) file.length());
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

    try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
        byte[] buff = new byte[1024];
        OutputStream os  = response.getOutputStream();
        int i = 0;
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
    } catch (IOException e) {
        return false;
    }
    return true;
}
}
