package com.argon.helpers;

public interface ResponseText {

    interface GENERIC {
        String DEFAUT_ERROR_MESSAGE = "Sorry, something went wrong !";
    }

    interface SIGNUP {
        String EMAIL_EXISTS = "e-mail id already exists";
    }

}
