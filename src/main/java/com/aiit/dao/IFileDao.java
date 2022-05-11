package com.aiit.dao;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface IFileDao {
    boolean isTrueVideoRange(MultipartFile file);
    boolean isTrueVideoSuffix(String originalName);
    boolean isTruRange(MultipartFile file);
    boolean isTrueSuffix(String originalName);
    boolean uploadPhoto(MultipartFile file,String realPath);
    boolean upLoadVideo(MultipartFile file,String realPath);
    boolean deleteFileMethod(String fileName);
    boolean deleteFile(String fileName);
    int getSecondTimestamp(Date date);
}
