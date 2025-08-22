package Exception;


public class ExceptionColumnDuplicated extends RuntimeException {

    private String columnDulicated;
    public ExceptionColumnDuplicated(String message) {

        super(message);

    }

    public ExceptionColumnDuplicated(String message, String columnDulicated){
        super(message);
        this.columnDulicated = columnDulicated;
    }
}
