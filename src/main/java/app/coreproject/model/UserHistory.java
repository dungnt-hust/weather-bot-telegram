package app.coreproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author ductbm
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserHistory extends User {

    public UserHistory(User user) {
        BeanUtils.copyProperties(user, this);
    }

    private Long hisId;
}
