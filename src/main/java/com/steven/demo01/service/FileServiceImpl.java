package com.steven.demo01.service;

import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Component
public class FileServiceImpl implements FileService {
    @Override
    public CommonResult<String> uploadFile(MultipartFile multipartFile) {
        // 文件保存路径
        FileOutputStream fileOutputStream = null;
        try {
            File parentFile = new File("D:\\filepath");
            if (!parentFile.exists()) parentFile.mkdir();
            //  文件名
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            File file = new File(parentFile, fileName);

            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
        } catch (AssertionError | IOException e) {
            e.printStackTrace();
            throw new CustomException(CommonResult.error("upload fail", ""));
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件关闭错误", e);
            }
        }
        return CommonResult.success("upload success");
    }
}
