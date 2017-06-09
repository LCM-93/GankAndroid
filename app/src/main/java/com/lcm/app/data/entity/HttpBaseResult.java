package com.lcm.app.data.entity;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午5:48
 * Desc:
 * *****************************************************************
 */
public class HttpBaseResult<T> {

    private boolean error;
    private T results;

    @Override
    public String toString() {
        return "HttpBaseResult{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
