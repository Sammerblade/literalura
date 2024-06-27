package com.alura.literalura.controller;

import com.alura.literalura.model.Libro;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsolaController implements CommandLineRunner {

    @Autowired
    private LibroService libroService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un año determinado");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Ingrese el título del libro:");
                    String titulo = scanner.nextLine();
                    Libro libro = libroService.buscarLibroPorTitulo(titulo);
                    if (libro == null) {
                        System.out.println("El libro no fue encontrado o ya está registrado.");
                    } else {
                        System.out.println("Libro registrado: " + libro.getTitulo());
                    }
                    break;
                case "2":
                    List<Libro> libros = libroService.listarLibros();
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        System.out.println("Libros registrados:");
                        for (Libro l : libros) {
                            System.out.println("Título: " + l.getTitulo() + ", Autor: " + l.getAutor().getNombre() + ", Idioma: " + l.getIdioma() + ", Descargas: " + l.getDescargas());
                        }
                    }
                    break;
                case "3":
                    List<String> autores = libroService.listarAutores();
                    if (autores.isEmpty()) {
                        System.out.println("No hay autores registrados.");
                    } else {
                        System.out.println("Autores registrados:");
                        for (String autor : autores) {
                            System.out.println("Autor: " + autor);
                        }
                    }
                    break;
                case "4":
                    System.out.println("Ingrese el año:");
                    int year = Integer.parseInt(scanner.nextLine());
                    // Implementa aquí la lógica para listar autores vivos en un año determinado
                    System.out.println("Funcionalidad no implementada aún.");
                    break;
                case "5":
                    System.out.println("Ingrese el código del idioma (ES, EN, FR, PT):");
                    String idioma = scanner.nextLine();
                    List<Libro> librosPorIdioma = libroService.listarLibrosPorIdioma(idioma);
                    if (librosPorIdioma.isEmpty()) {
                        System.out.println("No hay libros registrados en ese idioma.");
                    } else {
                        System.out.println("Libros en " + idioma + ":");
                        for (Libro l : librosPorIdioma) {
                            System.out.println("Título: " + l.getTitulo() + ", Autor: " + l.getAutor().getNombre() + ", Descargas: " + l.getDescargas());
                        }
                    }
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
