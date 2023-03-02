package com.bone.API.repository;
import com.bone.API.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
