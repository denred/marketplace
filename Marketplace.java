package com.app.marketplace;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Marketplace.java
 * ----------------
 * Marketplace application in the console menu
 * Function:
 * Enter 1 - to display list of users
 * Enter 2 - to display list of products
 * Enter 3 - the operation to buy product id by user id
 * Enter 4 - to display shopping list by user id
 * Enter 5 - to display product buyers
 * Enter 6 - operation add/delete user
 * Enter 7 - operation add/delete product
 * Enter 8 - to exit
 */
public class Marketplace {

    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<Product>();

        /* I've created the starting products */
        products.add(new Product(1, "phone", 1999.99));
        products.add(new Product(2, "charge", 22.97));
        products.add(new Product(3, "case", 15));

        ArrayList<User> users = new ArrayList<User>();

        /* I've created the starting users */
        users.add(new User(1, "John", "Smith", 1500));
        users.add(new User(2, "Ivan", "Sokol", 2500));
        users.add(new User(3, "Jack", "Smith", 500));


        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1-USERS | 2-PRODUCTS | 3-BUY | 4-SHOPPING CART | 5-PRODUCT BUYERS | 6-USER | 7-PRODUCT | 8-EXIT");
            System.out.print("Enter number of operation: ");
            String operation = input.next();


            switch (operation) {
                case ("1"):
                    listUsers(users);
                    break;
                case ("2"):
                    listProducts(products);
                    break;
                case ("3"): //buy operation
                    transaction(users, products, input);
                    break;
                case ("4"): // prints user products by id
                    printUserProducts(input, users);
                    break;
                case ("5"): // prints product buyers by id
                    printProductsBuyer(input, products);
                    break;
                case ("6"): // operations with users: add new user (ADD) or delete user by id
                    System.out.println("1-ADD | 2-DELETE");
                    System.out.print("Enter number of operation: ");
                    if (input.nextInt() == 1) {
                        addNewUser(input, users);
                    } else {
                        deleteUserById(input, users, products);
                    }
                    break;
                case ("7"):
                    System.out.println("1-ADD | 2-DELETE");
                    System.out.print("Enter number of operation: ");
                    if (input.nextInt() == 1) {
                        addNewProduct(input, products);
                    } else {
                        deleteProductById(input, users, products);
                    }
                    break;
                case ("8"):
                    return; // stop the program
                default:
                    System.out.println("Please, enter a valid number form 1 to 8");
                    break;
            }
        }
    }

    /**
     * The method deletes the product and all his history of transactions by passed id
     * @param input Object of Scanner class
     * @param users List of users
     * @param products List of products
     */
    private static void deleteProductById(Scanner input, ArrayList<User> users, ArrayList<Product> products) {
        System.out.print("Enter id of product: ");
        int deleteID = input.nextInt();
        for (User user : users) {
            Product find = products.get(deleteID - 1);
            while (user.products.contains(find)) {
                user.products.remove(find);
            }
        }
        products.remove(deleteID - 1);
    }

    /**
     * The method adds a new product with new properties
     * @param input Object of Scanner class
     * @param products List of products
     */
    private static void addNewProduct(Scanner input, ArrayList<Product> products) {
        System.out.print("Enter product name: ");
        String product = input.nextLine();
        while (product.isEmpty()) {
            product = input.nextLine();
        }
        System.out.print("Enter product price: ");
        String str = input.nextLine();
        while (!isNumeric(str)) {
            System.out.print("Enter a valid product price: ");
            str = input.next();
        }

        double priceNewProduct = Double.parseDouble(str);
        addProduct(products, product, priceNewProduct);
    }

    /**
     * The method deletes the user and all his history of transactions by passed id
     * @param input Object of Scanner class
     * @param users List of users
     * @param products List of products
     */
    private static void deleteUserById(Scanner input, ArrayList<User> users, ArrayList<Product> products) {
        System.out.print("Enter id of User: ");
        int deleteUserID = input.nextInt();
        for (Product item : products) {
            String find = users.get(deleteUserID - 1).firstName + " " + users.get(deleteUserID - 1).lastName;
            while (item.productsBuyer.contains(find)) {
                item.productsBuyer.remove(find);
            }
        }
        users.remove(deleteUserID - 1);
    }

    /**
     * The method adds a new user with new properties
     * @param input Object of Scanner class
     * @param users List of users
     */
    private static void addNewUser(Scanner input, ArrayList<User> users) {
        System.out.print("Enter FirstName: ");
        String firstName = input.nextLine();
        while (firstName.isEmpty()) {
            firstName = input.nextLine();
        }

        System.out.print("Enter LastName: ");
        String lastName = input.nextLine();
        while (lastName.isEmpty()) {
            lastName = input.nextLine();
        }
        System.out.print("Enter amount of money: ");
        String amount = input.nextLine();
        while (!isNumeric(amount)) {
            System.out.print("Enter a valid product price: ");
            amount = input.next();
        }

        double amountMoney = Double.parseDouble(amount);
        addUser(users, firstName, lastName, amountMoney);
    }

    /**
     * The method gets an array of products and print each element
     * @param products Array of products
     */
    private static void listProducts(ArrayList<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * The method gets an array of users and print each element
     * @param users Array of users
     */
    private static void listUsers(ArrayList<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * The method creates and add new user object to users list
     * @param users current list of users
     * @param firstName User's firstname
     * @param lastName User's lastname
     * @param amountMoney User's cash
     */
    private static void addUser(ArrayList<User> users, String firstName, String lastName, double amountMoney) {
        users.add(new User(users.size() + 1, firstName, lastName, amountMoney));
    }

    /**
     * Add new Product
     * @param products current list of products
     * @param product
     * @param priceNewProduct
     */
    private static void addProduct(ArrayList<Product> products, String product, double priceNewProduct) {
        products.add(new Product(products.size() + 1, product, priceNewProduct));
    }

    /**
     * The method prints all buyers by product id
     * @param input Object of Scanner class
     * @param products List of products
     */
    private static void printProductsBuyer(Scanner input, ArrayList<Product> products) {
        System.out.print("Enter product id: ");
        int productID = input.nextInt();

        for (Product item : products) {
            if (item.id == productID && item.productsBuyer.size() > 0) {
                System.out.println(item.productsBuyer.toString());
            }
        }
    }

    /**
     * The method prints user shopping cart by id
     * @param input Object of Scanner class
     * @param users List of users.
     */
    private static void printUserProducts(Scanner input, ArrayList<User> users) {
        System.out.print("Enter User id: ");
        int userID = input.nextInt();
        for (User user : users) {
            if (user.id == userID && user.products.size() > 0) {
                System.out.println(user.products.toString());
            }
        }
    }

    /**
     * This method implements transaction buying product with a particular product id
     * by the user with a particular user id.
     *
     * @param users List of users.
     * @param products List of products.
     * @param input Object of Scanner class
     */
    private static void transaction(ArrayList<User> users, ArrayList<Product> products, Scanner input) {
        System.out.print("Enter User id: ");
        int userID = input.nextInt();
        System.out.print("Enter product id: ");
        int productID = input.nextInt();

        double userCash = 0;
        double productPrice = 0;
        int numUser = 0;
        int numProduct = 0;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == userID) {
                userCash = users.get(i).userMoney;
                numUser = i;
            }
        }

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id == productID) {
                productPrice = products.get(i).price;
                numProduct = i;
            }
        }

        if (productPrice > userCash) {
            System.out.println("You don't have enough money");
        } else {
            users.get(numUser).userMoney -= products.get(numProduct).price; // decrease user cash on the product price
            users.get(numUser).products.add(products.get(numProduct)); // add the purchased product to the user’s shopping cart
            products.get(numProduct).productsBuyer.add(users.get(numUser).firstName + " " + users.get(numUser).lastName);
            System.out.println("Successful transaction");
        }
    }

    /**
     * Check input is numeric
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

