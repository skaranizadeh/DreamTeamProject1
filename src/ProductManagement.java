/*
   Dream team project 1
   code version 2.0 all view parts tested
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManagement {
    private HashMap<String, Category> categories = new HashMap<>();
    private HashMap<String, Subcategory> subcategories = new HashMap<>();
    private HashMap<String, Product> products = new HashMap<>();

    // Method to read data from the category CSV file
    private void readCategoryData(String csvFile, String csvSplitBy) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip the first line

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                String categoryId = data[0];
                String categoryName = data[1];

                // Create Category instance and put it in the respective HashMap
                Category category;

                // Check to make sure the category does not exist in the HashMap, then add it
                if (!categories.containsKey(categoryId)) {
                    category = new Category(categoryId, categoryName);
                    categories.put(categoryId, category);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Category> getCategories() {
        return categories;
    }

    // Method to read data from the subcategory CSV file
    private void readSubcategoryData(String csvFile, String csvSplitBy) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip the first line

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                String categoryId = data[0].substring(0, 3);
                String subcategoryId = data[0];
                String subcategoryName = data[1];

                // Get the category object above the subcategory from the category HashMap
                InventoryComponent category = categories.get(categoryId);

                // Create Subcategory instance
                Subcategory subcategory;

                // Check to make sure the subcategory does not exist in the HashMap, then add it
                if (!subcategories.containsKey(subcategoryId)) {
                    subcategory = new Subcategory(subcategoryId, subcategoryName, category);
                    subcategories.put(subcategoryId, subcategory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read data from the product CSV file
    private void readProductData(String csvFile, String csvSplitBy) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip the first line

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                String subcategoryId = data[0].substring(0, 6);
                String productId = data[0];
                String productName = data[1];
                String description = data[2];
                double purchasePrice = Double.parseDouble(data[3]);
                String purchaseDate = data[4];

                // Get the subcategory object to which this product belongs from the subcategory HashMap
                InventoryComponent subcategory = subcategories.get(subcategoryId);


                // Create Product instance
                Product product;

                 //Check to make sure the product does not exist in the HashMap, then add it
                if (!products.containsKey(productId)) {
                    product = new Product(productId, productName, description, purchasePrice, purchaseDate, subcategory);
                    products.put(productId, product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to initialize data from CSV files
    private void initializeData() {
        String categoryFile = "categoryData.csv";
        String subcategoryFile = "subcategoryData.csv";
        String productFile = "productData.csv";
        String csvSplitBy = ",";

        // Read data from the category CSV file
        readCategoryData(categoryFile, csvSplitBy);

        // Read data from the subcategory CSV file
        readSubcategoryData(subcategoryFile, csvSplitBy);

        // Read data from the product CSV file
        readProductData(productFile, csvSplitBy);
    }



    private void menu() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = false;
        boolean isCategoriesMenu = true;
        Category selectedCategory = null;
        Subcategory selectedSubcategory;

        while (!exit) {
            if (isCategoriesMenu) {
                System.out.println("*********** List of Product Categories **********");
                System.out.println("Select a number from the list to see subcategories");
                int i = 1;
                for (InventoryComponent category : categories.values()) {
                    System.out.println(i++ + "- " + category.getName());
                }
                System.out.print("Enter your category number (q to quit): ");
                input = sc.nextLine();

                if (input.equalsIgnoreCase("q")) {
                    exit = true;
                    break;
                }

                int categoryIndex = Integer.parseInt(input) - 1;

                if (categoryIndex >= 0 && categoryIndex < categories.size()) {
                    selectedCategory = (Category) categories.values().toArray()[categoryIndex];
                    isCategoriesMenu = false;
                } else {
                    System.out.println("Invalid input. Please select a number from the list.");
                }
            }

            if (!isCategoriesMenu) {
                if (selectedCategory != null) {
                    System.out.println("*********** List of Subcategories **********");
                    System.out.println("Select a number from the list to see the items");
                    int j = 1;
                    for (InventoryComponent subcategory : subcategories.values()) {
                        if (subcategory.getCategory().equals(selectedCategory)) {
                            System.out.println(j++ + "- " + subcategory.getName());
                        }
                    }
                    System.out.print("Enter your subcategory number (p to go back, q to quit): ");
                    input = sc.nextLine();

                    if (input.equalsIgnoreCase("q")) {
                        exit = true;
                        break;
                    } else if (input.equalsIgnoreCase("p")) {
                        isCategoriesMenu = true;
                    } else {
                        int subcategoryIndex = Integer.parseInt(input) - 1;
                        int currentSubcategory = 0;

                        for (InventoryComponent subcategory : subcategories.values()) {
                            if (subcategory.getCategory().equals(selectedCategory)) {
                                if (currentSubcategory == subcategoryIndex) {
                                    selectedSubcategory = (Subcategory) subcategory;

                                    while (true) {
                                        System.out.println("*********** List of Products **********");
                                        int k = 1;
                                        for (InventoryComponent product : products.values()) {
                                            if (product.getSubcategory().equals(selectedSubcategory)) {
                                                System.out.println(k++ + "- " + product.getName());
                                            }
                                        }
                                        System.out.print("Enter your item number (p to go back, q to quit): ");
                                        input = sc.nextLine();

                                        if (input.equalsIgnoreCase("q")) {
                                            exit = true;
                                            break;
                                        } else if (input.equalsIgnoreCase("p")) {
                                            break;
                                        } else {
                                            int productIndex = Integer.parseInt(input) - 1;
                                            int currentProduct = 0;

                                            for (InventoryComponent product : products.values()) {
                                                if (product.getSubcategory().equals(selectedSubcategory)) {
                                                    if (currentProduct == productIndex) {
                                                        currentProduct++;
                                                        System.out.println("You've selected: " + product.getName());
                                                        System.out.println("Product Info:");
                                                        System.out.println("Description: " + product.getDescription());
                                                        System.out.println("Price: " + product.getPurchasePrice());
                                                        System.out.println("Purchase Date: " + product.getPurchaseDate());
                                                        break;
                                                    }
                                                    currentProduct++;
                                                }
                                            }
                                        }
                                    }

                                    isCategoriesMenu = false;
                                    break;
                                }
                                currentSubcategory++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Exiting the Product Management System. Goodbye!");
        sc.close();
    }




    public static void main(String[] args)  {
        //System.out.println("Current Directory: " + System.getProperty("user.dir"));
        ProductManagement productManagement = new ProductManagement();
        productManagement.initializeData();

        productManagement.menu();

    }



}
