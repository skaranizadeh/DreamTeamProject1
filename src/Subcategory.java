import java.util.ArrayList;
import java.util.List;

public class Subcategory implements InventoryComponent {

    private static int subcategoryCounter = 1;
    private String subcategoryId;
    private String  subcategoryName;
    private List<InventoryComponent> products = new ArrayList<>();
    private InventoryComponent category;

    // Constructors
    public Subcategory(String subcategoryName, InventoryComponent category) {
        this.subcategoryName = subcategoryName;
        this.subcategoryId = category.getId() + String.format("%03d", subcategoryCounter++);
        this.category = category;
        category.getComponents().add(this);
    }

    public Subcategory(String subcategoryId, String subcategoryName, InventoryComponent category) {
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
        this.category = category;
        category.getComponents().add(this);
        subcategoryCounter++;
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
        for (InventoryComponent inventoryComponent : this.products) {
            inventoryComponent.setName(subcategoryName);
        }
        this.subcategoryName = subcategoryName;
        return true;
    }

    @Override
    public InventoryComponent getSubcategory() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public double getPurchasePrice() {
        return 0;
    }

    @Override
    public String getPurchaseDate() {
        return null;
    }


    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public void setComponents(List<InventoryComponent> components) {
        this.products = components;
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
        for (InventoryComponent component : products) {
            component.display();
        }
    }

    @Override
    public String getId() {
        return subcategoryId;
    }

    @Override
    public List<InventoryComponent> getComponents() {
        return products;
    }

    public void add(InventoryComponent product) {
        products.add(product);
     }
}
