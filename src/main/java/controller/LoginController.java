package controller;

import com.mycompany.modelo.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class LoginController {

    private Cliente cliente;

    public boolean loginAuth() {
        
        boolean isValido = false;

        if (cliente.getEmail().equals("") || cliente.getSenha().equals("")) {

            JOptionPane.showMessageDialog(null, "Nada Digitado! ");

        } else {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cadastroelogin");
            EntityManager em = emf.createEntityManager();

            List<Cliente> login = em.createQuery("select c from Cliente c where c.email=:emailForm and c.senha=:senhaForm", Cliente.class)
                    .setParameter("senhaForm", cliente.getSenha())
                    .setParameter("emailForm", cliente.getEmail())
                    .getResultList();
            
            System.out.println(login);
            
            if (login.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dados Incorretos!");
                em.close();
            } else {
                JOptionPane.showMessageDialog(null, "Login Efetuado com Sucesso!");
                isValido = true;
            }
        }

        return isValido;
    }

    public LoginController(Cliente cliente) {
        this.cliente = cliente;
    }

}
