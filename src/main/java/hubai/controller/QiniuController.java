package hubai.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;

import hubai.config.QiniuConfig;
import hubai.utils.RespBean;
import hubai.utils.RespBeanEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/qiniu")
@Api("QiniuController")
public class QiniuController {

    @Autowired
    private QiniuConfig qiniuConfig;

    @ApiOperation("获取七牛云文件上传token")
    @PostMapping("/getFileUploadToken")
    public RespBean getFileUploadToken(@RequestBody JSONObject jsonObject){
        String fileKey=jsonObject.get("fileKey").toString();
        HashMap<String, String> map = new HashMap<>();
        map.put("token",qiniuConfig.getUpToken(fileKey));
        map.put("urlPre", qiniuConfig.getUrlPre());
        return RespBean.success(map);
    }

    @ApiOperation("删除七牛云上传文件")
    @PostMapping("/deleteUploadFile")
    public RespBean deleteUploadFile(@RequestBody JSONObject jsonObject) throws QiniuException {
        String fileKey = jsonObject.get("fileKey").toString();
        boolean flag = qiniuConfig.delete(fileKey);
        return flag?RespBean.success():RespBean.error(RespBeanEnum.DELETE_FILE_ERROR);
    }
}
