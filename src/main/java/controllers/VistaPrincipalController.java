package controllers;

import com.example.preparcialventas.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VistaPrincipalController {
    @FXML
    private Button btnClientes;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnVentas;

    @FXML
    private AnchorPane panelContenido;


    @FXML
    private AnchorPane vistaEstatica;

    @FXML
    void onClientes() throws IOException {
        AnchorPane vistaClientes = FXMLLoader.load(getClass().getResource("/com/example/preparcialventas/RegistroDeClientes.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(vistaClientes);
    }
    @FXML
    void onProductos() throws IOException {
        AnchorPane vistaProductos = FXMLLoader.load(getClass().getResource("/com/example/preparcialventas/RegistroDeProductos.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(vistaProductos);

    }

    @FXML
    void onVentas() throws IOException {
        AnchorPane vistaVentas = FXMLLoader.load(
                getClass().getResource("/com/example/preparcialventas/RegistroDeVentas.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(vistaVentas);

    }
}
