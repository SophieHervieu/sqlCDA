import com.adrar.sqlcda.db.Bdd;
import com.adrar.sqlcda.model.User;
import com.adrar.sqlcda.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        User newUser = new User(
                "Sophie",
                "Hervieu",
                "test@test.com",
                "1234"
        );
        //UserRepository.save(newUser);
        System.out.println(UserRepository.findByEmail(newUser.getEmail()).getId());
    }
}
