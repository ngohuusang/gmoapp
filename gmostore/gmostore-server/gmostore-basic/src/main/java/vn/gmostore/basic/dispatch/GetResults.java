package vn.gmostore.basic.dispatch;

import java.util.List;

import vn.gmostore.basic.dto.Dto;

import com.gwtplatform.dispatch.shared.Result;

public class GetResults<T extends Dto> implements Result {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    int status = 200;//HttpStatus.OK

    List<T> results;

    protected GetResults() {
    }

    public GetResults(List<T> results) {
        this.results = results;
    }

    public GetResults(List<T> results, int statusCode) {
        this.results = results;
        this.status = statusCode;
    }

    public List<T> getResults() {
        return results;
    }

    public int getStatus() {
        return status;
    }
}
