package kream.clone.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorCode {
    private final HttpStatus httpStatus;
    private final String error;
    private final String code;
}
