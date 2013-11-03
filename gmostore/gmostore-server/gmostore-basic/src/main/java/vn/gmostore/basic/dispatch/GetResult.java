package vn.gmostore.basic.dispatch;

import vn.gmostore.basic.dto.Dto;

import com.gwtplatform.dispatch.shared.Result;

public class GetResult<T extends Dto> implements Result {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    T result;

    protected GetResult() {
    }

    public GetResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
