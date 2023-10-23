package kream.clone.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KreamException extends RuntimeException{

    private final ErrorCode errorCode;
}
