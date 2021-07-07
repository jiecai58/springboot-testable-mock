package com.spring.testable.mock.common.enums;

/**
 *  Abnormal error code
 * @author caijie
 */
public enum ErrorCode {

    SUCCESS(0L, "success"),
    ERROR_SYSTEM(1L, "system error"),
    ERROR_INTERNAL(1001L, "internal error"),
    ERROR_MISS_PARAM(1002L, "miss param"),
    ERROR_MISS_DB(1003L, "db error"),
    ERROR_RPC_REQUEST(1004L, "rpc request error"),
    ERROR_ID_ALLOC(1005L, "id alloc error"),
    ERROR_SYSTEM_CLOSE(1006L, "system close"),
    ERROR_SYSTEM_CONFIG_MISSING(1007L, "some system config never add"),
    ERROR_MISS_IO(1008L, "io error"),
    ERROR_REQ_LIMIT(1009L, "req count limit"),
    ERROR_ADDRESS_QUERY(1010L, "query address error "),
    ERROR_SIGN_BLANK(1011L,"sign is empty"),
    ERROR_TOO_OFTEN(1012L,"too often"),
    ERROR_SIGN_ERROR(1013L,"sign is error"),
    ERROR_REQUEST_EXPIRED(1014L,"request expired"),

    //user
    ERROR_USER_DOES_NOT_EXIT(2001L,"user does not exist"),
    ERROR_USER_FROZEN(2002L,"account has been frozen"),
    ERROR_USER_IDCARD_NOT_VERIFY(2003L,"identity is not authenticated"),
    ERROR_USER_IDCARD_VERIFYED(2004L,"identity is authenticated"),
    ERROR_USER_IDCARD_VERIFYED_FAIL(2005L,"identity authentication failed"),
    ERROR_USER_IDCARD_BIND_OTHER_ACCOUNT(2006L,"ID card binding to other bts account"),
    ERROR_USER_CHANGE_SAME_PHONE(2007L,"The user replaces the same mobile number"),
    ERROR_USER_SMS_TYPE_FAILED(2008L,"failed to get SMS template"),

    //merchant
    ERROR_MERCHANT_NOT_ACTIVED(3001L, "merchant not active"),
    ERROR_MERCHANT_NOT_EXIST(3002L, "merchant not exist"),
    ERROR_BTS_ACCOUNT_IS_MERCHANT(3003L, "bts account is merchant"),
    ERROR_BAK_BTS_ACCOUNT_IS_MERCHANT(3004L, "bak bts account is merchant"),
    ERROR_NICKNAME_ALREADY_EXIST(3005L, "nickname already exist"),
    ERROR_INSUFFICIENT_CONFITIONS(3006L, "does not meet the application conditions"),
    ERROR_UNAPPLIED_MERCHANT(3007L, "unapplied merchant"),
    ERROR_INSUFFICIENT_MARGIN(3008L, "insufficient margin")
    ;
    private Long code;
    private String message;

    ErrorCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
