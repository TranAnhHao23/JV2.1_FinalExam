package control;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import model.Category;
import model.Product;
import model.ProductCategory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ControllerServlet", value = "/home")
public class ControllerServlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    public void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createGet":
                createGet(request,response);
                break;
            case "createPost":
                createPost(request,response);
                break;
            case "editGet":
                editGet(request,response);
                break;
            case "editPost":
                editPost(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "searchByName":
                searchByName(request,response);
                break;
            default:
                displayAll(request, response);
                break;
        }
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("nameSearch");
        ArrayList<ProductCategory> productCategories = productDAO.findProductByName(nameSearch);
        request.setAttribute("productCategories",productCategories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        productDAO.deleteProductById(idProduct);
        displayAll(request,response);
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        String nameProduct = request.getParameter("nameProduct");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        productDAO.updateProductById(idProduct,nameProduct,price,quantity,color,description,idCategory);
        displayAll(request,response);
    }

    private void editGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        Product product = productDAO.getProductById(idProduct);
        ArrayList<Category> categories = categoryDAO.getAllCategory();
        request.setAttribute("categories",categories);
        request.setAttribute("product",product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-product.jsp");
        requestDispatcher.forward(request,response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameProduct = request.getParameter("nameProduct");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        productDAO.addProduct(nameProduct,price,quantity,color,description,idCategory);
        displayAll(request,response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> categories = categoryDAO.getAllCategory();
        request.setAttribute("categories",categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-new-product.jsp");
        requestDispatcher.forward(request,response);
    }

    private void displayAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ProductCategory> productCategories = productDAO.getAllProduct();
        request.setAttribute("productCategories",productCategories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request,response);
    }
}
