
package orangetalents.com.exception;

import lombok.Getter;

@Getter
public class ContaException extends RuntimeException {

    public ContaException(String message, Throwable err) {
        super(message, err);
    }

    public ContaException(String message) {
        super(message);
    }
}

