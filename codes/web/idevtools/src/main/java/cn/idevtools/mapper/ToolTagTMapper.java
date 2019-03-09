package cn.idevtools.mapper;

import cn.idevtools.po.ToolTagT;

import java.util.List;

public interface ToolTagTMapper {
    /**
     * 查询全部tag 王沁宽 2019.03.07
     * @return
     */
    List<ToolTagT> selectAllToolTag();
    /**
     * 根据id删除tag 王沁宽 2019.03.07
     * @return
     */
    int deleteToolTagById(Integer tagId);
    /**
     * 查询tag 王沁宽 2019.03.07
     * @return
     */
    List<ToolTagT> selectToolTag(ToolTagT toolTag);
    /**
     * 更新tag 王沁宽 2019.03.07
     * @return
     */
    int updateToolTag(ToolTagT toolTag);
    /**
     * 添加tag 王沁宽 2019.03.07
     * @return
     */
    int insertToolTag(ToolTagT toolTagT);
}