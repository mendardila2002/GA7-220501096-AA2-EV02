<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finalizar Orden — Boutique Moderna</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>

    <!-- Cabecera Simplificada para Checkout -->
    <header class="site-header">
        <div class="header-container">
            <div>
                <a href="${pageContext.request.contextPath}/catalogo" class="nav-link">&larr; Volver a la Colección</a>
            </div>
            <div class="brand-logo">
                <a href="${pageContext.request.contextPath}/catalogo" class="brand-title">Boutique Moderna</a>
            </div>
            <div class="header-actions">
                <span class="action-badge">Pago Seguro SSL</span>
            </div>
        </div>
    </header>

    <!-- Contenedor Principal de Checkout -->
    <main class="main-wrapper">

        <c:if test="${not empty errorMensaje}">
            <div class="alert-error" role="alert">
                <strong>Atención:</strong> Corrija los siguientes campos antes de continuar:
                <div style="margin-top: 0.5rem;">${errorMensaje}</div>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/checkout" method="POST" id="checkoutForm">
            <!-- Campo Oculto con el ID del Producto Seleccionado -->
            <input type="hidden" name="productoId" value="${producto.id}">

            <div class="checkout-grid">
                
                <!-- Columna Izquierda: Formulario Semántico Estructurado -->
                <div class="checkout-form-panel">
                    
                    <!-- Sección 1: Información del Cliente -->
                    <fieldset class="form-fieldset">
                        <legend class="section-legend">
                            <span>01.</span> Información del Cliente
                        </legend>

                        <div class="form-group">
                            <label for="nombreCliente" class="form-label">Nombre Completo *</label>
                            <input type="text"
                                   id="nombreCliente"
                                   name="nombreCliente"
                                   class="form-input"
                                   placeholder="Ej: Alejandro Silva Mendoza"
                                   value="${nombreCliente}"
                                   required>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="email" class="form-label">Correo Electrónico *</label>
                                <input type="email"
                                       id="email"
                                       name="email"
                                       class="form-input"
                                       placeholder="nombre@ejemplo.com"
                                       value="${email}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="telefono" class="form-label">Teléfono / Celular *</label>
                                <input type="tel"
                                       id="telefono"
                                       name="telefono"
                                       class="form-input"
                                       placeholder="+57 300 123 4567"
                                       value="${telefono}"
                                       required>
                            </div>
                        </div>
                    </fieldset>

                    <!-- Sección 2: Método de Entrega -->
                    <fieldset class="form-fieldset">
                        <legend class="section-legend">
                            <span>02.</span> Método de Entrega
                        </legend>

                        <div class="delivery-options-grid">
                            <label class="delivery-card" id="cardDomicilio">
                                <input type="radio"
                                       name="metodoEntrega"
                                       value="DOMICILIO"
                                       ${empty metodoEntrega || metodoEntrega == 'DOMICILIO' ? 'checked' : ''}
                                       onchange="toggleDireccion(true)">
                                <div>
                                    <div class="delivery-card-title">Envío a Domicilio</div>
                                    <div class="delivery-card-desc">Entrega asegurada en 2-4 días hábiles. Envío de cortesía.</div>
                                </div>
                            </label>

                            <label class="delivery-card" id="cardRecogida">
                                <input type="radio"
                                       name="metodoEntrega"
                                       value="RECOGIDA"
                                       ${metodoEntrega == 'RECOGIDA' ? 'checked' : ''}
                                       onchange="toggleDireccion(false)">
                                <div>
                                    <div class="delivery-card-title">Recogida en Boutique</div>
                                    <div class="delivery-card-desc">Atelier Flagship (Zona G, Bogotá). Listo en 24 horas.</div>
                                </div>
                            </label>
                        </div>

                        <div class="form-group" id="grupoDireccion">
                            <label for="direccion" class="form-label">Dirección de Envío Completa *</label>
                            <input type="text"
                                   id="direccion"
                                   name="direccion"
                                   class="form-input"
                                   placeholder="Ej: Calle 82 # 11-45, Apto 502, Bogotá"
                                   value="${direccion}">
                        </div>

                        <div class="form-group">
                            <label for="notas" class="form-label">Notas Adicionales de Entrega (Opcional)</label>
                            <textarea id="notas"
                                      name="notas"
                                      class="form-textarea"
                                      placeholder="Instrucciones para conserjería o preferencias de horario...">${notas}</textarea>
                        </div>
                    </fieldset>

                    <!-- Sección 3: Preferencias de la Prenda -->
                    <fieldset class="form-fieldset" style="margin-bottom: 0;">
                        <legend class="section-legend">
                            <span>03.</span> Especificaciones de la Prenda
                        </legend>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="talla" class="form-label">Seleccione su Talla *</label>
                                <select id="talla" name="talla" class="form-select" required>
                                    <c:forEach var="talla" items="${producto.tallas}">
                                        <option value="${talla}" ${tallaSeleccionada == talla ? 'selected' : ''}>
                                            Talla ${talla} &bull; Disponible en stock
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="cantidad" class="form-label">Cantidad de Unidades *</label>
                                <select id="cantidad" name="cantidad" class="form-select" onchange="actualizarTotal(this.value)">
                                    <option value="1" ${empty cantidad || cantidad == 1 ? 'selected' : ''}>1 pieza</option>
                                    <option value="2" ${cantidad == 2 ? 'selected' : ''}>2 piezas</option>
                                    <option value="3" ${cantidad == 3 ? 'selected' : ''}>3 piezas</option>
                                    <option value="4" ${cantidad == 4 ? 'selected' : ''}>4 piezas</option>
                                    <option value="5" ${cantidad == 5 ? 'selected' : ''}>5 piezas</option>
                                </select>
                            </div>
                        </div>
                    </fieldset>

                </div>

                <!-- Columna Derecha: Resumen de la Orden -->
                <aside class="order-summary-panel">
                    <h3 class="summary-title">Resumen de Selección</h3>

                    <div class="summary-item-card">
                        <img src="${producto.imagenUrl}" alt="${producto.nombre}" class="summary-item-img">
                        <div class="summary-item-details">
                            <div>
                                <span class="summary-item-tag">${producto.categoria} &bull; Autor</span>
                                <h4 class="summary-item-name">${producto.nombre}</h4>
                            </div>
                            <div class="summary-item-price" id="precioUnitarioTexto">
                                ${producto.precioFormateado}
                            </div>
                        </div>
                    </div>

                    <div class="summary-breakdown">
                        <div class="summary-row">
                            <span>Precio Unitario</span>
                            <span id="resumenPrecioUnitario">${producto.precioFormateado}</span>
                        </div>
                        <div class="summary-row">
                            <span>Unidades</span>
                            <span id="resumenCantidad">1</span>
                        </div>
                        <div class="summary-row">
                            <span>Envío Asegurado</span>
                            <span style="color: var(--accent-gold); font-weight: 600;">Cortesía ($0 COP)</span>
                        </div>
                        <div class="summary-row total-row">
                            <span>Total Liquidado</span>
                            <span id="resumenTotal">${producto.precioFormateado}</span>
                        </div>
                    </div>

                    <button type="submit" class="btn-submit-order">
                        Confirmar y Procesar Orden &rarr;
                    </button>

                    <div class="security-notice">
                        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                            <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
                        </svg>
                        Transacción cifrada y protegida por Boutique Moderna
                    </div>
                </aside>

            </div>
        </form>

    </main>

    <script>
        const precioUnitario = ${producto.precio};

        function actualizarTotal(cantidad) {
            const qty = parseInt(cantidad) || 1;
            const total = precioUnitario * qty;
            
            document.getElementById('resumenCantidad').textContent = qty;
            
            const formateador = new Intl.NumberFormat('es-CO', {
                style: 'currency',
                currency: 'COP',
                maximumFractionDigits: 0
            });
            document.getElementById('resumenTotal').textContent = formateador.format(total) + " COP";
        }

        function toggleDireccion(mostrar) {
            const grupo = document.getElementById('grupoDireccion');
            const input = document.getElementById('direccion');
            if (mostrar) {
                grupo.style.display = 'flex';
                input.required = true;
            } else {
                grupo.style.display = 'none';
                input.required = false;
            }
        }

        // Inicialización
        window.addEventListener('DOMContentLoaded', () => {
            const cantSelect = document.getElementById('cantidad');
            if (cantSelect) actualizarTotal(cantSelect.value);

            const checkedRadio = document.querySelector('input[name="metodoEntrega"]:checked');
            if (checkedRadio) {
                toggleDireccion(checkedRadio.value === 'DOMICILIO');
            }
        });
    </script>

</body>
</html>
