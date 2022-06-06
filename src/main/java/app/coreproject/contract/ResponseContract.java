package app.coreproject.contract;


import app.coreproject.util.Response;

public class ResponseContract<T> {
    private int code;
    private String message;
    private T response;

    public ResponseContract(int code, String message, T response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public ResponseContract(Response res, T response) {
        this.code = res.getCode();
        this.message = res.toString();
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
