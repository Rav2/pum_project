package com.example.rafalmaselek.colorfun;


public class Result {
    String username;
    int nr, result, max_possible_result;


    public Result(int nr_, String username_, int result_, int max_possible_result_){
        nr = nr_;
        username = username_;
        result = result_;
        max_possible_result = max_possible_result_;
    }


    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMax_possible_result() {
        return max_possible_result;
    }

    public void setMax_possible_result(int max_possible_result) {
        this.max_possible_result = max_possible_result;
    }

}
