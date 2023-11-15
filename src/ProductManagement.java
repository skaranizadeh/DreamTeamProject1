import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ProductManagement {
    HashMap<String, Category> categories = new HashMap<>();
    HashMap<String, Subcategory> subcategories = new HashMap<>();
    HashMap<String, Product> products = new HashMap<>();

    private void initializeDate() {
        String csvFile = "your_file.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();// skip the first line

            while ((line = br.readLine()) != null){
                String[] data = line.split(csvSplitBy);

                // Extracting data from CSV file
                String categoryID = data[0];
                String categoryName = data[1];
                String subcategoryID = data[2];
                String subcategoryName = data[3];
                String productID = data[4];
                String productName = data[5];
                String description = data[6];
                double price = Double.parseDouble(data[7]);
                String purchaseDate = data[8];

                // Create Category, Subcategory, and Product instances and put them in the respective maps
                Category category;
                Subcategory subcategory;
                Product product;

                if (!categories.containsKey(categoryID)) {
                    category = new Category(categoryID, categoryName);
                    categories.put(categoryID, category);
                } else {
                    category = categories.get(categoryID);
                }

                if (!subcategories.containsKey(subcategoryID)) {
                    subcategory = new Subcategory(subcategoryID, subcategoryName, category);
                    subcategories.put(subcategoryID, subcategory);
                } else {
                    subcategory = subcategories.get(subcategoryID);
                }


                product = new Product(productID, productName, description, price, purchaseDate, category, subcategory);
                products.put(productID, product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                System.out.println("*********** List of product Categories **********");
                System.out.println("Select a number from the list to see subcategories");
                int i = 1;
                for (Category category : categories.values()) {
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
                    System.out.println("*********** List of subcategories **********");
                    System.out.println("Select a number from the list to see the items");
                    int j = 1;
                    for (Subcategory subcategory : subcategories.values()) {
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

                        for (Subcategory subcategory : subcategories.values()) {
                            if (subcategory.getCategory().equals(selectedCategory)) {
                                if (currentSubcategory == subcategoryIndex) {
                                    selectedSubcategory = subcategory;
                                    System.out.println("*********** List of items **********");
                                    int k = 1;
                                    for (Product product : products.values()) {
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
                                        isCategoriesMenu = false;
                                    } else {
                                        int productIndex = Integer.parseInt(input) - 1;
                                        int currentProduct = 0;

                                        for (Product product : products.values()) {
                                            if (product.getSubcategory().equals(selectedSubcategory)) {
                                                if (currentProduct == productIndex) {
                                                    currentProduct++;
                                                    System.out.println("You've selected: " + product.getName());
                                                    System.out.println("Product Info:");
                                                    System.out.println("Description: " + product.getDescription());
                                                    System.out.println("Price: " + product.getPurchasePrice());
                                                    System.out.println("Purchase Date: " + product.getPurchaseDate());
                                                    isCategoriesMenu = false;
                                                    break;
                                                }
                                                currentProduct++;
                                            }
                                        }
                                    }
                                }
                                currentSubcategory++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Exiting the Product Manager. Goodbye!");
        sc.close();
    }



    public static void main(String[] args)  {
        //System.out.println("Current Directory: " + System.getProperty("user.dir"));
        ProductManagement productManagement = new ProductManagement();
        productManagement.initializeDate();

        productManagement.menu();

    }
}
