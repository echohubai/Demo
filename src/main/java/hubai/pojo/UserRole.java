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
@ApiModel("用户角色对象")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer uid;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer rid;


}
