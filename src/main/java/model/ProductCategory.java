package model;

public class ProductCategory {
    private Product product;
    private String nameCategory;

    public ProductCategory(Product product, String nameCategory) {
        this.product = product;
        this.nameCategory = nameCategory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
