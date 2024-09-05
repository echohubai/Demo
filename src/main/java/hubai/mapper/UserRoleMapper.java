package hubai.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hubai.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    void deleteByUserId(Integer id);

    List<String> queryByUserId(Integer id);

    void setRole(Integer id);
}
