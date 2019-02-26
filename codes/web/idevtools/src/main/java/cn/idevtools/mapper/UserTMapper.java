package cn.idevtools.mapper;

import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserTMapper {
    /**
     * 使用存储过程proc_select_user_page分页查询用户基本信息
     *
     * @param pageParams pageParams的key分别为<br>
     *               pageNum:页面号;<br>
     *               pageSize:每页显示量;<br>
     *               total,用户信息总数<br>
     *               在使用时，只需要指定pageId与pageLimit，而total则会由存储过程自动写入
     *
     * @return 具体页的用户信息
     */
    @Deprecated
    List<UserT> selectUserPage(Map<String,Object> pageParams);


    /**
     * 根据用户id将其valid设置为0，即不可用状态，表示已经被删除
     *
     * @param user 待修改用户
     *
     * @return 成功操作的个数，由于用户id为主键，因此成功返回1
     */
    int updateUserValidById(@Param("user") UserT user);

    /**
     * 查询全部用户信息
     */
    List<UserT> selectAllUser();
    /**
     * 条件查询用户信息
     */
    List<UserT> selectUsers(UserT user);

    /**
     * 根据userId更新用户基本信息
     * @param user 待修改的用户数据，userId与userName不能修改
     * @return 操作影响的行数
     */
    int updateUser(UserT user);
    /**
     * 根据userId查询附带标签信息的用户详细信息，包含用户的全部字段以及用户对应标签的全部字段
     * @param userId 用户id
     */
    UserTagVO selectUserDetailWithTagById(Integer userId);

    /**
     * 根据userName查询用户信息 southday 2019.02.26
     * @param userName
     * @return
     */
    UserT selectUserByUserName(String userName);
}