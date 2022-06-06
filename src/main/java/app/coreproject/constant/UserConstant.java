package app.coreproject.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ductbm
 */
public class UserConstant {

    @Getter
    @AllArgsConstructor
    public enum UserStatus {
        ACTIVE(1),
        INACTIVE(4),
        OTHER(999);
        private Integer value;

        private static final Map<Integer, UserStatus> mappingValue = new HashMap<>();

        static {
            for (UserStatus status : UserStatus.values()) {
                mappingValue.put(status.getValue(), status);
            }
        }


        @JsonCreator
        public static UserStatus fromValue(Integer value) {
            return mappingValue.getOrDefault(value, OTHER);
        }
    }
}
