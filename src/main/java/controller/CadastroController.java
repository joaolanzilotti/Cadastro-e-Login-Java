package controller;

import GUI.CadastroGUI;
import GUI.LoginGUI;
import Utils.RegexUtils;
import com.mycompany.modelo.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class CadastroController {

    Cliente cliente;

    public boolean isCadastroAuth() {

        boolean isValido = false;

        CadastroGUI cadastrogui = new CadastroGUI();

        if (cliente.getEmail().equals("") || cliente.getSenha().equals("") || cliente.getNome().equals("") || cliente.getCpf().equals("")) {

            JOptionPane.showMessageDialog(null, "Campos Obrigatórios! ");

        } else if (!cliente.getEmail().isEmpty()) {
            boolean isEmailValido = RegexUtils.isEmail(cliente.getEmail());

            if (!isEmailValido) {
                JOptionPane.showMessageDialog(null, "Email Invalido!");
            }

            try {
                if (isEmailValido) {

                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("cadastroelogin");
                    EntityManager em = emf.createEntityManager();
                    List<Cliente> cadastroExistente = em.createQuery("select c from Cliente c where c.email=:emailForm", Cliente.class).setParameter("emailForm", cliente.getEmail()).getResultList();
                    List<Cliente> cpfExistente = em.createQuery("select c from Cliente c where c.cpf=:cpfForm", Cliente.class).setParameter("cpfForm", cliente.getCpf()).getResultList();
                    System.out.println(cadastroExistente);
                    System.out.println(cpfExistente);

                    if (!cadastroExistente.isEmpty() || !cpfExistente.isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Usuário Já Existente");
                        em.close();
                    } else {

                        em.getTransaction().begin();
                        em.persist(cliente);
                        em.getTransaction().commit();
                        JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
                        em.close();
                        isValido = true;
                    }
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Sem Conexào com o Servidor!");
            }
        }

        return isValido;

    }

    public CadastroController(Cliente cliente) {
        this.cliente = cliente;
    }

}
