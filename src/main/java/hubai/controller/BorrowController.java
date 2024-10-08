package hubai.controller;

import hubai.pojo.Borrow;
import hubai.pojo.LoginUser;
import hubai.service.IBorrowService;
import hubai.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/borrow")
@Api("BorrowController")
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    @GetMapping("/requestBorrow")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowSearch')")
    @ApiOperation("用户请求借阅")
    public RespBean requestBorrow(Integer id){
        return borrowService.requestBorrow(id);
    }

    @PostMapping("/add")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowManage')")
    @ApiOperation("管理员添加借阅")
    public RespBean add(@RequestBody Borrow borrow){
        return borrowService.add(borrow);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowManage')")
    @ApiOperation("删除借阅")
    public RespBean delete(@RequestParam("ids") Integer[] ids){
        borrowService.removeByIds(Arrays.asList(ids));
        return RespBean.success();
    }

    @PutMapping("/update")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowManage')")
    @ApiOperation("修改借阅")
    public RespBean update(@RequestBody Borrow borrow){
        borrowService.updateById(borrow);
        return RespBean.success();
    }

    @GetMapping("/query")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowManage')")
    @ApiOperation("管理员查看借阅")
    public RespBean query(String username,String bookName,String author,Integer current){
        return borrowService.queryByCondition(username,bookName,author,current);
    }

    @GetMapping("/queryWithUser")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowSearch')")
    @ApiOperation("用户查看借阅")
    public RespBean queryWithUser(String bookName,String author,Integer current){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUsername();
        return borrowService.queryByCondition(username,bookName,author,current);
    }

    @GetMapping("/requestRepaid")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowSearch')")
    @ApiOperation("用户申请归还图书")
    public RespBean requestRepaid(Integer id){
        return borrowService.requestRepaid(id);
    }

    @GetMapping("/cancel")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowSearch')")
    @ApiOperation("用户撤销操作")
    public RespBean cancel(Integer id){
        return borrowService.cancel(id);
    }

    @GetMapping("/agree")
    @PreAuthorize("@ex.hasAuthority('sys:borrow:borrowManage')")
    @ApiOperation("管理员同意操作")
    public RespBean agree(Integer id,Integer station){
        return borrowService.agree(id,station);
    }
}
