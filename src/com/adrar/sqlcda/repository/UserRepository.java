package com.adrar.sqlcda.repository;

import com.adrar.sqlcda.db.Bdd;
import com.adrar.sqlcda.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    //Attribut
    private static final Connection connection = Bdd.getConnection();
    //Méthodes (CRUD)
    public static User save(User addUser){
        //Créer un objet user
        User newUser = null;
        try {
            //Requête
            String sql = "INSERT INTO users(firstname, lastname, email, password) VALUES(?, ?, ?, ?)";
            //Préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Bind les paramètres
            preparedStatement.setString(1, addUser.getFirstname());
            preparedStatement.setString(2, addUser.getLastname());
            preparedStatement.setString(3, addUser.getEmail());
            preparedStatement.setString(4, addUser.getPassword());
            //Exécution de la requête
            int nbrRows = preparedStatement.executeUpdate();
            //Vérifier si la requête est passée
            if(nbrRows > 0){
                newUser = new User(
                        addUser.getFirstname(),
                        addUser.getLastname(),
                        addUser.getEmail(),
                        addUser.getPassword()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isExist(String email) {
        boolean getUser = false;
        try {
            String sql = "SELECT id FROM users WHERE email = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Bind le paramètre
            preparedStatement.setString(1, email);
            //récupérer le résultat de la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            //Vérification du résultat
            while(resultSet.next()){
                getUser = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getUser;
    }

    public static User findByEmail(String email) {
        User getUser = null;
        try {
            String sql = "SELECT id, firstname, lastname, email FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, email);
            //Requête de consultation
            ResultSet resultSet = preparedStatement.executeQuery();
            //On boucle pour récupérer toutes les informations
            while (resultSet.next()){
                getUser = new User();
                getUser.setId(resultSet.getInt("id"));
                getUser.setFirstname(resultSet.getString("firstname"));
                getUser.setLastname(resultSet.getString("lastname"));
                getUser.setEmail(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUser;
    }

    public static List<User> findAll(){
        List<User> findUsers = new ArrayList<>();
        try {
            String sql = "SELECT id, firstname, lastname, email FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User findUser = new User();
                findUser.setId(resultSet.getInt("id"));
                findUser.setFirstname(resultSet.getString("firstname"));
                findUser.setLastname(resultSet.getString("lastname"));
                findUser.setEmail(resultSet.getString("email"));
                findUsers.add(findUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findUsers;
    }

    public static User update(User user, String email) {
        User updateUser = findByEmail(email);
        try {
            String sql = "UPDATE users SET firstname=?, lastname=?, email=?, password=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, updateUser.getId());
            int nbrRows = preparedStatement.executeUpdate();
            if(nbrRows > 0){
                updateUser.setFirstname(user.getFirstname());
                updateUser.setLastname(user.getLastname());
                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateUser;
    }

    public static User saveWithRoles(User addUser) {
        User newUser = null;
        try {
            String sql = "INSERT INTO users(firstname, lastname, email, password, role_id)" +
                    "VALUE(?, ?, ?, ?, ( SELECT id FROM roles WHERE roles_name = ?))";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, addUser.getFirstname());
            preparedStatement.setString(2, addUser.getLastname());
            preparedStatement.setString(3, addUser.getEmail());
            preparedStatement.setString(4, addUser.getPassword());
            preparedStatement.setString(5, addUser.getRoles().getRolesName());
            int nbrRows = preparedStatement.executeUpdate();
            if(nbrRows > 0) {
                newUser = addUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newUser;
    }
}
