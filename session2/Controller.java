package session2;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    private final MockAPIClient mockAPIClient = new MockAPIClient();

    public void createUsers(User user) {
        mockAPIClient.createUserDataToMockAPI(user);
    }

    public void updateUsers(User user) {
        mockAPIClient.updateUserOnMockAPI(user);
    }

    public List<User> getAllUsers() {
        return mockAPIClient.getAllUserOnMockAPI();
    }

    public boolean deleteUsers(int id) {
        return mockAPIClient.deleteUserOnMockAPI(id);
    }
}
