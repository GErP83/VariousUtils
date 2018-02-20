package com.gerp83.variousutils;

/**
 * Created by gerp83
 * this exception is thrown when no app found that can handle an intent
 */

public class IntentFailException extends Exception{

    public IntentFailException(String message) {
        super(message);
    }

}
