<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orden Confirmada — Boutique Moderna</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>

    <!-- Cabecera -->
    <header class="site-header">
        <div class="header-container">
            <div></div>
            <div class="brand-logo">
                <a href="${pageContext.request.contextPath}/catalogo" class="brand-title">Boutique Moderna</a>
            </div>
            <div></div>
        </div>
    </header>

    <main class="main-wrapper">

        <c:choose>
            <c:when test="${not empty pedido}">
                <!-- Contenedor Principal de la Orden Confirmada -->
                <div class="confirmation-container">
                    
                    <div class="confirmation-badge-icon" aria-hidden="true">
                        &#10003;
                    </div>

                    <h1 class="confirmation-title">Orden Confirmada con Éxito</h1>
                    <p class="confirmation-subtitle">
                        Estimado(a) <strong>${pedido.nombreCliente}</strong>, agradecemos su preferencia por la distinción y exclusividad de Boutique Moderna. Hemos enviado el comprobante oficial a <strong>${pedido.email}</strong>.
                    </p>

                    <!-- Caja Editorial con el Código del Pedido -->
                    <div class="order-code-box">
                        <span class="order-code-label">Identificador Único de Pedido</span>
                        <span class="order-code-value">${pedido.codigoPedido}</span>
                    </div>

                    <!-- Tabla de Detalle y Liquidación de la Orden -->
                    <table class="order-details-table">
                        <thead>
                            <tr>
                                <th colspan="2">Resumen Editorial de la Adquisición</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="table-label">Prenda Seleccionada</td>
                                <td>
                                    <strong>${pedido.producto.nombre}</strong><br>
                                    <small style="color: var(--text-secondary);">${pedido.producto.composicion}</small>
                                </td>
                            </tr>
                            <tr>
                                <td class="table-label">Especificaciones</td>
                                <td>Talla: <strong>${pedido.tallaSeleccionada}</strong> &bull; Cantidad: <strong>${pedido.cantidad} unidad(es)</strong></td>
                            </tr>
                            <tr>
                                <td class="table-label">Precio Unitario</td>
                                <td>${pedido.producto.precioFormateado}</td>
                            </tr>
                            <tr>
                                <td class="table-label">Método de Entrega</td>
                                <td>
                                    <strong>${pedido.metodoEntrega}</strong>
                                    <c:if test="${not empty pedido.direccion}">
                                        <br><small style="color: var(--text-secondary);">${pedido.direccion}</small>
                                    </c:if>
                                </td>
                            </tr>
                            <c:if test="${not empty pedido.notas}">
                                <tr>
                                    <td class="table-label">Instrucciones Especiales</td>
                                    <td><small style="color: var(--text-secondary);">${pedido.notas}</small></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td class="table-label">Teléfono de Contacto</td>
                                <td>${pedido.telefono}</td>
                            </tr>
                            <tr>
                                <td class="table-label">Fecha de Procesamiento</td>
                                <td>${pedido.fechaFormateada}</td>
                            </tr>
                            <tr style="background-color: #faf9f7; font-size: 1.05rem;">
                                <td class="table-label" style="font-weight: 700; color: var(--text-primary);">Total Liquidado</td>
                                <td style="font-weight: 700; color: var(--text-primary); font-family: var(--font-serif); font-size: 1.25rem;">
                                    ${pedido.totalFormateado}
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- Acciones -->
                    <div class="cta-actions">
                        <a href="${pageContext.request.contextPath}/catalogo" class="btn-buy" style="padding: 0.85rem 1.8rem;">
                            Volver al Catálogo Editorial
                        </a>
                        <button onclick="window.print()" class="btn-secondary">
                            Imprimir Comprobante
                        </button>
                    </div>

                </div>
            </c:when>

            <c:otherwise>
                <!-- Estado sin orden activa en sesión -->
                <div class="confirmation-container">
                    <h2 class="confirmation-title">No hay una orden activa</h2>
                    <p class="confirmation-subtitle">
                        No hemos detectado un pedido procesado recientemente en esta sesión.
                    </p>
                    <div class="cta-actions">
                        <a href="${pageContext.request.contextPath}/catalogo" class="btn-buy">
                            Explorar la Colección
                        </a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

    </main>

    <!-- Pie de Página -->
    <footer class="site-footer">
        <div class="footer-container">
            <div>
                <h3 class="footer-brand-title">Boutique Moderna</h3>
                <p class="footer-text">
                    La máxima expresión del diseño atemporal. Cada pieza refleja un compromiso innegociable con la nobleza textil y la alta costura.
                </p>
            </div>
            <div>
                <h4 class="footer-col-title">Soporte</h4>
                <ul class="footer-links">
                    <li><a href="#" class="footer-link">Rastreo de Guía</a></li>
                    <li><a href="#" class="footer-link">Garantía de Satisfacción</a></li>
                    <li><a href="#" class="footer-link">Contacto de Conserjería</a></li>
                </ul>
            </div>
            <div>
                <h4 class="footer-col-title">Atelier</h4>
                <ul class="footer-links">
                    <li><span class="footer-link">Calle 82 # 11-45, Zona G, Bogotá</span></li>
                    <li><span class="footer-link">+57 (601) 745-8900</span></li>
                    <li><span class="footer-link">concierge@boutiquemoderna.com</span></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <span>&copy; 2026 Boutique Moderna S.A.S.</span>
            <span>Comprobante Electrónico Válido &bull; Arquitectura Java Web</span>
        </div>
    </footer>

</body>
</html>
