package com.boutique.servlet;

import com.boutique.model.Pedido;
import com.boutique.model.Producto;
import com.boutique.repository.ProductoRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

/**
 * Controlador Servlet que gestiona el flujo de compra:
 * - doGet: Prepara y renderiza el formulario de checkout con la prenda seleccionada.
 * - doPost: Valida los datos del cliente, calcula los montos, genera la orden y muestra la confirmación.
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

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
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        int productoId = 1; // ID por defecto si no se especifica

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                productoId = Integer.parseInt(idParam.trim());
            } catch (NumberFormatException e) {
                productoId = 1;
            }
        }

        Optional<Producto> optProducto = productoRepository.buscarPorId(productoId);
        if (optProducto.isEmpty()) {
            // Si el ID solicitado no existe, redirigir al catálogo principal
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }

        Producto producto = optProducto.get();
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Captura de datos del formulario semántico
        String productoIdParam = request.getParameter("productoId");
        String nombreCliente = request.getParameter("nombreCliente");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String metodoEntrega = request.getParameter("metodoEntrega");
        String direccion = request.getParameter("direccion");
        String notas = request.getParameter("notas");
        String tallaSeleccionada = request.getParameter("talla");
        String cantidadParam = request.getParameter("cantidad");

        // Validación de producto
        int productoId = 1;
        try {
            if (productoIdParam != null && !productoIdParam.trim().isEmpty()) {
                productoId = Integer.parseInt(productoIdParam.trim());
            }
        } catch (NumberFormatException e) {
            productoId = 1;
        }

        Optional<Producto> optProducto = productoRepository.buscarPorId(productoId);
        if (optProducto.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }
        Producto producto = optProducto.get();

        // Parseo de cantidad
        int cantidad = 1;
        try {
            if (cantidadParam != null && !cantidadParam.trim().isEmpty()) {
                cantidad = Integer.parseInt(cantidadParam.trim());
                if (cantidad < 1) cantidad = 1;
            }
        } catch (NumberFormatException e) {
            cantidad = 1;
        }

        // Validación de campos del negocio
        StringBuilder errorMensaje = new StringBuilder();
        if (nombreCliente == null || nombreCliente.trim().length() < 3) {
            errorMensaje.append("Por favor ingrese su nombre y apellido completo.<br>");
        }
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errorMensaje.append("Por favor proporcione un correo electrónico válido.<br>");
        }
        if (telefono == null || telefono.trim().length() < 7) {
            errorMensaje.append("Por favor ingrese un número telefónico de contacto válido.<br>");
        }
        if ("DOMICILIO".equalsIgnoreCase(metodoEntrega) && (direccion == null || direccion.trim().isEmpty())) {
            errorMensaje.append("Para envíos a domicilio es obligatorio indicar la dirección de entrega.<br>");
        }

        // Si existen errores de validación, retornar al formulario con los datos ingresados
        if (errorMensaje.length() > 0) {
            request.setAttribute("errorMensaje", errorMensaje.toString());
            request.setAttribute("producto", producto);
            request.setAttribute("nombreCliente", nombreCliente);
            request.setAttribute("email", email);
            request.setAttribute("telefono", telefono);
            request.setAttribute("metodoEntrega", metodoEntrega);
            request.setAttribute("direccion", direccion);
            request.setAttribute("notas", notas);
            request.setAttribute("tallaSeleccionada", tallaSeleccionada);
            request.setAttribute("cantidad", cantidad);
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }

        // Liquidación y cálculo de totales
        double total = producto.getPrecio() * cantidad;

        // Generación de código único de orden editorial (Ej: BM-2026-7842)
        int codigoAleatorio = 1000 + new Random().nextInt(9000);
        String codigoPedido = "BM-2026-" + codigoAleatorio;

        // Normalización del nombre del método de entrega para la vista
        String metodoEntregaLegible = "DOMICILIO".equalsIgnoreCase(metodoEntrega)
                ? "Envío Exclusivo a Domicilio"
                : "Recogida Personal en Boutique Flagship";

        // Instanciación del Modelo de Dominio Pedido
        Pedido pedido = new Pedido(
                codigoPedido,
                nombreCliente.trim(),
                email.trim(),
                telefono.trim(),
                metodoEntregaLegible,
                (direccion != null && !direccion.trim().isEmpty()) ? direccion.trim() : "No aplica (Recogida en tienda)",
                (notas != null) ? notas.trim() : "",
                producto,
                tallaSeleccionada != null ? tallaSeleccionada : "Estándar",
                cantidad,
                total
        );

        // Almacenar el pedido en el request scope y redirigir a la vista de confirmación
        request.setAttribute("pedido", pedido);
        request.getRequestDispatcher("/confirmacion.jsp").forward(request, response);
    }
}
