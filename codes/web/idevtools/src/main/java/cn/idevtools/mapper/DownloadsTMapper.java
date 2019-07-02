package cn.idevtools.mapper;

import cn.idevtools.po.DownloadsT;

import java.util.List;

public interface DownloadsTMapper {
    /**
     * 插入一条下载记录,时间为now() 王沁宽 2019.03.03
     * @param download
     * @return
     */
    int insertDownload(DownloadsT download);

    /**
     * 根据用户id,查询用户下载的工具信息 王沁宽 2019.03.03
     * @param userId
     * @return
     */
    List<DownloadsTMapper> selectDownloadToolByUserId(Integer userId);
}