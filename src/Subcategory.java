public class Subcategory {
    private String subcategoryId;
    private String subcategoryName;
    private Category category; // Reference to the parent category

    public Subcategory(String subcategoryId, String subcategoryName, Category category) {
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
        this.category = category;
    }

    // Getters and Setters

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

