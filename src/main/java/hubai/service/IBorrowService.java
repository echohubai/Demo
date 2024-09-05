package hubai.service;


import com.baomidou.mybatisplus.extension.service.IService;
import hubai.pojo.Borrow;
import hubai.utils.RespBean;

public interface IBorrowService extends IService<Borrow> {

    RespBean requestBorrow(Integer id);

    RespBean queryByCondition(String username, String bookName,String author, Integer current);

    RespBean add(Borrow borrow);

    RespBean requestRepaid(Integer id);

    RespBean cancel(Integer id);

    RespBean agree(Integer id, Integer station);
}
