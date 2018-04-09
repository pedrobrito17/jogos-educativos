package br.com.ifma.view;

import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.Arquivo;
import br.com.ifma.view.components.menu.Opcoes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Pedro Brito
 */
public class JanelaPrincipal extends JFrame{
    
    private final JPanel jpanel;
    private final JMenuBar menuBar;
    private final Arquivo arquivo;
    private final Opcoes opcoes;
    private final Ajuda ajuda;
    
    public JanelaPrincipal(){
        menuBar = new JMenuBar();
        arquivo = new Arquivo("Arquivo");
        opcoes = new Opcoes("Opções");
        ajuda = new Ajuda("Ajuda");
        
        jpanel = new JPanel();
        
        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }
    
    private void configuracaoDoMenu(){
        menuBar.add(arquivo);
        menuBar.add(opcoes);
        menuBar.add(ajuda);
    }
    
    private void configuracaoDoJPanel(){
        ImageIcon imagemForca = createImageIcon("icones/img-main.png");
        jpanel.setLayout(new BorderLayout());
        jpanel.add(new JLabel(null, imagemForca, JLabel.LEFT), BorderLayout.CENTER);
    }
    
    private void inicializarFrame(){
        this.setSize(508, 505);
        this.setLayout(new GridLayout(1,1));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setJMenuBar(menuBar);
        this.add(jpanel);
        
        this.setVisible(true);
    }  
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = JanelaPrincipal.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Arquivo não encontrado: " + path);
            return null;
        }
    }

}
