package hubai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hubai.pojo.User;
import hubai.utils.RespBean;

public interface IUserService extends IService<User> {

    /**
     * 用户注册
     * @param user 用户信息
     */
    RespBean register(User user);

    /**
     * 用户登录
     * @param user 登录用户
     * @return 登录结果
     */
    RespBean login(User user);

    /**
     * 退出
     * @return 结果
     */
    RespBean logout();

    void updateRoleById(Integer id, String[] roles);

    RespBean queryAll(String username,Integer currentPage);

    RespBean updatePassword(String username, String lastPassword, String newPassword);
}
