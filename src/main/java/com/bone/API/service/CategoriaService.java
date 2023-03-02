
package com.bone.API.service;

import com.bone.API.modelo.Categoria;
import com.bone.API.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repository;

    public List<Categoria>listarCategorias(){
        return repository.findAll();
    }
    
    public void guardarCategorias(Categoria producto){
        repository.save(producto);
    }
    
    public Categoria obtenerCategoriaPorId(Integer id){
        return repository.findById(id).get();
    }
    
    public void eliminarCategoria(Integer id) {
        repository.deleteById(id);
    }
}
