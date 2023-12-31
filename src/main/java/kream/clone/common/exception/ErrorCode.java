package kream.clone.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "JWT_001", "Access Token이 유효하지 않습니다."),
    EXPIRATION_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "JWT_002", "Access Token이 만료되었습니다"),
    ACCESS_TOKEN_NOT_SUPPORT(HttpStatus.UNAUTHORIZED, "JWT_003", "지원하지 않는 Access Token입니다"),
    UNKNOWN_ACCESS_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "JWT_004", "Access Token 에러입니다"),
    UNKNOWN_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "JWT_005", "알 수 없는 토큰 에러입니다"),

    NOT_AUTHORIZED_MEMBER(HttpStatus.BAD_REQUEST, "MEMBER_001", "인가되지 않은 사용자입니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "MEMBER_002", "찾을 수 없는 회원입니다."),
    NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER_003", "비밀번호를 다시 확인해주세요."),
    ALREADY_EXIST_USERNAME(HttpStatus.BAD_REQUEST, "MEMBER_004", "이미 존재하는 회원 아이디입니다."),

    NOT_FOUND_BRAND(HttpStatus.NOT_FOUND, "BRAND_002", "찾을 수 없는 브랜드입니다."),
    ALREADY_EXIST_BRANDNAME(HttpStatus.CONFLICT, "BRAND_004", "이미 존재하는 브랜드 이름입니다."),

    DUPLICATED_PRODUCT(HttpStatus.CONFLICT, "PRODUCT_001", "이미 존재하는 상품입니다."),
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "PRODUCT_002", "찾을 수 없는 상품입니다.")
    ;
    private final HttpStatus httpStatus;
    private final String error;
    private final String code;
}
