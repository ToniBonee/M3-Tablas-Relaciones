package com.bone.API.controller;

import com.bone.API.modelo.Categoria;
import com.bone.API.service.CategoriaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoriaController {
    
    private final CategoriaService categoriaService;
    @CrossOrigin (origins = "http://localhost")
    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Integer id) {
        try {
            Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/categorias")
    public void nuevoCategoria(@RequestBody Categoria categoria) {
        categoriaService.guardarCategorias(categoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<?> editarCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) {
        try {
            Categoria categoriaExistente = categoriaService.obtenerCategoriaPorId(id);
            categoriaExistente.setId(categoria.getId());
            categoriaExistente.setNombre(categoria.getNombre());
            categoriaExistente.setDescripción(categoria.getDescripción());
            categoriaService.guardarCategorias(categoriaExistente);
            return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/categorias/{id}")
    public void borrarCategoria (@PathVariable Integer id){
        categoriaService.eliminarCategoria(id);
    }
}

    

