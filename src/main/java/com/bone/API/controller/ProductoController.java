package com.bone.API.controller;
import com.bone.API.modelo.Producto;
import com.bone.API.service.ProductoService;
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
public class ProductoController {
    
    private final ProductoService productoService;
    @CrossOrigin (origins = "http://localhost:8080")
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/productos")
    public void nuevoProducto(@RequestBody Producto producto) {
        productoService.guardarProductos(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto producto, @PathVariable Integer id) {
        try {
            Producto productoExistente = productoService.obtenerProductoPorId(id);
            productoExistente.setProducto(producto.getProducto());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setStock(producto.getStock());
            productoExistente.setImagenProducto(producto.getImagenProducto());
            productoExistente.setInfoProducto(producto.getInfoProducto());
            productoExistente.setId(producto.getId());
            productoService.guardarProductos(productoExistente);
            return new ResponseEntity<Producto>(producto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/productos/{id}")
    public void borrarProducto (@PathVariable Integer id){
        productoService.eliminarProductos(id);
    }
}
