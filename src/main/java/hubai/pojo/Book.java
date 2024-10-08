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
@ApiModel("Book对象")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 书名
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

}
