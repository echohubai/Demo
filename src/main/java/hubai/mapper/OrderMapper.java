package hubai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hubai.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void agree(@Param("id") Integer id);

    void cancel(@Param("id") Integer id);
}
