package hubai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hubai.mapper.OrderMapper;
import hubai.pojo.Order;
import hubai.utils.RespBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderMapper mockOrderMapper;

    @InjectMocks
    private OrderServiceImpl orderServiceImplUnderTest;

    @Test
    public void testAdd() {
        // Setup
        final Order order = new Order();
        order.setId(0);
        order.setUsername("username");
        order.setBookName("bookName");
        order.setAuthor("author");
        order.setNum(0);

        // Run the test
        orderServiceImplUnderTest.add(order);

        // Verify the results
        // Confirm OrderMapper.insert(...).
        final Order entity = new Order();
        entity.setId(0);
        entity.setUsername("username");
        entity.setBookName("bookName");
        entity.setAuthor("author");
        entity.setNum(0);
        verify(mockOrderMapper).insert(entity);
    }

    @Test
    public void testQueryByCondition() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");
        when(mockOrderMapper.selectPage(any(Page.class), any(QueryWrapper.class))).thenReturn(
                new Page<>(0L, 0L, 0L, false));

        // Run the test
        final RespBean result = orderServiceImplUnderTest.queryByCondition("username", "bookName", "author", 0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAgree() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Run the test
        final RespBean result = orderServiceImplUnderTest.agree(0);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockOrderMapper).agree(0);
    }

    @Test
    public void testCancel() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Run the test
        final RespBean result = orderServiceImplUnderTest.cancel(0);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockOrderMapper).cancel(0);
    }
}
