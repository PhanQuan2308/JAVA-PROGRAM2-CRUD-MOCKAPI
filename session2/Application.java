package session2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static final Scanner sc = new Scanner(System.in);
    public static final Controller controller = new Controller();
    public static void createUsers() {
        System.out.println("----------Create Users---------");

        System.out.println("Enter user name: ");
        String name = sc.nextLine();

        System.out.println("Enter user email: ");
        String email = sc.nextLine();

        User createUser = new User(name, email);

        try {
            controller.createUsers(createUser);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    public static void updateUser() {
        System.out.println("---------- Update User ----------");
        System.out.print("Enter user id to update:  ");
        int id = sc.nextInt();
        System.out.print("Enter new name: ");
        String name = sc.next();
        System.out.print("Enter new email: ");
        String email = sc.next();

        User updatedUser = new User(id, name, email);

        try {
            controller.updateUsers(updatedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteUser() {
        System.out.println("---------- Delete User ----------");
        System.out.print("Enter user id to delete: ");
        int id = sc.nextInt();

        try {

            boolean deleted = controller.deleteUsers(id);
            if (deleted) {
            } else {
                System.out.println("not found user with id: "+ id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void getAllUsers(){
        System.out.println("----------- Show All User ----------");
        try {
            List<User> users = controller.getAllUsers();
            if(users.isEmpty()){
                System.out.println("No user in database");
            }else {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonOutput = gson.toJson(users);
                System.out.println(jsonOutput);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Show All Users");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Đọc dấu Enter sau khi nhập số

            switch (choice) {
                case 1:
                    createUsers();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    getAllUsers();
                    break;
                case 5:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        } while (choice != 5);
    }

}
