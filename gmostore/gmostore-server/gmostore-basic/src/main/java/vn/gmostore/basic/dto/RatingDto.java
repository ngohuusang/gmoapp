package vn.gmostore.basic.dto;

public class RatingDto implements Dto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private int point;
    private Long ratedDate;
    private int mark;
    private ProductDto product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Long getRatedDate() {
        return ratedDate;
    }

    public void setRatedDate(Long ratedDate) {
        this.ratedDate = ratedDate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
