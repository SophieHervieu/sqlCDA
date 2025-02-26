import com.adrar.sqlcda.db.Bdd;
import com.adrar.sqlcda.model.User;
import com.adrar.sqlcda.repository.UserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User newUser = new User(
                "Sophie",
                "Hervieu",
                "test@test.com",
                "1234"
        );
        //UserRepository.save(newUser);
        //System.out.println(UserRepository.findByEmail(newUser.getEmail()).getId());
        User getUser = UserRepository.findByEmail("test@test2.com");
        if(getUser != null) {
            System.out.println(getUser);
        }
        else {
            System.out.println("Le compte n'existe pas");
        }

        List<User> allUsers = UserRepository.findAll();
        System.out.println(allUsers.get(0).toString());

        User newUser2 = new User(
                "Alexandre",
                "Ferraria",
                "test@test2.com",
                "123456"
        );
        String userEmail = "test@test.com";
        //UserRepository.update(newUser2, userEmail);
    }
}
