package eai.devass.sinistreat.appli.restClient;

import java.util.List;

public class ApiResponse {
    private int statusCode;
    private String message;
    private List<String> errors;

    public ApiResponse(int statusCode, String message, List<String> errors) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
