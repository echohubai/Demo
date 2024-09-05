package hubai.service;


import com.baomidou.mybatisplus.extension.service.IService;
import hubai.pojo.Order;
import hubai.utils.RespBean;

public interface IOrderService extends IService<Order> {

    void add(Order order);

    RespBean queryByCondition(String username, String bookName, String author, Long current);

    RespBean agree(Integer id);

    RespBean cancel(Integer id);
}
