package app.coreproject.util;

public enum Response {
    SUCCESS(200),
    SERVER_ERROR(500),
    DB_ERROR(501),
    LOGIC_ERROR(503),
    USER_NOT_AUTH(401),
    PERMISSION_DENY(403),
    NOT_FOUND(404),
    LIMITED(405),
    BAD_REQUEST(400);
    private int code;

    private Response(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
