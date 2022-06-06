package app.coreproject.payload.response;

import app.coreproject.payload.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ductbm
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Response {

    public ResponseMeta meta;
    public Object data;
}
