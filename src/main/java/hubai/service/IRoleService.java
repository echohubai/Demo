package hubai.service;


import com.baomidou.mybatisplus.extension.service.IService;
import hubai.pojo.Role;
import hubai.utils.RespBean;

public interface IRoleService extends IService<Role> {

    void add(Role role, String[] permissions);

    void delete(Integer[] ids);

    void updateRole(Role role, String[] permissions);

    RespBean queryByName(String name, Integer currentPage);
}
