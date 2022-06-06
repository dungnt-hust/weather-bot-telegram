package app.coreproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author toduc
 *
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Integer id;
    private String username;
    private String password;
    private String permission;
    private String email;
    private String phone;
    private String name;
    private String twitterUsername;
    private String telegramUsername;
    private Map<String, Object> extraData;
    private Integer version;
    private Integer status;
    private Date createdTime;
    private Date updatedTime;

}
