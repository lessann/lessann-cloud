package cn.lessann.cloud.beans.tables;

import cn.lessann.cloud.beans.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 3:22 下午
 */
@Data
@TableName("user")
public class User extends Base implements Serializable {

    private static final long serialVersionUID = -4506066734109455300L;

    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String image;
    private String code;
}
