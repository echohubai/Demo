package hubai.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("用户角色实体")
public class UserRoleVo {

    /**
     * 主键id
     */
    @TableId
    @ApiModelProperty("用户id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("角色")
    List<String> roles;
}

