package com.aiit.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @auther Mr Tang
 * @Date 2018/12/28 16:59
 */
@Repository("fileDao")
public class FileDao implements IFileDao {


    /**
     * 2018年10月24日下午8:02:04
     * 这个函数的功能是获取当前时间点与1970年1月1日的间隔秒数
     */
    @Override
    public int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    @Override
    public boolean isTrueVideoRange(MultipartFile file) {
        if (file.getSize() < 318137677) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTrueVideoSuffix(String originalName) {
        String suffix = originalName.substring(originalName.lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase("mp4") || suffix.equalsIgnoreCase("rmvb")) {
            return true;
        }
        return false;
    }

    /**
     * 判断图片大小是否在范围内
     *
     * @param file
     * @return
     */
    @Override
    public boolean isTruRange(MultipartFile file) {
        //System.out.println("size"+file.getSize());//max 2097152
        if (file.getSize() < 2097152) {
            return true;
        }
        return false;
    }

    /**
     * 2018年10月24日下午8:48:58
     * 这个函数的功能是判断文件后缀是否是jpg或gif格式
     */
    @Override
    public boolean isTrueSuffix(String originalName) {
        String suffix = originalName.substring(originalName.lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("png")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean upLoadVideo(MultipartFile file, String realPath) {
        if (isTrueVideoRange(file)) {
            if (isTrueVideoSuffix(file.getOriginalFilename())) {
                File newFile = new File(realPath);
                try {
                    file.transferTo(newFile);
                    System.out.println("上传视频成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                System.out.println("上传失败！视频后缀不正确");
            }
        } else {
            System.out.println("上传失败！视频超出了规定范围大小");
        }
        return false;
    }

    /**
     * 将图片上传到本地地址
     *
     * @param file
     * @return
     */
    @Override
    public boolean uploadPhoto(MultipartFile file, String realPath) {
        if (isTruRange(file)) {
            if (isTrueSuffix(file.getOriginalFilename())) {
                File newFile = new File(realPath);
                try {
                    file.transferTo(newFile);
                    System.out.println("上传图片成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                System.out.println("上传失败！图片后缀不正确");
            }
        } else {
            System.out.println("上传失败！图片超出了规定范围大小");
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     * @return
     */
    @Override
    public boolean deleteFileMethod(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
        }
        return false;
    }

    @Override
    public boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
