package isp.models;

public class Response {
    public enum Type{OK, FAILED}

    public Type type;
    public String message;

    public Response(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
