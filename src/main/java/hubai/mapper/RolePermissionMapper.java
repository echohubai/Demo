package hubai.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hubai.pojo.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void deleteByRoleId(@Param("id") Integer id);

    List<String> queryByRoleId(@Param("id") Integer id);

}
