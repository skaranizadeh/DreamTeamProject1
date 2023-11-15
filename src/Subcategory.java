import java.util.ArrayList;
import java.util.List;

public class Subcategory implements InventoryComponent {

    private static int subcategoryCounter = 1;
    private String  subcategoryName;
    private String subcategoryId;
    private List<InventoryComponent> components = new ArrayList<>();
    private InventoryComponent category;

    // Constructor
    public Subcategory(String subcategoryName, InventoryComponent category) {
        this.subcategoryName = subcategoryName;
        this.subcategoryId = category.getId() + String.format("%03d", subcategoryCounter++);
        this.category = category;
        category.getComponents().add(this);
    }

    public static int getSubcategoryCounter() {
        return subcategoryCounter;
    }

    public static void setSubcategoryCounter(int subcategoryCounter) {
        Subcategory.subcategoryCounter = subcategoryCounter;
    }

    @Override
    public String getName() {
        return subcategoryName;
    }

    @Override
    public void setName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    @Override
    public boolean remove(InventoryComponent subcategory) {
        if (subcategory.getComponents().isEmpty()) {
            this.category.getComponents().remove(subcategory);
            subcategory = null;
            return true;
        }
        System.out.println(subcategory.getName() + " subcategories can not be deleted. It has products associated to it.");
        return false;
    }

    @Override
    public boolean edit(String subcategoryName) {
        // change category name in all subcategories under itself
        for (InventoryComponent inventoryComponent : this.components) {
            inventoryComponent.setName(subcategoryName);
        }
        this.subcategoryName = subcategoryName;
        return true;
    }



    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public void setComponents(List<InventoryComponent> components) {
        this.components = components;
    }

    public InventoryComponent getCategory() {
        return category;
    }

    public void setCategory(InventoryComponent category) {
        this.category = category;
    }

    @Override
    public void display() {
        System.out.println("Subcategory: " + subcategoryName + " (ID: " + subcategoryId + ")\n");
        for (InventoryComponent component : components) {
            component.display();
        }
    }

    @Override
    public String getId() {
        return subcategoryId;
    }

    @Override
    public List<InventoryComponent> getComponents() {
        return components;
    }

    public void add(InventoryComponent product) {
        components.add(product);
     }
}
