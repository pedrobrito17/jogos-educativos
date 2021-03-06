package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.utils.Fonte;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;

/**
 *
 * @author Pedro Brito
 */
public class OpcoesQuiz extends JMenu{
    
    private JMenuItem adicionarFase, deletarFase, customizarQuiz;
    private OpcoesQuizInterface tabbedInterface;
    
    public OpcoesQuiz(String s) {
        super(s);
    }
    
    public OpcoesQuiz(Component parent){
        if(parent instanceof OpcoesQuizInterface){
            tabbedInterface = (OpcoesQuizInterface) parent;
        }else{
            throw new RuntimeException(parent.toString()
                    + " deve implementar onLoginCompletedListener");
        }
        configurarMenu();
    }
    
    private void configurarMenu() {
        configurarItemMenu();
        this.setFont(Fonte.retornarFontePadrao());
        this.add(adicionarFase);
        this.add(deletarFase);
        this.add(customizarQuiz);
    }

    private void configurarItemMenu() {
        adicionarFase = getItemMenu("Adicionar fase", this);
        deletarFase = getItemMenu("Deletar fase", this);
        customizarQuiz = getItemMenu("Customizar quiz", this);
    }
    
    private JMenuItem getItemMenu(String text, Component parent){
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(new OpcoesQuiz.MenuItemActionListener(parent));
        menuItem.setFont(Fonte.retornarFontePadrao());
        return menuItem;
    }
    
    private class MenuItemActionListener implements ActionListener {

        Component parent;

        private MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch(item.getActionCommand()){
                case "Adicionar fase":
                    tabbedInterface.adicionarFase();
                    break;
                case "Deletar fase":
                    tabbedInterface.deletarFase();
                    break;
                case "Customizar quiz":
                    tabbedInterface.personalizarQuiz();
                    break;
                default:
                    break;
            }
        }
    }

}
