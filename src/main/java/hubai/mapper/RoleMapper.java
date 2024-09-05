package hubai.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hubai.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleByLikeName(String name);

    Role getRoleByEqualName(String name);
}
