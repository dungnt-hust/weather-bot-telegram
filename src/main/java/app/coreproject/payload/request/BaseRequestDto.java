package app.coreproject.payload.request;

import lombok.Data;

/**
 * @author ductbm
 */

@Data
public class BaseRequestDto {

    private int skip = 0;
    private int take = 15;
    private int page = 1;
    private int pageSize = 15;
}
