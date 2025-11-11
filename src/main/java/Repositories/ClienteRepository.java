package Repositories;

import objects.Cliente;

import java.util.ArrayList;

public class ClienteRepository {
    private static ClienteRepository instance;
    private ArrayList<Cliente> clientes;
    private ClienteRepository() {
        clientes = new ArrayList<>();
        showExamples();
    }
    public static ClienteRepository getInstance() {
        if(instance == null){
            instance = new ClienteRepository();
        }
        return instance;
    }
    private void showExamples(){
        clientes.add(new Cliente("1092457866", "Daniela Vega", "320744675",
                "Soul777@gmail.com"));
    }
    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public void removeCliente(Cliente cliente){
        clientes.remove(cliente);
    }
}
