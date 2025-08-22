package Exception;

import lombok.Getter;

public class ExceptionColumnDuplicated extends RuntimeException {

    @Getter
    private String columnDulicated;
    public ExceptionColumnDuplicated(String message) {

        super(message);

    }

    public ExceptionColumnDuplicated(String message, String columnDulicated){
        super(message);
        this.columnDulicated = columnDulicated;
    }
}
