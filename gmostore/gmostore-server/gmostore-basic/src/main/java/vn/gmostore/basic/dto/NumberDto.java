package vn.gmostore.basic.dto;

public class NumberDto<T extends Number> implements Dto {

    private T number;

    protected NumberDto() {
    }

    public NumberDto(T number) {
        this.number = number;
    }

    public T getNumber() {
        return number;
    }

    public void setNumber(T number) {
        this.number = number;
    }
}
