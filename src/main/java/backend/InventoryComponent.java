package backend;

import java.util.List;

public interface InventoryComponent {
    void display();
    String getId();
    public void setCounterId(String maxId);
    String getName();
    List<InventoryComponent> getComponents();
    //InventoryComponent getCategory();
    void setName(String componentName);
    boolean remove(InventoryComponent component);
    boolean edit(String componentName);
    //InventoryComponent getSubcategory();
    String getDescription();
    double getPurchasePrice();
    String getPurchaseDate();
}
