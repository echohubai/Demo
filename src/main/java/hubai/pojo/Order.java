package hubai.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`order`")
@ApiModel("订单")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("订单id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 订单名
     */
    @ApiModelProperty("书名")
    private String bookName;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer num;

    /**
     * 出版社
     */
    @ApiModelProperty("出版社")
    private String press;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String content;

    /**
     * 发起时间
     */
    @ApiModelProperty("发起时间")
    private String launchTime;

    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    private Integer station;

}
