package queue;

public class QueueError extends RuntimeException{
	private static final long serialVersionUID = 1L;
	String message;
	 public QueueError(String m) {message = m;}
}
