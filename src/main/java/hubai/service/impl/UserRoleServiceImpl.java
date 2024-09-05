package hubai.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hubai.mapper.UserRoleMapper;
import hubai.pojo.UserRole;
import hubai.service.IUserRoleService;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
