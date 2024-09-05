package hubai.service;


import com.baomidou.mybatisplus.extension.service.IService;
import hubai.pojo.Book;
import hubai.utils.RespBean;

public interface IBookService extends IService<Book> {

    void add(Book book);

    RespBean queryByCondition(String bookName, String author, Long current);
}
