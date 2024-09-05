package hubai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hubai.mapper.PermissionMapper;
import hubai.pojo.Permission;
import hubai.service.IPermissionService;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
