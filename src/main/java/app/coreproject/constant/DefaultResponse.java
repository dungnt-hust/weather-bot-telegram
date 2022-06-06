package app.coreproject.constant;

import app.coreproject.contract.ResponseContract;
import app.coreproject.util.Response;

public class DefaultResponse {

    public static ResponseContract<?> defaultSuccessResponse() {
        return new ResponseContract<>(Response.SUCCESS, null);
    }

    public static ResponseContract<?> defaultExceptionResponse() {
        return new ResponseContract<>(Response.SERVER_ERROR, "Some thing went wrong!");
    }
}
