package com.boutique.servlet;

import com.boutique.model.Producto;
import com.boutique.repository.ProductoRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Controlador Servlet encargado de gestionar el despliegue del catálogo editorial
 * y procesar el filtrado dinámico por categoría ("TODOS", "HOMBRE", "MUJER").
 */
@WebServlet(name = "CatalogoServlet", urlPatterns = {"/catalogo", ""})
public class CatalogoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductoRepository productoRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productoRepository = ProductoRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Configurar codificación de caracteres para solicitudes y respuestas
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Obtener parámetro de filtrado HTTP GET
        String categoriaParam = request.getParameter("categoria");
        if (categoriaParam == null || categoriaParam.trim().isEmpty()) {
            categoriaParam = "TODOS";
        } else {
            categoriaParam = categoriaParam.trim().toUpperCase();
        }

        // Obtener lista filtrada de productos desde el repositorio de dominio
        List<Producto> productos = productoRepository.filtrarPorCategoria(categoriaParam);

        // Adjuntar atributos al alcance de la solicitud (Request Scope)
        request.setAttribute("productos", productos);
        request.setAttribute("categoriaActual", categoriaParam);
        request.setAttribute("totalProductos", productos.size());

        // Redireccionar la solicitud a la vista JSP correspondiente
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
