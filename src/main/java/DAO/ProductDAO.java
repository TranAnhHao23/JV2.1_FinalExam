package DAO;

import connection.MyConnection;
import model.Product;
import model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    MyConnection myConnection = new MyConnection();

    private static final String GET_ALL_PRODUCT = "SELECT * FROM product inner join category on product.id_category = category.id_category;";
    private static final String CREATE_PRODUCT = "INSERT INTO product (product_name, price, quantity, color, description, id_category) VALUES (?, ? , ?, ?, ?, ?);";
    private static final String DELETE_PRODUCT_BY_ID = "delete from product where id_product = ?";
    private static final String GET_PRODUCT_BY_ID = "select * from product where id_product = ?;";
    private static final String UPDATE_PRODUCT_BY_ID = "UPDATE product SET product_name = ?, price = ?, quantity = ?, color = ?, description = ?, id_category = ? WHERE (id_product = ?);";
    private static final String FIND_PRODUCT_BY_NAME = "select * from product inner join category on product.id_category = category.id_category where product_name like ?;";

    public ArrayList<ProductCategory> findProductByName(String nameSearch){
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            preparedStatement.setString(1,nameSearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt(1);
                String nameProduct = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String color = resultSet.getString(5);
                String nameCategory = resultSet.getString(9);
                Product product = new Product(idProduct, nameProduct, price, quantity, color);
                productCategories.add(new ProductCategory(product, nameCategory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategories;
    }

    public void updateProductById(int idProduct, String nameProduct, double price, int quantity, String color, String description, int idCategory){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            preparedStatement.setString(1,nameProduct);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,quantity);
            preparedStatement.setString(4,color);
            preparedStatement.setString(5,description);
            preparedStatement.setInt(6,idCategory);
            preparedStatement.setInt(7,idProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int idProduct){
        Product product = null;
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nameProduct = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String color = resultSet.getString(5);
                String description = resultSet.getString(6);
                int idCategory = resultSet.getInt(7);
                product = new Product(idProduct,nameProduct,price,quantity,color,description,idCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void deleteProductById(int idProduct){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1,idProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductCategory> getAllProduct() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt(1);
                String nameProduct = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String color = resultSet.getString(5);
                String nameCategory = resultSet.getString(9);
                Product product = new Product(idProduct, nameProduct, price, quantity, color);
                productCategories.add(new ProductCategory(product, nameCategory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategories;
    }

    public void addProduct(String nameProduct, double price, int quantity, String color, String description, int idCategory) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT);
            preparedStatement.setString(1,nameProduct);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,quantity);
            preparedStatement.setString(4,color);
            preparedStatement.setString(5,description);
            preparedStatement.setInt(6,idCategory);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
