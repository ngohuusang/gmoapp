package vn.gmostore.basic.dispatch;

import vn.gmostore.basic.dto.Dto;

import com.gwtplatform.dispatch.shared.Result;

public class GetResult<T extends Dto> implements Result {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    int status = 200;//HttpStatus.OK

    T result;

    protected GetResult() {
    }

    public GetResult(T result) {
        this.result = result;
    }

    public GetResult(T result, int statusCode) {
        this.result = result;
        this.status = statusCode;
    }

    public T getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }
}
