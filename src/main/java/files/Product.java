package files;

public class Product {
    private String brandOfProduct;
    private String genericOfProduct;
    private double priceOfProduct;
    private String attributeOfButton;

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

    public void setAttributeOfButton(String attribute){attributeOfButton=attribute;}

    public String getAttributeOfButton() {return attributeOfButton;}
    @Override
    public String toString() {
        return "Product{" +
                "brandOfProduct='" + brandOfProduct + '\'' +
                ", genericOfProduct='" + genericOfProduct + '\'' +
                ", priceOfProduct=" + priceOfProduct +
                '}' + "\n";
    }

}
