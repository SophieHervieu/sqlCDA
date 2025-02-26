package com.adrar.sqlcda.repository;

import com.adrar.sqlcda.db.Bdd;
import com.adrar.sqlcda.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
