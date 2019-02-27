package cn.idevtools.service;


import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import cn.idevtools.service.impl.UserServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 管理员用户管理模块的接口.
 * 具体实现见
 * @see UserServiceImpl
 *
 * @author 王沁宽
 */
@Service
public interface UserService {

    /**
     * 使用自己定义的存储过程分页,查询用户基本信息.
     *
     * @param pageParams pageParams的key分别为<br/>
     *               pageNum:页面号;<br/>
     *               pageSize:每页显示量;<br/>
     *               total,用户信息总数.<br/>
     *               在使用时，只需要指定pageId与pageLimit，而total则会由存储过程自动写入
     *
     * @return 具体页的用户信息
     */
    @Deprecated
    List<UserT> getAllUserPage(Map<String,Object> pageParams);

    /**
     * 使用mybatis插件:pageHelper 进行分页，查询用户基本信息.
     * @param pageNum 页号
     * @param pageSize 每页显示量
     *
     * @return 用PageInfo包装后的查询结果，查询结果可以通过getList获得。
     */
    PageInfo<UserT> getAllUserPage(int pageNum, int pageSize);

    /**
     * 删除用户，但并不做实际的删除，只是将用户的valid字段设置为0表示该用户不再可用，
     * 用于防止用户名的重复
     *
     * @param userId 待删除的用户id
     * @return 操作影响的行数，由于用户id为主键，唯一对应一个用户，因此操作成功时，返回1
     */
    int deleteUser(Integer userId);

    /**
     * 修改用户信息
     *
     * @param user 待修改的用户对象
     * @return 操作影响的行数，由于用户id为主键，唯一对应一个用户，因此操作成功时，返回1
     */
    int updateUser(UserT user);
    /**
     * 新建用户
     *
     * @param user 待新建的用户对象
     * @return 操作影响的行数，由于用户id为主键，唯一对应一个用户，因此操作成功时，返回1
     */
    int createUser(UserT user);

    /**
     * 分页查询用户信息
     *
     * @param user 待查询的用户对象
     *
     * @return 符合查询条件的用户list
     */
    PageInfo<UserT> getUsersPage(UserT user, int pageNum, int pageSize);
    /**
     * 根据用户id查询包含了标签信息的单个用户的详细信息
     *
     * @param userId 用户id
     */
    UserTagVO getUserDetailWithTagById(Integer userId);
    /**
     * 根据用户id与标签id为相应的用户添加标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:添加失败 1:添加成功
     */
    int addTagForUser(Integer userId,Integer tagId);
    /**
     * 根据用户id与标签id为对应用户删除标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:失败 1:成功
     */
    int removeTagForUser(Integer userId,Integer tagId);

    /**
     * 根据userName判断用户是否存在 southday 2019.02.26
     * @param userName
     * @return
     */
    boolean isUserExists(String userName);
}
