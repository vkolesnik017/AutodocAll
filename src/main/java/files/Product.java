package files;

public class Product {
    private String brandOfProduct;
    private String genericOfProduct;
    private double priceOfProduct;

    public void setBrandOfProduct(String brand) {
        brandOfProduct = brand;
    }

    public String getBrandOfProduct() {
        return brandOfProduct;
    }

    public void setGenericOfProduct(String generic) {
        genericOfProduct = generic;
    }

    public String getGenericOfProduct() {
        return genericOfProduct;
    }

    public void setPriceOfProduct(double price) {
        priceOfProduct = price;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brandOfProduct='" + brandOfProduct + '\'' +
                ", genericOfProduct='" + genericOfProduct + '\'' +
                ", priceOfProduct=" + priceOfProduct +
                '}' + "\n";
    }

}
