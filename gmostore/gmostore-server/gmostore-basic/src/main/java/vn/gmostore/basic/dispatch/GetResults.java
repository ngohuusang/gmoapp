package vn.gmostore.basic.dispatch;

import java.util.List;

import vn.gmostore.basic.dto.Dto;

import com.gwtplatform.dispatch.shared.Result;

public class GetResults<T extends Dto> implements Result {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    List<T> results;

    protected GetResults() {
    }

    public GetResults(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }
}
