package com.aiit.service;

import com.aiit.dao.ICommentDao;
import com.aiit.dao.IFileDao;
import com.aiit.dao.IUsersDao;
import com.aiit.pojo.FilmComment;
import com.aiit.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/28 9:57
 * <p>代理模式</>
 */
@Transactional
@Service("commentService")
public class
CommentServiceImp implements ICommentService {
    private final ICommentDao commentDao;
    private final IUsersDao usersDao;

    @Autowired
    public CommentServiceImp(ICommentDao commentDao, IUsersDao usersDao) {
        this.commentDao = commentDao;
        this.usersDao = usersDao;
    }

    @Autowired
    IFileDao fileDao;

    @Override
    public boolean setCommentPicVideo(FilmComment filmComment, MultipartFile picFile, MultipartFile videoFile) {
        int randomNum = getSecondTimestamp();
        String pSuffix = getSuffix(picFile.getOriginalFilename());
        String vSuffix = getSuffix(videoFile.getOriginalFilename());

        String pRealPath = "E:/commentimgs/" + randomNum + pSuffix;
        String pDummyPath = "/pic/" + randomNum + pSuffix;

        String vRealPath = "E:/commentimgs/videos/" + randomNum + vSuffix;
        String vDummyPath = "/pic/videos/" + randomNum + vSuffix;

        filmComment.setImage(pDummyPath);
        filmComment.setVideo(vDummyPath);

        System.out.println(filmComment.toString());
        boolean flag1 = false, flag2 = false, flag3 = false;
        //先上传文件到文件夹，再将数据传到数据库
        flag1 = upLoadPhoto(picFile, pRealPath);
        flag2 = upLoadVideo(videoFile, vRealPath);
        if (flag1 && flag2) {
            flag3 = addFilmComment(filmComment);
        }
        return flag3;
    }

    @Override
    public boolean setCommentPic(FilmComment filmComment, MultipartFile picFile) {
        int randomNum = getSecondTimestamp();
        String pSuffix = getSuffix(picFile.getOriginalFilename());
        String pRealPath = "E:/commentimgs/" + randomNum + pSuffix;
        String pDummyPath = "/pic/" + randomNum + pSuffix;
        filmComment.setImage(pDummyPath);
        System.out.println(filmComment.toString());
        boolean flag1 = false, flag2 = false;
        flag1 = upLoadPhoto(picFile, pRealPath);
        if (flag1) {
            flag2 = addFilmComment(filmComment);
        }
        return flag2;
    }

    @Override
    public int deleteCommentList(String[] ids) {
        return commentDao.deleteCommentList(ids);
    }
    @Override
    public boolean deleteCommentById(String id) {
        return commentDao.deleteCommentById(id);
    }

    @Override
    public List<FilmComment> searchFilmCommentBymId(String mId) {
        return commentDao.searchFilmCommentBymId(mId);
    }

    @Override
    public boolean addViewNumById(String id) {
        String viewnum = commentDao.selectViewNumById(id);
        int newNum = Integer.parseInt(viewnum) + 1;
        return commentDao.addViewNumById(id, newNum);
    }

    @Override
    public List<FilmComment> searchFilmCommentByfId(String fId) {
        return commentDao.searchFilmCommentByfId(fId);
    }

    /**
     * 获取文件后缀
     *
     * @param originalName
     * @return
     */
    @Override
    public String getSuffix(String originalName) {
        return "." + originalName.substring(originalName.lastIndexOf(".") + 1);
    }

    @Override
    public List<FilmComment> randomSearchFilmComments() {
        return commentDao.randomSearchFilmComments();
    }

    @Override
    public FilmComment searchFilmCommentById(String id) {
        return commentDao.searchFilmCommentById(id);
    }

    @Override
    public Member searchMemberByName(String name) {
        return usersDao.searchMemberByName(name);
    }

    /**
     * 获取当前时间
     */
    @Override
    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    @Override
    public boolean addFilmComment(FilmComment filmComment) {
        return commentDao.addFilmComment(filmComment);
    }

    @Override
    public int getSecondTimestamp() {
        Date date = new Date();
        return fileDao.getSecondTimestamp(date);
    }

    @Override
    public List<FilmComment> showAllFComments() {
        return commentDao.showAllFComments();
    }

    @Override
    public boolean upLoadVideo(MultipartFile file, String realPath) {
        return fileDao.upLoadVideo(file, realPath);
    }

    @Override
    public boolean upLoadPhoto(MultipartFile file, String realPath) {
        return fileDao.uploadPhoto(file, realPath);
    }

    @Override
    public boolean deleteFile(String fileName) {
        return fileDao.deleteFileMethod(fileName);
    }
}
