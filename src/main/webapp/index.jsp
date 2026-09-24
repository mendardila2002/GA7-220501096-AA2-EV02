<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boutique Moderna — Piezas de Autor & Alta Costura</title>
    <!-- Estilos Quiet Luxury -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>

    <!-- Barra de Anuncios Editorial -->
    <aside class="top-announcement">
        Envíos de cortesía asegurados en todo el país &bull; Asesoría de estilo personalizada en Boutique Flagship
    </aside>

    <!-- Cabecera Principal Fija -->
    <header class="site-header">
        <div class="header-container">
            <nav aria-label="Navegación principal">
                <ul class="nav-links">
                    <li><a href="${pageContext.request.contextPath}/catalogo" class="nav-link ${categoriaActual == 'TODOS' || empty categoriaActual ? 'active' : ''}">Colección</a></li>
                    <li><a href="${pageContext.request.contextPath}/catalogo?categoria=HOMBRE" class="nav-link ${categoriaActual == 'HOMBRE' ? 'active' : ''}">Hombre</a></li>
                    <li><a href="${pageContext.request.contextPath}/catalogo?categoria=MUJER" class="nav-link ${categoriaActual == 'MUJER' ? 'active' : ''}">Mujer</a></li>
                </ul>
            </nav>

            <div class="brand-logo">
                <a href="${pageContext.request.contextPath}/catalogo" class="brand-title">
                    Boutique Moderna
                </a>
            </div>

            <div class="header-actions">
                <span class="action-badge">Atelier Bogotá &bull; 2026</span>
            </div>
        </div>
    </header>

    <!-- Contenido Principal -->
    <main class="main-wrapper">

        <!-- Sección Titular Editorial -->
        <section class="editorial-hero">
            <span class="hero-subtitle-tag">Colección Permanente &bull; Guardarropa Cápsula</span>
            <h1 class="hero-title">Piezas de Autor</h1>
            <p class="hero-description">
                Elegancia atemporal, cortes limpios y tejidos nobles concebidos para perdurar
                más allá de las tendencias efímeras. Cada prenda es confeccionada en tiradas limitadas
                por maestros artesanos.
            </p>
        </section>

        <!-- Pestañas de Filtrado Dinámico (HTTP GET) -->
        <nav class="filter-bar" aria-label="Filtro de categorías">
            <a href="${pageContext.request.contextPath}/catalogo?categoria=TODOS"
               class="filter-btn ${categoriaActual == 'TODOS' || empty categoriaActual ? 'active' : ''}">
                Todos (${totalProductos != null ? totalProductos : '8'})
            </a>
            <a href="${pageContext.request.contextPath}/catalogo?categoria=HOMBRE"
               class="filter-btn ${categoriaActual == 'HOMBRE' ? 'active' : ''}">
                Hombre
            </a>
            <a href="${pageContext.request.contextPath}/catalogo?categoria=MUJER"
               class="filter-btn ${categoriaActual == 'MUJER' ? 'active' : ''}">
                Mujer
            </a>
        </nav>

        <%-- Cuadrícula de Productos Renderizada con JSTL c:forEach --%>
        <section class="catalog-grid" aria-label="Catálogo de productos">
            <c:forEach var="item" items="${productos}">
                <article class="product-card">
                    <!-- Contenedor Visual (Aspect Ratio 3:4) -->
                    <div class="product-media">
                        <span class="badge-category"><c:out value="${item.categoria}"/></span>
                        <img src="${item.imagenUrl}"
                             alt="${item.nombre}"
                             class="product-img"
                             loading="lazy">
                    </div>

                    <!-- Información de la Prenda -->
                    <div class="product-info">
                        <span class="product-category-text">Línea Exclusiva</span>
                        <h2 class="product-title"><c:out value="${item.nombre}"/></h2>
                        <p class="product-composition"><c:out value="${item.composicion}"/></p>

                        <!-- Tallas Disponibles -->
                        <div class="product-sizes-wrapper">
                            <span class="size-label">Tallas:</span>
                            <span class="size-pill"><c:out value="${item.tallasTexto}"/></span>
                        </div>

                        <!-- Precio y Botón de Acción -->
                        <div class="product-footer">
                            <span class="product-price">${item.precioFormateado}</span>
                            <a href="${pageContext.request.contextPath}/checkout?id=${item.id}"
                               class="btn-buy"
                               title="Comprar ${item.nombre}">
                                Comprar Ahora &rarr;
                            </a>
                        </div>
                    </div>
                </article>
            </c:forEach>
        </section>

    </main>

    <!-- Pie de Página Institucional -->
    <footer class="site-footer">
        <div class="footer-container">
            <div>
                <h3 class="footer-brand-title">Boutique Moderna</h3>
                <p class="footer-text">
                    Curaduría de prendas concebidas bajo la filosofía de la discreción, el corte impecable y la autenticidad textil.
                    Confección ética y compromiso con la artesanía de lujo perdurable.
                </p>
            </div>
            <div>
                <h4 class="footer-col-title">Colección</h4>
                <ul class="footer-links">
                    <li><a href="${pageContext.request.contextPath}/catalogo?categoria=HOMBRE" class="footer-link">Sastrería Masculina</a></li>
                    <li><a href="${pageContext.request.contextPath}/catalogo?categoria=MUJER" class="footer-link">Atelier Femenino</a></li>
                    <li><a href="${pageContext.request.contextPath}/catalogo?categoria=TODOS" class="footer-link">Cápsula Esencial</a></li>
                </ul>
            </div>
            <div>
                <h4 class="footer-col-title">Atención al Cliente</h4>
                <ul class="footer-links">
                    <li><a href="#" class="footer-link">Cuidado de las Prendas</a></li>
                    <li><a href="#" class="footer-link">Guía de Tallas Internacional</a></li>
                    <li><a href="#" class="footer-link">Políticas de Envíos y Cambios</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <span>&copy; 2026 Boutique Moderna S.A.S. Todos los derechos reservados.</span>
            <span>Arquitectura Jakarta EE / Java Web &bull; Proyecto Formativo SENA</span>
        </div>
    </footer>

</body>
</html>
