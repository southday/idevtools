package cn.idevtools.mapper;

import cn.idevtools.CommonTest;
import cn.idevtools.po.DownloadsT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试 DownloadsTmapper sql
 *
 * @author 王沁宽
 * @date 2019/3/3
 */
public class DownloadsTmapperTest extends CommonTest {

    @Autowired
    DownloadsTMapper downloadsTMapper;

    @Test
    public void downloadsTmapperTest(){
        downloadsTMapper.selectDownloadToolByUserId(1);

        DownloadsT downloads=new DownloadsT();
        downloads.setResId(1);
        downloads.setUserId(3);
        downloadsTMapper.insertDownload(downloads);
    }
}
