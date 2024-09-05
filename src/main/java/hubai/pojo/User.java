package hubai.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import hubai.validator.IsEmail;
import hubai.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户对象")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotNull
    @IsEmail
    private String email;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    @NotNull
    @IsMobile
    private String phone;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;
}
