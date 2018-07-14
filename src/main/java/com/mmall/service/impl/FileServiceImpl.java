package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.FileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uplaod(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        String fileExtendsionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uplaodFIleName = UUID.randomUUID().toString() + "." + fileExtendsionName;
        logger.info("开始上传文件，上传文件的文件名：{}，上传的路径：{}，新文件名：{}", fileName, path, uplaodFIleName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uplaodFIleName);
        try {
            //写入文件
            file.transferTo(targetFile);
            //上传到FTP
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
        }
        return targetFile.getName();
    }

}
