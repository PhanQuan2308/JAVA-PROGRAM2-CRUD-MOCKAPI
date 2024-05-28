package session2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MockAPIClient {
    public void createUserDataToMockAPI(User user) {
        try {
            URL url = new URL("https://664f5401fafad45dfae35964.mockapi.io/v1/users/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Chuyển đổi  User thành chuỗi JSON
            Gson gson = new Gson();
            String jsonData = gson.toJson(user);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Data created successfully on MockAPI.");
            } else {
                System.out.println("Failed to create data on MockAPI. Response code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateUserOnMockAPI(User user){
        try {
            URL url = new URL("https://664f5401fafad45dfae35964.mockapi.io/v1/users/users/" + user.getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String jsonData = gson.toJson(user);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input,0,input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("User updated successfully on MockAPI.");
            } else {
                System.out.println("Failed to update user on MockAPI. Response code: " + responseCode);
            }
            connection.disconnect();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteUserOnMockAPI(int userId) {
        try {

            URL url = new URL("https://664f5401fafad45dfae35964.mockapi.io/v1/users/users/" + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            boolean success = responseCode == HttpURLConnection.HTTP_OK;

            if (success) {
                System.out.println("User deleted successfully on MockAPI.");
            } else {
                System.out.println("Failed to delete user on MockAPI. Response code: " + responseCode);
            }

            connection.disconnect();
            return success;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<User> getAllUserOnMockAPI() {
        try {
            URL url = new URL("https://664f5401fafad45dfae35964.mockapi.io/v1/users/users" );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            Gson gson = new Gson();
            List<User> userList = gson.fromJson(response.toString(), new TypeToken<List<User>>(){}.getType());
            connection.disconnect();
            return userList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch users from MockAPI.", e);
        }
    }
}
