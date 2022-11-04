package com.steven.demo01.service;

import com.steven.demo01.domain.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    CommonResult<String> uploadFile(MultipartFile multipartFile);
}
