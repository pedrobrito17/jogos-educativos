package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.filter.FiltroFileChooserAudio;
import br.com.ifma.view.components.filter.FiltroFileChooserImagem;
import br.com.ifma.view.components.filter.FiltroFileChooserVideo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro Brito
 */
public final class DialogMidia extends JFrame {

    private JLabel selecao, caminho;
    private final String TEXTO = "Selecione o tipo de mídia desejada para adicionar à pergunta da questão.";
    private final String URL = "img/select.png";
    private JRadioButton img, audio, video, link;
    private ButtonGroup group;
    private Botao btnCancelar, btnOk;
    private JPanel jpTexto, jpMidia, jpBotoes, jpTextMedia, jpCaminho;
    private JFileChooser fileChooser;

    public DialogMidia() throws HeadlessException {
        configText();
        configRadioButton();
        configJPTextMedia();
        configCaminho();
        configBotoes();
        inicializarFrame();
    }

    public void configText() {
        selecao = new JLabel(TEXTO, new ImageIcon(URL), JLabel.LEFT);
        selecao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));

        jpTexto = new JPanel(new FlowLayout());
        jpTexto.setBorder(new EmptyBorder(10, 10, 5, 10));
        jpTexto.add(selecao);
    }

    public void configRadioButton() {
        img = new JRadioButton("Imagem");
        img.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        img.setFocusPainted(false);
        img.setSelected(true);
        audio = new JRadioButton("Áudio");
        audio.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        audio.setFocusPainted(false);
        video = new JRadioButton("Vídeo");
        video.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        video.setFocusPainted(false);
        link = new JRadioButton("Link");
        link.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        link.setFocusPainted(false);

        group = new ButtonGroup();
        group.add(img);
        group.add(audio);
        group.add(video);
        group.add(link);

        jpMidia = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        jpMidia.add(img);
        jpMidia.add(audio);
        jpMidia.add(video);
        jpMidia.add(link);
    }

    public void configJPTextMedia() {
        jpTextMedia = new JPanel(new BorderLayout());
        jpTextMedia.add(jpTexto, BorderLayout.NORTH);
        jpTextMedia.add(jpMidia, BorderLayout.CENTER);
    }

    public void configCaminho() {
        caminho = new JLabel("http://www.youtube.com.br/teste");
        caminho.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));

        jpCaminho = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jpCaminho.setBorder(new EmptyBorder(10, 10, 5, 10));
        jpCaminho.add(caminho);
    }

    public void configBotoes() {
        btnCancelar = new Botao("Cancelar");
        btnCancelar.configurarBotao();
        btnCancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btnOk = new Botao("Ok");
        btnOk.configurarBotao();
        btnOk.addActionListener((ActionEvent e) -> {

            this.dispose();
            if (link.isSelected()) {
                new DialogLink();
            } else {
                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(getFiltroDoRadioButtonSelected());
                fileChooser.setAcceptAllFileFilterUsed(false);
                int a = fileChooser.showOpenDialog(this);
                if (a == 0) {
                    File file = fileChooser.getSelectedFile();
                    System.out.println(file.getPath());
                }
            }

        });

        jpBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotoes.add(btnCancelar);
        jpBotoes.add(btnOk);
    }

    public void inicializarFrame() {
        this.setSize(550, 180);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(jpTextMedia, BorderLayout.NORTH);
        this.add(jpCaminho, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public FileFilter getFiltroDoRadioButtonSelected() {
        if (img.isSelected()) {
            return new FiltroFileChooserImagem();
        } else if (audio.isSelected()) {
            return new FiltroFileChooserAudio();
        } else {
            return new FiltroFileChooserVideo();
        }
    }

}