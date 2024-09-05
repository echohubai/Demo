package hubai.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hubai.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> queryPermissionByUserId(@Param("id") Integer id);

    List<String> queryPermission(@Param("id")Integer id);
}
