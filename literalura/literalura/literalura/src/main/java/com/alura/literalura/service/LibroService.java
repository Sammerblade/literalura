package com.alura.literalura.service;


import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final String GUTENDEX_API_URL = "https://gutendex.com/books?search=";

    public Libro buscarLibroPorTitulo(String titulo) {
        if (libroRepository.findByTitulo(titulo) != null) {
            return null; // Libro ya está en la base de datos
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = GUTENDEX_API_URL + titulo;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response == null || response.getResults().isEmpty()) {
            return null; // Libro no encontrado en la API
        }

        GutendexBook apiBook = response.getResults().get(0);
        Autor autor = new Autor();
        autor.setNombre(apiBook.getAuthors().get(0).getName());
        autor.setBirthYear(apiBook.getAuthors().get(0).getBirthYear());
        autor.setDeathYear(apiBook.getAuthors().get(0).getDeathYear());

        // Guarda el autor en la base de datos si no existe
        final Autor finalAutor = autor;
        Optional<Autor> autorExistente = autorRepository.findAll().stream()
                .filter(a -> a.getNombre().equals(finalAutor.getNombre()))
                .findFirst();
        if (autorExistente.isEmpty()) {
            autor = autorRepository.save(autor);
        } else {
            autor = autorExistente.get();
        }

        Libro libro = new Libro();
        libro.setTitulo(apiBook.getTitle());
        libro.setAutor(autor);
        libro.setIdioma(apiBook.getLanguages().get(0));
        libro.setDescargas(apiBook.getDownloadCount());

        return libroRepository.save(libro);
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public List<String> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .map(Autor::getNombre)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    // Implementa aquí la funcionalidad para listar autores vivos en un año determinado
}
