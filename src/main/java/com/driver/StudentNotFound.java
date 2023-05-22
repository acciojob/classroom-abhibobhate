package com.driver;

public class StudentNotFound extends RuntimeException{
    public StudentNotFound(String s){
        super(s);
    }
}
