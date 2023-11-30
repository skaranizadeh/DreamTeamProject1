package backend;

import java.util.List;

public class Product implements InventoryComponent{

    private static int productCounter = 1;
    private String productId;
    private String productName;
    private String description;
    private double purchasePrice;
    private String purchaseDate;
    //private InventoryComponent subcategory;

    // Constructors
    public Product(String productName, InventoryComponent subcategory) {
        this.productName = productName;
        this.productId = subcategory.getId() + String.format("%03d", productCounter++);
        //this.subcategory = subcategory;
        subcategory.getComponents().add(this);
    }

    public Product(String productName, String description, double purchasePrice, String purchaseDate, InventoryComponent subcategory) {
        this(productName, subcategory);
        this.productName = productName;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;

    }

    public Product(String productId, String productName, String description, double purchasePrice, String purchaseDate, InventoryComponent subcategory) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        //this.productId = subcategory.getId() + String.format("%03d", productCounter++);
        //this.subcategory = subcategory;
        subcategory.getComponents().add(this);
    }

//    public InventoryComponent getSubcategory() {
//        return subcategory;
//    }

//    public void setSubcategory(InventoryComponent subcategory) {
//        this.subcategory = subcategory;
//    }

    public static int getProductCounter() {
        return productCounter;
    }

    public static void setProductCounter(int productCounter) {
        Product.productCounter = productCounter;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return productName;
    }

    @Override
    public void setName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean remove(InventoryComponent product) {
        //this.subcategory.getComponents().remove(product);
        product = null;
        return true;
    }

    @Override
    public boolean edit(String productName) {
        this.productName = productName;
        return true;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public void display() {
        System.out.println("********* Product Details *************");
//        System.out.println("Category: "+ subcategory.getCategory().getName() +"\nSubcategory: " + subcategory.getName() + "\nProduct: " + productName + " (ID: " + productId + ")"
//        +"\nDescription: " + description + "\nPurchasePrice: " + purchasePrice
//        +"\nPurchaseDate: " + purchaseDate);
        System.out.println("Product: " + productName + " (ID: " + productId + ")"
                +"\nDescription: " + description + "\nPurchasePrice: " + purchasePrice
                +"\nPurchaseDate: " + purchaseDate);
        System.out.println();

    }

    @Override
    public String getId() {
        return productId;
    }


    private static void setCounterId(int maxId) {
        productCounter = maxId + 1;
    }

    @Override
    public List<InventoryComponent> getComponents() {

        // Not applicable for product
        throw new UnsupportedOperationException("Cannot edit a Product directly.");
    }

    public static Product addProduct(String productName, String description, double purchasePrice, String purchaseDate, InventoryComponent subcategory, int maxId) {
        setCounterId(maxId);
        return new Product(productName, description, purchasePrice, purchaseDate, subcategory);
    }

//    @Override
//    public InventoryComponent getCategory() {
//        // Not applicable for product
//        throw new UnsupportedOperationException("Cannot edit a Product directly.");
//    }
}
