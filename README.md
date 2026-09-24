# Boutique Moderna — Plataforma E-Commerce Java Web (MVC)

<div align="center">

![SENA](https://img.shields.io/badge/SENA-ADSO%20Virtual-39A900?style=for-the-badge&logo=sena&logoColor=white)
![Java](https://img.shields.io/badge/Java-17%20LTS%20%2F%2023-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-10%20(Servlet%206.0%20%2F%20JSP%203.1)-E61F24?style=for-the-badge&logo=eclipseide&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Tomcat-10.1%20Embebido%20%2F%20Standalone-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

</div>

---

### 📋 Información Institucional y de la Evidencia de Aprendizaje

| Campo | Detalle Institucional |
| :--- | :--- |
| **Institución** | Servicio Nacional de Aprendizaje — **SENA** |
| **Programa de Formación** | Tecnólogo en **Análisis y Desarrollo de Software (ADSO)** |
| **Modalidad** | Virtual |
| **Código de Formato Institucional** | **GFPI-F-135 V02** |
| **Fase / Guía de Aprendizaje** | Guía de Aprendizaje 7 (**GA7**) |
| **Código de la Evidencia** | **GA7-220501096-AA2-EV02** |
| **Denominación de la Evidencia** | **Módulos de *software* codificados y probados** |
| **Aprendiz** | **Iván Andrés Méndez Ardila** |
| **Proyecto Formativo** | Plataforma de Comercio Electrónico "Boutique Moderna" |
| **Enfoque Técnico** | Aplicación Web con Servlets, JSP, JSTL y Arquitectura MVC Nativa |

---

## 1. Alcance y Matriz de Cumplimiento de la Guía de Aprendizaje

De acuerdo con los requerimientos específicos de la evidencia **GA7-220501096-AA2-EV02 (GFPI-F-135 V02)**, se presenta la correspondencia de cada elemento desarrollado:

| Elemento Solicitado en la Guía | Implementación en la Solución de Software | Evidencia / Archivo |
| :--- | :--- | :---: |
| **Artefactos del ciclo de software previos** | Codificación basada en los artefactos de diseño: diagramas de clases (`Producto`, `Pedido`), casos de uso (*Consultar Catálogo*, *Filtrar Colección*, *Comprar*, *Generar Orden*), historias de usuario y prototipos UI. | Modelos y Controladores en `src/main/java/com/boutique/` |
| **Herramientas de versionamiento** | Creación y administración del proyecto bajo control de versiones con **Git** y repositorio alojado en **GitHub** con historial de commits estructurado. | Control de versiones Git |
| **Formularios HTML con Servlets** | Formulario semántico de compra y liquidación que envía parámetros hacia el controlador servlet mediante `POST`. | [`src/main/webapp/checkout.jsp`](file:///c:/Users/hdtol/OnehDrive/Documents/2026/SENA/Ivan/GA07/GA7-220501096-AA2-EV02/src/main/webapp/checkout.jsp) |
| **Utilizar métodos GET y POST** | • **Método GET:** Consulta general de catálogo (`CatalogoServlet.doGet`), filtros por categoría y precarga de prenda en formulario (`CheckoutServlet.doGet`).<br>• **Método POST:** Validación en el servidor de campos requeridos, cálculo matemático de subtotales/totales y emisión de la orden (`CheckoutServlet.doPost`). | [`CatalogoServlet.java`](file:///c:/Users/hdtol/OnehDrive/Documents/2026/SENA/Ivan/GA07/GA7-220501096-AA2-EV02/src/main/java/com/boutique/servlet/CatalogoServlet.java)<br>[`CheckoutServlet.java`](file:///c:/Users/hdtol/OnehDrive/Documents/2026/SENA/Ivan/GA07/GA7-220501096-AA2-EV02/src/main/java/com/boutique/servlet/CheckoutServlet.java) |
| **Utilizar elementos de JSP** | Vistas dinámicas con directivas de página (`<%@ page ... %>`), directivas de taglib JSTL (`<%@ taglib ... %>`), iteraciones `<c:forEach>`, condicionales `<c:if>`, escape seguro `<c:out>` y expresiones EL `${...}`, erradicando el uso de scriptlets Java obsoletos (`<% ... %>`). | [`index.jsp`](file:///c:/Users/hdtol/OnehDrive/Documents/2026/SENA/Ivan/GA07/GA7-220501096-AA2-EV02/src/main/webapp/index.jsp)<br>[`checkout.jsp`](file:///c:/Users/hdtol/OnehDrive/Documents/2026/SENA/Ivan/GA07/GA7-220501096-AA2-EV02/src/main/webapp/checkout.jsp)<br>[`confirmacion.jsp`](file:///c:/Users/hdtol/OnehDrive/Documents/2026/SENA/Ivan/GA07/GA7-220501096-AA2-EV02/src/main/webapp/confirmacion.jsp) |
| **Módulos codificados y probados** | Verificación funcional de navegación, persistencia en memoria, cálculo de totales y pruebas de interfaz responsiva. | Pruebas funcionales documentadas en Sección 7 |

---

## 2. Pila Tecnológica y Arquitectura

| Componente | Tecnología | Versión / Estándar | Justificación Técnica |
| :--- | :--- | :--- | :--- |
| **Lenguaje de Programación** | Java (JDK) | Java 17 LTS / Compatible 21 y 23 | Tipado estático robusto, Streams API y soporte multiplataforma. |
| **Plataforma Web Empresarial**| Jakarta EE | Jakarta EE 10 (Servlet API 6.0, JSP 3.1) | Estándar industrial contemporáneo para desarrollo web en Java. |
| **Biblioteca de Etiquetas** | Jakarta JSTL | API 3.0.0 & GlassFish Impl 3.0.1 | Renderizado dinámico y seguro sin lógica Java acoplada a las vistas. |
| **Gestor de Compilación** | Apache Maven | Maven 3.9+ (WAR Packaging) | Automatización del ciclo de compilación, dependencias y empaquetado. |
| **Servidor de Aplicaciones** | Apache Tomcat | Tomcat 10.1+ (Embebido y Standalone) | Motor de servlets compatible nativamente con la especificación Jakarta EE 10. |
| **Patrón de Arquitectura** | MVC Nativo | Modelo - Vista - Controlador | Separación estricta de lógica de negocio, control y presentación. |
| **Frontend Editorial** | JSP + HTML5 + CSS3 | Flexbox, CSS Grid, Tipografías Web | Experiencia de usuario responsiva inspirada en *"Quiet Luxury"*. |

---

## 3. Estructura del Directorio del Proyecto

```text
GA7-220501096-AA2-EV02/
├── pom.xml                                  <- Descriptor Maven y gestión de dependencias Jakarta EE
├── mvnw.cmd                                 <- Script wrapper de Maven para Windows
├── README.md                                <- Sustentación técnica formal de la evidencia SENA
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── boutique/
│   │   │           ├── model/
│   │   │           │   ├── Producto.java    <- Entidad de dominio encapsulada (JavaBean Serializable)
│   │   │           │   └── Pedido.java      <- Entidad de orden de compra y liquidación
│   │   │           ├── repository/
│   │   │           │   └── ProductoRepository.java <- Acceso a datos en memoria (Patrón Singleton)
│   │   │           ├── servlet/
│   │   │           │   ├── CatalogoServlet.java    <- Controlador Servlet: Manejo de HTTP GET y filtros
│   │   │           │   └── CheckoutServlet.java    <- Controlador Servlet: Manejo de HTTP GET y POST
│   │   │           └── server/
│   │   │               └── EmbeddedTomcatServer.java <- Servidor Tomcat 10 embebido (1 comando)
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml                  <- Descriptor de despliegue Jakarta EE 6.0
│   │       ├── css/
│   │       │   └── styles.css               <- Hoja de estilos con diseño editorial y responsive
│   │       ├── index.jsp                    <- Vista del catálogo de productos y filtros por colección
│   │       ├── checkout.jsp                 <- Vista de formulario semántico de compra y liquidación
│   │       └── confirmacion.jsp             <- Vista de comprobante formal y confirmación de compra
└── target/
    └── BoutiqueModerna.war                  <- Artefacto web empaquetado para despliegue
```

---

## 4. Identidad Visual y Experiencia "Quiet Luxury"

El diseño visual de **Boutique Moderna** implementa una estética de alta gama:
- **Paleta Cromática Atemporal:** Fondo cálido alabastro (`#fcfbf9`), blanco puro (`#ffffff`), negro sastre (`#141414`), grises neutros y acentos sutiles en oro champagne (`#a38c6d`).
- **Tipografía Editorial:** Encabezados en *Playfair Display* (serif clásico) combinados con la legibilidad moderna de *Plus Jakarta Sans*.
- **Cuadrícula Editorial (3:4):** Imágenes con proporción fotográfica de alta costura, efectos de zoom sutil al pasar el cursor y badges flotantes de categoría y tallas.
- **Microinteracciones y Formularios Semánticos:** Validación en tiempo real, selector visual de tipo de entrega (Domicilio asegurado vs. Recogida en Flagship Boutique) y recálculo automático de subtotales en Pesos Colombianos (`$ COP`).

---

## 5. Instrucciones de Compilación y Empaquetado

### Opción A: Usando el Wrapper Directo (`mvnw.cmd` — Recomendado en Windows)
No requiere tener instalada la variable de entorno global `mvn`:
```powershell
.\mvnw.cmd clean package
```

### Opción B: Usando Maven Global (`mvn`)
```powershell
mvn clean package
```

### Artefacto Generado:
Al finalizar satisfactoriamente la compilación, se generará el paquete estándar WAR en:
```text
target/BoutiqueModerna.war
```

---

## 6. Instrucciones de Ejecución Local y Visualización en Navegador

### Modalidad 1: Servidor Tomcat 10 Embebido (En 1 solo comando)
El proyecto cuenta con un servidor integrado en la clase `EmbeddedTomcatServer`. Éste comprueba si el puerto `8080` está en uso (ej. por XAMPP u otro servicio activo) y, de estar ocupado, conmuta automáticamente al puerto `8085`:

```powershell
.\mvnw.cmd compile exec:java
```

*(O con Maven global: `mvn compile exec:java`)*.

Una vez que en la consola se notifique que el servidor está en ejecución, abre tu navegador web en:
* 🌐 **Catálogo Principal:** [http://localhost:8085/catalogo](http://localhost:8085/catalogo)
* 🌐 **Ruta Raíz:** [http://localhost:8085/](http://localhost:8085/)

> **Nota de puerto personalizado:** Si deseas forzar un puerto diferente, puedes pasar el parámetro `-Dport`:
> ```powershell
> .\mvnw.cmd compile exec:java "-Dport=9090"
> ```

---

### Modalidad 2: Despliegue en Servidor Apache Tomcat 10 Standalone
Si dispones de una instalación externa de Apache Tomcat 10:
1. Copia el archivo empaquetado:
   ```powershell
   Copy-Item "target\BoutiqueModerna.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\"
   ```
2. Inicia el servicio de Tomcat o ejecuta `bin\startup.bat`.
3. Ingresa en el navegador a:
   ```text
   http://localhost:8080/BoutiqueModerna/catalogo
   ```

---

## 7. Matriz de Pruebas Funcionales y Casos de Uso

| Caso de Prueba | Módulo / Acción | Método HTTP | Parámetros / Entrada | Comportamiento y Resultado Obtenido |
| :---: | :--- | :---: | :--- | :--- |
| **CP-01** | Carga del Catálogo | `GET` | `/catalogo` | Recupera del repositorio los 8 productos y renderiza `index.jsp` con imágenes 3:4, precios en COP, badges y tallas. |
| **CP-02** | Filtrado Colección Hombre | `GET` | `/catalogo?categoria=HOMBRE` | Aplica filtro mediante Streams en `ProductoRepository`; despliega únicamente las 4 prendas masculinas con pestaña activa. |
| **CP-03** | Filtrado Colección Mujer | `GET` | `/catalogo?categoria=MUJER` | Despliega exclusivamente las 4 prendas femeninas manteniendo la coherencia de navegación. |
| **CP-04** | Restaurar Todos | `GET` | `/catalogo?categoria=TODOS` | Restablece la visualización de la colección completa. |
| **CP-05** | Precarga de Compra | `GET` | `/checkout?id=2` | Busca la prenda por ID y envía el objeto `Producto` a `checkout.jsp` cargando imagen, título, precio unitario y tallas. |
| **CP-06** | Validación de Errores | `POST` | `/checkout` (Campos vacíos) | El servlet valida nombre, email, teléfono y dirección obligatoria en domicilio. Muestra alerta `alert-error` preservando los datos ya ingresados. |
| **CP-07** | Procesamiento de Compra | `POST` | `/checkout` (Datos válidos) | Calcula el total, crea el objeto `Pedido` con código único (ej. `BM-2026-8432`) y redirecciona a `confirmacion.jsp`. |
| **CP-08** | Emisión de Comprobante | N/A | Clic en *"Imprimir Comprobante"* | Ejecuta `window.print()` con estilos CSS `@media print` para generar el soporte físico/PDF de la orden. |

---

## 8. Lineamientos para la Entrega de la Evidencia (SENA)

Conforme a las instrucciones generales de entrega establecidas en la guía:

1. **Nomenclatura reglamentaria del archivo comprimido:**
   ```text
   IVANMENDEZ_AA2_EV02.zip
   ```
2. **Contenido del paquete de entrega:**
   - Código fuente completo del proyecto (`pom.xml`, `mvnw.cmd`, `src/`, `README.md`).
   - Archivo `ENLACE_REPOSITORIO.txt` con el enlace del repositorio Git / GitHub correspondiente.
3. **Canal de Envío:**
   - Plataforma Virtual SENA (Zajuna).
   - Espacio correspondiente: **Módulos de *software* codificados y probados. GA7-220501096-AA2-EV02**.
