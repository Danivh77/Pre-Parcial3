package controllers;

import Repositories.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import objects.Cliente;

import java.util.Optional;

public class RegistroDeClientesController {
    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Cliente,String> colCorreo;

    @FXML
    private TableColumn<Cliente, String> colId;

    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTel;
    @FXML
    private TableColumn<Cliente, String> colTel;


    private AnchorPane panelContenido;
    private ClienteRepository clienteRepository;
    private ObservableList<Cliente> clientesObservable;
    @FXML
    public void initialize() {
        clienteRepository = ClienteRepository.getInstance();
        clientesObservable = FXCollections.observableArrayList();
        tablaClientes.setItems(clientesObservable);
        configurarTabla();
        cargarClientes();
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    boolean isSelected = newValue != null;
                    btnEliminar.setDisable(!isSelected);
                    btnModificar.setDisable(!isSelected);
                    mostrarCliente(newValue);
                    txtId.setDisable(isSelected);
                }
        );
    }
    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    }
    private void cargarClientes() {
       clientesObservable.clear();
       clientesObservable.addAll(clienteRepository.getClientes());
    }
    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtTel.clear();
        txtCorreo.clear();
        txtId.setDisable(false);
        tablaClientes.getSelectionModel().clearSelection();
    }


    @FXML
    void onAgregar() {
        if(!validarCampos()){
            return;
        } try{
            String cedula = txtId.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTel.getText();
            String correo = txtCorreo.getText();
            Cliente cliente= new Cliente(cedula, nombre, telefono, correo);
            clienteRepository.addCliente(cliente);
            mostrarAlerta("Éxito", "Cliente agregado correctamente.", Alert.AlertType.INFORMATION);
            actualizarTabla();
            limpiarCampos();
        }catch (Exception e) {
            mostrarAlerta("Error", "Error al agregar el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }

    }
    private boolean validarCampos() {
        if(txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtTel.getText().isEmpty()||
                txtCorreo.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben ser diligenciados obligatoriamente.", Alert.AlertType.WARNING);
            return false;
        } return true;
    }

    @FXML
    void onEliminar() {
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if(cliente == null){
            mostrarAlerta("Error", "Seleccione un cliente para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Estás seguro?");
        confirmacion.setContentText("¿Deseas eliminar el cliente: " + cliente.getNombre() + "?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if(resultado.isPresent() && resultado.get() == ButtonType.OK){
            try{
                clienteRepository.removeCliente(cliente);
                cargarClientes();
                mostrarAlerta("Exito", "cliente eliminado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } catch (Exception e) {
                mostrarAlerta("Exito", "Error al eliminar el Cliente: "+ e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    public void actualizarTabla() {
        cargarClientes();
    }
    private void mostrarCliente(Cliente cliente) {
        if(cliente != null){
            txtId.setText(cliente.getCedula());
            txtNombre.setText(cliente.getNombre());
            txtTel.setText(cliente.getTelefono());
            txtCorreo.setText(cliente.getCorreo());
            txtId.setDisable(true);
        } else {
            limpiarCampos();
            txtId.setDisable(false);
        }
    }

    @FXML
    void onModificar() {
        if(!validarCampos()){
            return;
        }
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if(cliente == null){
            mostrarAlerta("Error", "Seleccione un cliente para modificar.", Alert.AlertType.WARNING);
            return;
        }
        try{
            cliente.setNombre(txtNombre.getText());
            cliente.setTelefono(txtTel.getText());
            cliente.setCorreo(txtCorreo.getText());
            actualizarTabla();
            tablaClientes.refresh();
            mostrarAlerta("Éxito", "Cliente modificado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al modificar el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }


    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
