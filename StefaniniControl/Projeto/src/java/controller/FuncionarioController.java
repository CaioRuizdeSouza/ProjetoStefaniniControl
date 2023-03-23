package controller;

import dao.Funcionario;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import bean.FuncionarioFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("funcionarioController")
@SessionScoped
public class FuncionarioController implements Serializable {

    @EJB
    private bean.FuncionarioFacade ejbFacade;
    private List<Funcionario> items = null;
    private Funcionario funcionario;

    public FuncionarioController() {
        prepararCriacao();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FuncionarioFacade getFacade() {
        return ejbFacade;
    }

    public Funcionario prepararCriacao() {
        funcionario = new Funcionario();
        initializeEmbeddableKey();
        return funcionario;
    }
    
    public Boolean validarFuncionario(Funcionario funcionario){
        
        Boolean validado;
        
        if(funcionario.getNome().length() > 2){
          String nome = funcionario.getNome();
                nome =  nome.replaceAll("[0-9]","");
                funcionario.setNome(nome);
                
        }else{
            JsfUtil.addErrorMessage("Erro: Nome precisa ter mais que 2 caracteres");
            validado = false;
            return validado;
        }
        
        if (funcionario.getEmail().length() > 1 &&
            funcionario.getEmail().contains("@stefanini.com") == true) {
            
            
        }else{
            JsfUtil.addErrorMessage("Erro: Email precisa que o dominio seja @stefanini.com");
            validado = false;
            return validado;
        }
        
        String cpf = funcionario.getCpf();
        cpf = cpf.replaceAll("[^0-9]","");
        funcionario.setCpf(cpf);
        
        if (funcionario.getCpf().length() != 11) {
            JsfUtil.addErrorMessage("Erro: Tamanho invalido do cpf");
            validado = false;
            return validado;
        }else{
             List<Funcionario> funcionarios = getItemsAvailableSelectMany();
             for (Funcionario f : funcionarios) {
                  if (!f.getId().equals(funcionario.getId())
             && f.getCpf().equals(funcionario.getCpf())) {
                      JsfUtil.addErrorMessage("Erro: CPF já cadastrado");
                      validado = false;
                      return validado;
                  }
             }
        }
        
        if (funcionario.getEmail().equals(funcionario.getEmail())){
            List<Funcionario> funcionarios = getItemsAvailableSelectMany();
            for (Funcionario f : funcionarios) {
                  if (!f.getId().equals(funcionario.getId())
             && f.getEmail().equals(funcionario.getEmail())) {
                      JsfUtil.addErrorMessage("Erro: Email já cadastrado");
                      validado = false;
                      return validado;
                  }
             }
        }
        

        
          
//        if(.contains("@") != true){
//            System.out.println("Email Invalido");
//       }
        validado = true;
        return validado;
                
    }
    

    public void criar() {
        if(validarFuncionario(funcionario)){
        persist(PersistAction.CREATE, "Funcionário Adicionado");
        prepararCriacao();
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void atualizar() {
        if(validarFuncionario(funcionario)){
        persist(PersistAction.UPDATE, "Funcionário Atualizado");
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
    }

    public void excluir() {
        persist(PersistAction.DELETE, "Funcionário Excluído");
        if (!JsfUtil.isValidationFailed()) {
            funcionario = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Funcionario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (funcionario != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(funcionario);
                } else {
                    getFacade().remove(funcionario);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Funcionario getFuncionario(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Funcionario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Funcionario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Funcionario.class)
    public static class FuncionarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FuncionarioController controller = (FuncionarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "funcionarioController");
            return controller.getFuncionario(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Funcionario) {
                Funcionario o = (Funcionario) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Funcionario.class.getName()});
                return null;
            }
        }

    }

}

