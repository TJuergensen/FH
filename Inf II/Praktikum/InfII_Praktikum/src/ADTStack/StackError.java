package ADTStack;

public class StackError extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String message;

    public StackError(String m) {
	message = m;
    }
}
