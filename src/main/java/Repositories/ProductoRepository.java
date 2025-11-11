package Repositories;

import objects.Producto;

import java.util.ArrayList;

public class ProductoRepository {
    private static ProductoRepository instance;
    private ArrayList<Producto> productos;
    private ProductoRepository() {
        productos = new ArrayList<>();
        showExamples();
    }
    public static ProductoRepository getInstance() {
        if (instance == null) {
            instance = new ProductoRepository();
        }
        return instance;
    }
    public void showExamples() {
        productos.add(new Producto("2008", "Coca-Cola", 3000, 20));
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }
}
