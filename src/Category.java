import java.util.ArrayList;
import java.util.List;

public class Category implements InventoryComponent{
    private static int categoryCounter = 1;
    private String categoryName;
    private String categoryId;
    private List<InventoryComponent> components = new ArrayList<>();

    // Constructor
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.categoryId = String.format("%03d", categoryCounter++);

    }
    // Constructor
    public Category(String categoryId, String categoryName) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }


    @Override
    public void display() {
        System.out.println("Category: " + categoryName + " (ID: " + categoryId + ")\n" );
        for (InventoryComponent component : components) {
            component.display();
        }
    }

    @Override
    public String getId() {
        return categoryId;
    }

    public void add(InventoryComponent subcategory) {
        components.add(subcategory);

    }

    public List<InventoryComponent> getComponents() {
        return components;
    }

    @Override
    public InventoryComponent getCategory() {
        // Not applicable for product
        throw new UnsupportedOperationException("Cannot edit a Product directly.");
    }

    @Override
    public String getName() {
        return categoryName;
    }

    @Override
    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean remove(InventoryComponent category) {
        if (category.getComponents().isEmpty()) {
            category = null;
            return true;
        }
        System.out.println(category.getName() + " category can not be deleted. It has subcategories associated to it.");
        return false;
    }

    @Override
    public boolean edit(String categoryName) {

        // change category name in all subcategories under itself
        for (InventoryComponent inventoryComponent : this.components) {
            inventoryComponent.getCategory().setName(categoryName);
        }
        this.categoryName = categoryName;
        return true;

    }

}
