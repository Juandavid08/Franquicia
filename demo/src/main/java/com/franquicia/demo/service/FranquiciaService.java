package com.franquicia.demo.service;

import com.franquicia.demo.model.Franquicia;
import com.franquicia.demo.model.Producto;
import com.franquicia.demo.model.Sucursal;
import com.franquicia.demo.repository.FranquiciaRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FranquiciaService {
    private final FranquiciaRepository franquiciaRepository;

    public FranquiciaService(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

    public Franquicia agregarFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }
    public Franquicia agregarSucursal(String idFranquicia, Sucursal sucursal) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();

            if (sucursal.getId() == null || sucursal.getId().isEmpty()) {
                sucursal.setId(new ObjectId().toString());
            }

            if (franquicia.getSucursales() == null) {
                franquicia.setSucursales(new ArrayList<>());
            }
            franquicia.getSucursales().add(sucursal);
            return franquiciaRepository.save(franquicia);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Franquicia agregarProducto(String idFranquicia, String idSucursal, Producto producto) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            for (Sucursal sucursal : franquicia.getSucursales()) {
                if (sucursal.getId().equals(idSucursal)) {
                    if (producto.getId() == null || producto.getId().isEmpty()) {
                        producto.setId(new ObjectId().toString());
                    }
                    sucursal.getProductos().add(producto);
                    return franquiciaRepository.save(franquicia);
                }
            }
            throw new RuntimeException("Sucursal no encontrada con ID: " + idSucursal);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Franquicia eliminarProducto(String idFranquicia, String idSucursal, String idProducto) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            for (Sucursal sucursal : franquicia.getSucursales()) {
                if (sucursal.getId().equals(idSucursal)) {
                    sucursal.getProductos().removeIf(p -> p.getId().equals(idProducto));
                    return franquiciaRepository.save(franquicia);
                }
            }
            throw new RuntimeException("Sucursal no encontrada con ID: " + idSucursal);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Franquicia modificarStock(String idFranquicia, String idSucursal, String idProducto,
            Producto productoConNuevoStock) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            for (Sucursal sucursal : franquicia.getSucursales()) {
                if (sucursal.getId().equals(idSucursal)) {
                    for (Producto producto : sucursal.getProductos()) {
                        if (producto.getId().equals(idProducto)) {
                            producto.setStock(productoConNuevoStock.getStock());
                            return franquiciaRepository.save(franquicia);
                        }
                    }
                    throw new RuntimeException("Producto no encontrado con ID: " + idProducto);
                }
            }
            throw new RuntimeException("Sucursal no encontrada con ID: " + idSucursal);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Map<String, Producto> obtenerProductosConMasStock(String idFranquicia) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            Map<String, Producto> productosConMasStock = new HashMap<>();

            for (Sucursal sucursal : franquicia.getSucursales()) {
                Producto productoMaxStock = sucursal.getProductos().stream()
                        .max(Comparator.comparingInt(Producto::getStock))
                        .orElse(null);

                if (productoMaxStock != null) {
                    productosConMasStock.put(sucursal.getNombre(), productoMaxStock);
                }
            }
            return productosConMasStock;
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Franquicia actualizarNombreFranquicia(String idFranquicia, String nuevoNombre) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            franquicia.setNombre(nuevoNombre);
            return franquiciaRepository.save(franquicia);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Franquicia actualizarNombreSucursal(String idFranquicia, String idSucursal, String nuevoNombre) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            for (Sucursal sucursal : franquicia.getSucursales()) {
                if (sucursal.getId().equals(idSucursal)) {
                    sucursal.setNombre(nuevoNombre);
                    return franquiciaRepository.save(franquicia);
                }
            }
            throw new RuntimeException("Sucursal no encontrada con ID: " + idSucursal);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }

    public Franquicia actualizarNombreProducto(String idFranquicia, String idSucursal, String idProducto,
            String nuevoNombre) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(idFranquicia);
        if (franquiciaOpt.isPresent()) {
            Franquicia franquicia = franquiciaOpt.get();
            for (Sucursal sucursal : franquicia.getSucursales()) {
                if (sucursal.getId().equals(idSucursal)) {
                    for (Producto producto : sucursal.getProductos()) {
                        if (producto.getId().equals(idProducto)) {
                            producto.setNombre(nuevoNombre);
                            return franquiciaRepository.save(franquicia);
                        }
                    }
                }
            }
            throw new RuntimeException("Producto no encontrado con ID: " + idProducto);
        } else {
            throw new RuntimeException("Franquicia no encontrada con ID: " + idFranquicia);
        }
    }
}
