package com.franquicia.demo.controller;

import com.franquicia.demo.model.Franquicia;
import com.franquicia.demo.model.Producto;
import com.franquicia.demo.model.Sucursal;
import com.franquicia.demo.service.FranquiciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {
    private final FranquiciaService franquiciaService;

    public FranquiciaController(FranquiciaService franquiciaService) {
        this.franquiciaService = franquiciaService;
    }

    // agrega nuevas franquicias
    @PostMapping
    public ResponseEntity<Franquicia> agregarFranquicia(@RequestBody Franquicia franquicia) {
        return ResponseEntity.ok(franquiciaService.agregarFranquicia(franquicia));
    }

    // agrega neuvas sucursales
    @PostMapping("/{idFranquicia}/sucursales")
    public ResponseEntity<Franquicia> agregarSucursal(
            @PathVariable String idFranquicia,
            @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(franquiciaService.agregarSucursal(idFranquicia, sucursal));
    }

    // agrega un producto a una sucursal
    @PostMapping("/{idFranquicia}/sucursales/{idSucursal}/productos")
    public ResponseEntity<Franquicia> agregarProducto(
            @PathVariable String idFranquicia,
            @PathVariable String idSucursal,
            @RequestBody Producto producto) {
        return ResponseEntity.ok(franquiciaService.agregarProducto(idFranquicia, idSucursal, producto));
    }

    //  elimina un producto de una sucursal
    @DeleteMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}")
    public ResponseEntity<Franquicia> eliminarProducto(
            @PathVariable String idFranquicia,
            @PathVariable String idSucursal,
            @PathVariable String idProducto) {
        return ResponseEntity.ok(franquiciaService.eliminarProducto(idFranquicia, idSucursal, idProducto));
    }

    // modifica el stock de un producto
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/stock")
    public ResponseEntity<Franquicia> modificarStock(
            @PathVariable String idFranquicia,
            @PathVariable String idSucursal,
            @PathVariable String idProducto,
            @RequestBody Producto stock) {
        return ResponseEntity.ok(franquiciaService.modificarStock(idFranquicia, idSucursal, idProducto, stock));
    }

    // obtiene el producto con más stock por sucursal para una franquicia
    @GetMapping("/{idFranquicia}/productos/max-stock")
    public ResponseEntity<Map<String, Producto>> obtenerProductosConMasStock(@PathVariable String idFranquicia) {
        Map<String, Producto> productosConMasStock = franquiciaService.obtenerProductosConMasStock(idFranquicia);
        return ResponseEntity.ok(productosConMasStock);
    }

    // actualiza el nombre de una franquicia
    @PutMapping("/{idFranquicia}")
    public ResponseEntity<Franquicia> actualizarNombreFranquicia(
            @PathVariable String idFranquicia,
            @RequestBody Map<String, String> body) {
        String nuevoNombre = body.get("nuevoNombre");
        return ResponseEntity.ok(franquiciaService.actualizarNombreFranquicia(idFranquicia, nuevoNombre));
    }

    // actualiza el nombre de una sucursal
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}")
    public ResponseEntity<Franquicia> actualizarNombreSucursal(
            @PathVariable String idFranquicia,
            @PathVariable String idSucursal,
            @RequestBody Map<String, String> body) {
        String nuevoNombreSucursal = body.get("nuevoNombre");
        return ResponseEntity
                .ok(franquiciaService.actualizarNombreSucursal(idFranquicia, idSucursal, nuevoNombreSucursal));
    }

    // actualiza el nombre de un producto
    @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}")
    public ResponseEntity<Franquicia> actualizarNombreProducto(
            @PathVariable String idFranquicia,
            @PathVariable String idSucursal,
            @PathVariable String idProducto,
            @RequestBody Map<String, String> body) {
        String nuevoNombreProducto = body.get("nuevoNombre");
        return ResponseEntity.ok(
                franquiciaService.actualizarNombreProducto(idFranquicia, idSucursal, idProducto, nuevoNombreProducto));
    }

}
