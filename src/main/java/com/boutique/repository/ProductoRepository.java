package com.boutique.repository;

import com.boutique.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repositorio en memoria que gestiona el inventario editorial de Boutique Moderna.
 * Diseñado con el patrón Singleton para garantizar consistencia en la capa de datos.
 */
public class ProductoRepository {

    private static ProductoRepository instance;
    private final List<Producto> catalogo;

    private ProductoRepository() {
        this.catalogo = new ArrayList<>();
        inicializarCatalogo();
    }

    public static synchronized ProductoRepository getInstance() {
        if (instance == null) {
            instance = new ProductoRepository();
        }
        return instance;
    }

    private void inicializarCatalogo() {
        // Colección Quiet Luxury: Piezas de Autor estructuradas con estética minimalista

        // 1. HOMBRE - Blazer Estructurado de Lino y Lana
        catalogo.add(new Producto(
                1,
                "Blazer Estructurado de Lino Savile",
                "HOMBRE",
                "Silueta semi-desestructurada confeccionada en lino europeo con entretela ligera de crin de caballo. Solapa en muesca y acabados sastre a mano.",
                890000.0,
                "https://images.unsplash.com/photo-1507679799987-c73779587ccf?auto=format&fit=crop&w=800&q=80",
                "100% Lino Orgánico de Normandía",
                "38R", "40R", "42R", "44R"
        ));

        // 2. MUJER - Abrigo Minimalista de Lana Merino
        catalogo.add(new Producto(
                2,
                "Abrigo Minimalista de Lana Merino",
                "MUJER",
                "Corte envolvente de caída limpia sin botones visibles. Confeccionado en doble faz de lana merino con tacto cachemir y cinturón a juego.",
                1250000.0,
                "https://images.unsplash.com/photo-1539109136881-3be0616acf4b?auto=format&fit=crop&w=800&q=80",
                "100% Lana Merino Virgen de Primera Cosecha",
                "XS", "S", "M", "L"
        ));

        // 3. HOMBRE - Camisa Popelín de Algodón Egipcio
        catalogo.add(new Producto(
                3,
                "Camisa Popelín Cuello Francés",
                "HOMBRE",
                "Patronaje contemporáneo en popelín de hilo doble retorcido. Botones naturales de nácar pulido de la Polinesia y costuras inglesas ultrafinas.",
                420000.0,
                "https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?auto=format&fit=crop&w=800&q=80",
                "100% Algodón Egipcio Giza 45",
                "S", "M", "L", "XL"
        ));

        // 4. MUJER - Vestido Lencero de Seda Mulberry
        catalogo.add(new Producto(
                4,
                "Vestido Columna de Seda Mulberry",
                "MUJER",
                "Corte al bies que esculpe la silueta con delicadeza. Escote recto y tirantes finos en satén de seda pesada de grado 6A.",
                780000.0,
                "https://images.unsplash.com/photo-1595777457583-95e059d581b8?auto=format&fit=crop&w=800&q=80",
                "100% Seda Mulberry Orgánica 22 Momme",
                "XS", "S", "M", "L"
        ));

        // 5. HOMBRE - Pantalón Tailored con Pliegues
        catalogo.add(new Producto(
                5,
                "Pantalón Tailored de Tiro Alto",
                "HOMBRE",
                "Confección clásica con pinza invertida y ceñidores laterales ajustables. Caída recta impecable en sarga de lana fresca para cuatro estaciones.",
                540000.0,
                "https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?auto=format&fit=crop&w=800&q=80",
                "98% Lana Fina Fresca, 2% Elastano",
                "30", "32", "34", "36"
        ));

        // 6. MUJER - Chaqueta Trench Fluida Oversize
        catalogo.add(new Producto(
                6,
                "Trench Coat Fluido Oversize",
                "MUJER",
                "Reinterpretación sobria de la gabardina clásica. Hombro caído, manga raglán y tejido hidrófugo con brillo sutil mate.",
                1120000.0,
                "https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?auto=format&fit=crop&w=800&q=80",
                "70% Algodón Gabardina, 30% Cupro Reciclado",
                "S", "M", "L"
        ));

        // 7. HOMBRE - Suéter de Cuello Vuelto en Cachemira
        catalogo.add(new Producto(
                7,
                "Suéter Cuello Vuelto Mongolia",
                "HOMBRE",
                "Tejido de punto fino con galga 14 de incomparable suavidad térmica. Acabados acanalados en cuello, puños y bajo.",
                690000.0,
                "https://images.unsplash.com/photo-1617137984095-74e4e5e3613f?auto=format&fit=crop&w=800&q=80",
                "100% Cachemira Inner Mongolia",
                "S", "M", "L", "XL"
        ));

        // 8. MUJER - Pantalón Palazzo de Lino Crudo
        catalogo.add(new Producto(
                8,
                "Pantalón Palazzo de Lino Crudo",
                "MUJER",
                "Silueta amplia de corte palazzo con pretina alta estructurada. Bolsillos laterales discretos y caída etérea con movimiento natural.",
                510000.0,
                "https://images.unsplash.com/photo-1509631179647-0177331693ae?auto=format&fit=crop&w=800&q=80",
                "100% Lino Italiano de Hilado Artesanal",
                "XS", "S", "M", "L"
        ));
    }

    /**
     * Retorna todos los productos del catálogo.
     */
    public List<Producto> obtenerTodos() {
        return Collections.unmodifiableList(catalogo);
    }

    /**
     * Filtra los productos por categoría ("HOMBRE", "MUJER" o "TODOS").
     */
    public List<Producto> filtrarPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty() || "TODOS".equalsIgnoreCase(categoria.trim())) {
            return obtenerTodos();
        }
        String categoriaNormalizada = categoria.trim().toUpperCase();
        return catalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoriaNormalizada))
                .collect(Collectors.toList());
    }

    /**
     * Busca un producto por su identificador único.
     */
    public Optional<Producto> buscarPorId(int id) {
        return catalogo.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}
