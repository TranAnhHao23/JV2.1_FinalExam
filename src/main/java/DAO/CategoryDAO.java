package DAO;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    connection.MyConnection myConnection = new connection.MyConnection();

    private static final String GET_ALL_CATEGORY = "select * from category";

    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idCategory = resultSet.getInt(1);
                String nameCategory = resultSet.getString(2);
                categories.add(new Category(idCategory,nameCategory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
