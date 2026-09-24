package com.boutique.server;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.net.ServerSocket;

/**
 * Servidor Apache Tomcat Embebido (Jakarta EE 10 / Tomcat 10.1).
 * Permite ejecutar la aplicación completa en desarrollo local con un solo comando:
 *   mvn compile exec:java
 */
public class EmbeddedTomcatServer {

    private static final int DEFAULT_PORT = 8080;
    private static final int FALLBACK_PORT = 8085;

    public static void main(String[] args) throws Exception {
        int port = resolvePort();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector(); // Inicializa el conector HTTP

        // Directorio base para temporales de Tomcat
        File baseDir = new File("target/tomcat-embed");
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
        tomcat.setBaseDir(baseDir.getAbsolutePath());

        // Directorio webapp de la aplicación
        File webappDir = new File("src/main/webapp");
        Context context = tomcat.addWebapp("", webappDir.getAbsolutePath());

        // Configurar el ClassLoader padre para que Tomcat reconozca las dependencias y clases en exec:java
        context.setParentClassLoader(EmbeddedTomcatServer.class.getClassLoader());

        // Añadir las clases compiladas al classpath del contexto web
        File additionWebInfClasses = new File("target/classes");
        org.apache.catalina.WebResourceRoot resources = new org.apache.catalina.webresources.StandardRoot(context);
        resources.addPreResources(new org.apache.catalina.webresources.DirResourceSet(
                resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"
        ));
        context.setResources(resources);

        System.out.println("==========================================================");
        System.out.println("   BOUTIQUE MODERNA - SERVIDOR LOCAL EN EJECUCION");
        System.out.println("   Arquitectura: Jakarta EE 10 / Tomcat 10.1 Embebido");
        System.out.println("   Acceso Web: http://localhost:" + port + "/catalogo");
        System.out.println("   Catálogo:   http://localhost:" + port + "/catalogo");
        System.out.println("   Puerto activo: " + port);
        System.out.println("==========================================================");

        tomcat.start();
        tomcat.getServer().await();
    }

    private static int resolvePort() {
        String propPort = System.getProperty("port");
        if (propPort != null && !propPort.trim().isEmpty()) {
            try {
                return Integer.parseInt(propPort.trim());
            } catch (NumberFormatException ignored) {
            }
        }

        // Probar si el puerto 8080 está disponible
        if (isPortAvailable(DEFAULT_PORT)) {
            return DEFAULT_PORT;
        }

        // Si 8080 está en uso, usar 8085
        return FALLBACK_PORT;
    }

    private static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
