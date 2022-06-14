package com.steven.demo01.controller;

import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@Api(tags = "文件相关接口")
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public CommonResult<String> fileUpload(@RequestParam("file") MultipartFile file) {
        log.info("mime type: {}",file.getContentType());
        log.info("name: {}",file.getName());
        log.info("original name: {}",file.getOriginalFilename());
        log.info("size: {}",file.getSize());
        return fileService.uploadFile(file);
    }
}
