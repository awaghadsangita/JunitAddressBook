package com.addressbook;

public class AddressBookCustomException extends Exception {
    enum ExceptionType{
        INVALID_NAME,INVALID_ADDRESS,INVALID_MOBILENUMBER,INVALID_CITY,INVALID_STATE,INVLAID_ZIP,PERSON_NOT_FOUND,ADDRESSBOOK_ALREADY_EXIST,ADDRESSBOOK_NOT_EXIST,ADDRESS_NOT_OPEN,
        DADA_NOT_SAVED,UNABLE_TO_SAVEAS
    }
    ExceptionType type;
    public AddressBookCustomException(ExceptionType type,String message) {
        super(message);
        this.type=type;
    }
}
