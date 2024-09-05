package hubai.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色权限对象")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer rid;

    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    private Integer pid;

}
