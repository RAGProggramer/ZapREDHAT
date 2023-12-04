package visao;

import java.util.Timer;
import java.util.TimerTask;
import Conexao.conexao;
import dao.ConversaDAO;
import dao.MensagemDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import modal.Conversa;
import modal.Mensagem;
import java.util.*;

/**
 *
 * @author RAG
 */
public class TelaPrincipal extends javax.swing.JFrame {

    TelaCadastro TelaC = new TelaCadastro();
    private boolean cadastroAberto = false;
    Conversa mConversa = new Conversa();
    Mensagem mMenssagens = new Mensagem();
    ConversaDAO dConversa = new ConversaDAO();
    MensagemDAO dMensagens = new MensagemDAO();
    conexao conn = new conexao();
    int idUsuario;
    Map<String, String> usuarioNomes = new HashMap<>();

    int idConversa = 0;
    String loginUsuario, nomeUsuarioSelecionado, mensagem = "", mensagemNova;
    private int lastSelectedIndex = -1;

    /**
     * Creates new form TelaPrincipal
     *
     * @param usuario_id
     * @param login
     */
    public TelaPrincipal(int usuario_id, String login) {
        initComponents();
        try {
            adicionaBotoesUsuarios(usuario_id);
            idUsuario = usuario_id;
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.loginUsuario = login;
        ImageIcon icon = new ImageIcon("/storage/RAG/ZapREDHAT/src/IMG/2311531.png");
        java.awt.Image img = icon.getImage();
        jButtonMenu.setSize(30, 30);
        java.awt.Image newimg = img.getScaledInstance(jButtonMenu.getWidth(), jButtonMenu.getHeight(), java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        // Atribua o ícone ao botão
        jButtonMenu.setIcon(icon);
        jButtonMenu.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * This method is called from within the construcadastroAbertoctor to
     * initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItem = new javax.swing.JMenuItem();
        jMenuItemLogout = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanelConversa = new javax.swing.JPanel();
        jLabelMenssagem = new javax.swing.JLabel();
        jButtonMenu = new javax.swing.JButton();
        jButtonEnviar = new javax.swing.JButton();
        jTextFieldMensagens = new javax.swing.JTextField();

        jPopupMenu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenuPopupMenuWillBecomeVisible(evt);
            }
        });

        jMenuItem.setText("Cadastro");
        jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItem);

        jMenuItemLogout.setText("Logout");
        jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogoutActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItemLogout);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jPanelConversa.setBackground(new java.awt.Color(51, 51, 51));
        jPanelConversa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conversas", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Hack Nerd Font", 1, 24), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanelConversa.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanelConversaLayout = new javax.swing.GroupLayout(jPanelConversa);
        jPanelConversa.setLayout(jPanelConversaLayout);
        jPanelConversaLayout.setHorizontalGroup(
            jPanelConversaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jPanelConversaLayout.setVerticalGroup(
            jPanelConversaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jLabelMenssagem.setToolTipText("");
        jLabelMenssagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Hack Nerd Font", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jLabelMenssagem.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jLabelMenssagemAncestorResized(evt);
            }
        });

        jButtonMenu.setForeground(new java.awt.Color(51, 51, 51));
        jButtonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuActionPerformed(evt);
            }
        });

        jButtonEnviar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jTextFieldMensagens.setBackground(new java.awt.Color(51, 51, 51));
        jTextFieldMensagens.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldMensagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMensagensActionPerformed(evt);
            }
        });
        jTextFieldMensagens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldMensagensKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 315, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMenssagem, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldMensagens, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelConversa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(746, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMenssagem, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMensagens, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelConversa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1054, 601));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldMensagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMensagensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMensagensActionPerformed

    private void jTextFieldMensagensKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMensagensKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { // Correção aqui
            jButtonEnviarActionPerformed(null);
        }
    }//GEN-LAST:event_jTextFieldMensagensKeyPressed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        mandarMensagem();

    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuActionPerformed
        jPopupMenu.show(jButtonMenu, 0, jButtonMenu.getHeight());

    }//GEN-LAST:event_jButtonMenuActionPerformed

    private void jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemActionPerformed
        if (!cadastroAberto) {
            TelaC = new TelaCadastro();
            TelaC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            TelaC.setVisible(true);
            cadastroAberto = true;

            // Adicione um listener para detectar o fechamento da janela de cadastro
            TelaC.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    cadastroAberto = false;
                }
            });
        } else {
            // Se a janela já estiver aberta, traga-a para o topo
            TelaC.toFront();
        }
    }//GEN-LAST:event_jMenuItemActionPerformed

    private void jPopupMenuPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenuPopupMenuWillBecomeVisible
        jMenuItem = new javax.swing.JMenuItem();

    }//GEN-LAST:event_jPopupMenuPopupMenuWillBecomeVisible

    private void jMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogoutActionPerformed
        TelaLogin t = new TelaLogin();
        t.setVisible(true);
        dispose();

    }//GEN-LAST:event_jMenuItemLogoutActionPerformed

    private void jLabelMenssagemAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabelMenssagemAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelMenssagemAncestorResized

    public void atualizaMensagens() throws SQLException {
        trataConversa(mensagem);
    }

    private void trataConversa(String conversa) {
        List<String> mensagens = extrairMensagem(conversa);
        usuarioNomes.put("1", "<html><b>" + loginUsuario + "</b>");
        usuarioNomes.put("2", "<html><b>" + nomeUsuarioSelecionado + "</b>");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat titleFormat = new SimpleDateFormat("d 'de' MMMM", new Locale("pt", "BR"));

        JPanel panelMensagens = new JPanel();
        panelMensagens.setLayout(new BoxLayout(panelMensagens, BoxLayout.Y_AXIS));

        if (mensagens.isEmpty()) {
            jLabelMenssagem.removeAll();
            jLabelMenssagem.setLayout(new BorderLayout());
            JLabel labelVazio = new JLabel("A conversa está vazia.");
            labelVazio.setHorizontalAlignment(SwingConstants.CENTER);
            jLabelMenssagem.add(labelVazio, BorderLayout.CENTER);
            revalidate(); // Atualiza o layout da janela
            repaint(); // Repinta a janela
            setVisible(true);
        } else {
            String dataAnterior = null;
            for (String mensagem : mensagens) {
                String dataMensagem = extrairDataMensagem(mensagem);
                try {
                    Date dateM = dateFormat.parse(dataMensagem);
                    String tituloDia = titleFormat.format(dateM);
                    String horaMinutos = new SimpleDateFormat("HH:mm").format(dateM); // Formata apenas a hora e os minutos

                    if (!Objects.equals(dataAnterior, tituloDia)) {
                        JLabel labelTitulo = new JLabel("<html><div style='text-align: center; font-weight: bold;'>" + tituloDia + "</div></html>");
                        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER); // Configura o alinhamento horizontal para centralizar
                        panelMensagens.add(labelTitulo);

                        dataAnterior = tituloDia;
                    }

                    String nomeRemetente = extrairNomeRemetente(mensagem);
                    String soMensagens = extrairMensagens(mensagem);
                    String mensagemFormatada = mensagem;
                    if (nomeRemetente != null) {
                        String cssClass = nomeRemetente.equals(loginUsuario) ? "user-1" : (nomeRemetente.equals(nomeUsuarioSelecionado) ? "user-2" : "");
                        String cssStyle = "<style>"
                                + ".user-1 { text-align: right; }"
                                + ".user-2 { text-align: left; }"
                                + ".chat-message { display: flex; flex-direction: column; margin: 8px; }"
                                + ".chat-name { font-weight: bold; margin-bottom: 4px; }"
                                + ".chat-text { background-color: #DCF8C6; padding: 8px; border-radius: 8px; margin-bottom: 4px; width: 200px; overflow-y: auto; }"
                                + ".chat-time { font-size: 10px; color: #999; }"
                                + "</style>";

                        // Adicione o estilo CSS ao seu JLabel
                        mensagemFormatada = "<html>" + cssStyle + "<div class='chat-message " + cssClass + "'>"
                                + "<div class='chat-name'>" + nomeRemetente + "</div>"
                                + "<div class='chat-text'>" + soMensagens + "</div>"
                                + "<div class='chat-time'>" + horaMinutos + "</div>"
                                + "</div></html>";
                    }
                    JLabel label = new JLabel(mensagemFormatada);
                    label.setBackground(Color.DARK_GRAY);
                    label.setOpaque(true);

                    if (nomeRemetente != null && nomeRemetente.equals(usuarioNomes.get("1"))) {
                        label.setHorizontalAlignment(SwingConstants.RIGHT);
                        label.setForeground(Color.DARK_GRAY);
                    } else if (nomeRemetente != null) {
                        label.setHorizontalAlignment(SwingConstants.LEFT);
                        label.setForeground(Color.DARK_GRAY);
                    }
                    panelMensagens.add(label);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            JScrollPane scrollPane = new JScrollPane(panelMensagens);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            jLabelMenssagem.removeAll();
            jLabelMenssagem.setLayout(new BorderLayout());
            jLabelMenssagem.add(scrollPane, BorderLayout.CENTER);

            // Deixa o ScrollBar no máximo para baixo
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                    scrollPane.revalidate();
                    scrollPane.repaint();
                }
            });
            // FIM
            revalidate(); // Atualiza o layout da janela
            repaint(); // Repinta a janela
            setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        atualizaMensagens();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            timer.schedule(task, 0, 5000);
        }

    }

    private void adicionaBotoesUsuarios(int idUsuario) throws IOException {
        // Configura o layout vertical.
        jPanelConversa.setLayout(new BoxLayout(jPanelConversa, BoxLayout.Y_AXIS));

        List<JButton> buttons = new ArrayList<>();
        conn.executaSQL("SELECT * FROM `Usuarios` WHERE usuario_id <> " + idUsuario);

        try {
            while (conn.rs.next()) {

                String nome = conn.rs.getString("login");
                String id = conn.rs.getString("Usuario_id");
                String nomeTotal = id + " - " + nome;
                BufferedImage imagem = null;

                if (conn.rs.getBytes("imagemPerfil") != null && conn.rs.getBytes("imagemPerfil").length > 0) {
                    byte[] imagemBytes = conn.rs.getBytes("imagemPerfil");
                    ByteArrayInputStream bais = new ByteArrayInputStream(imagemBytes);
                    imagem = ImageIO.read(bais);
                }

                // Cria o botão
                JButton button = new JButton(nomeTotal);
                button.setPreferredSize(new Dimension(200, 80));

                // Configura o estilo do botão
                button.setBackground(new Color(51, 255, 0)); // Verde do WhatsApp
                button.setForeground(Color.BLACK);

                // Fonte do texto
                Font font = new Font("Arial", Font.BOLD, 16);
                button.setFont(font);

                // Borda arredondada
                button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                button.setFocusPainted(false); // Remove o contorno de foco
                // Define um ícone da imagem do usuário (se disponível)
                if (imagem != null) {
                    Image scaledImage = imagem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(scaledImage));
                }

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        String buttonText = clickedButton.getText();
                        String[] partes = buttonText.split("-");

                        if (partes.length >= 2) {
                            String nome = partes[1].trim();
                            jLabelMenssagem.setText(buttonText);
                            nomeUsuarioSelecionado = nome;
                            conversa(buttonText, String.valueOf(idUsuario));
                        }
                    }
                });

                buttons.add(button);
                usuarioNomes.put(id, nome);
            }
            jLabelMenssagem.setText(buttons.get(0).getText());
            for (JButton button : buttons) {
                jPanelConversa.add(button);
            }

            jPanelConversa.setVisible(true);
            jPanelConversa.revalidate(); // Use revalidate() em vez de repaint() para atualizar o layout.
            conn.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void conversa(String usuario, String idUsuario) {
        mensagem = "";
        String[] partes = usuario.split("-");
        String idUsuarioSelecionado = partes[0].trim();

        conn.executaSQL("SELECT * FROM conversas WHERE (usuario1_id = " + idUsuario
                + " AND usuario2_id = " + idUsuarioSelecionado + ") OR (usuario1_id = " + idUsuarioSelecionado
                + " AND usuario2_id = " + idUsuario + ");");

        try {
            if (conn.rs.next()) {
                idConversa = conn.rs.getInt("CONVERSA_ID");

                mConversa = dConversa.getConversasById(idConversa);

                conn.executaSQL("SELECT * FROM mensagens WHERE CONVERSA_ID = " + idConversa + ";");

                while (conn.rs.next()) {
                    mensagem = mensagem + conn.rs.getString("conteudo") + " " + conn.rs.getDate("data") + " " + conn.rs.getTime("hora") + "\n";

                }
                trataConversa(mensagem);
            } else {
                Conversa mConversa = new Conversa();
                mConversa.setUsuario1_id(Integer.parseInt(idUsuario));
                mConversa.setUsuario2_id(Integer.parseInt(idUsuarioSelecionado));
                mConversa.setApelido(loginUsuario);

                dConversa.createConversa(mConversa);

                // Now retrieve the newly created conversation ID
                conn.executaSQL("SELECT CONVERSA_ID FROM Conversas WHERE usuario1_id = " + idUsuario
                        + " AND usuario2_id = " + idUsuarioSelecionado + ";");

                if (conn.rs.next()) {
                    idConversa = conn.rs.getInt("CONVERSA_ID");
                }

                trataConversa(mensagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String extrairMensagens(String conversa) {
        StringBuilder mensagens = new StringBuilder();

        Pattern pattern = Pattern.compile("<b>.*?</b>:<br>(.*?)<br>\\(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(conversa);

        while (matcher.find()) {
            String mensagem = matcher.group(1).trim();
            mensagens.append(mensagem).append("\n"); // Adiciona a mensagem ao StringBuilder
        }

        return mensagens.toString();
    }

    private String extrairNomeRemetente(String mensagem) {
        // Use uma expressão regular para extrair o nome do remetente
        // A expressão regular assume que o nome do remetente é seguido por ":" na mensagem.
        Pattern pattern = Pattern.compile("<b>(.*?)</b>:");
        Matcher matcher = pattern.matcher(mensagem);

        if (matcher.find()) {
            // O grupo 1 da expressão regular contém o nome do remetente
            return "<html><b>" + matcher.group(1).trim() + "</b>";
        }

        // Retorna null se não for possível extrair o nome do remetente.
        return null;
    }

    private String extrairDataMensagem(String mensagem) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(mensagem);

        if (matcher.find()) {
            // O grupo 1 da expressão regular contém a data
            return matcher.group(1);

        } else {
            System.out.println("Data não encontrada na mensagem.");
        }
        return null;
    }

    private List<String> extrairMensagem(String conversa) {
        List<String> mensagens = new ArrayList<>();

        // Use uma expressão regular para encontrar mensagens na string com nomes de remetentes e timestamps
        Pattern pattern = Pattern.compile("([A-Za-z ]+): (.*?)(?: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}))?$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(conversa);

        while (matcher.find()) {
            String nomeRemetente = matcher.group(1);
            String mensagem = matcher.group(2);
            String data = matcher.group(3);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("d 'de' MMMM", new Locale("pt", "BR"));

            // Substitua o nome do remetente pelo nome do usuário correspondente
            if (usuarioNomes.containsKey(nomeRemetente)) {

                mensagem = mensagem.replace(nomeRemetente + ":", usuarioNomes.get(nomeRemetente) + ":");
            }

            mensagens.add("<html><b>" + nomeRemetente + "</b>:<br>" + mensagem + "<br>(" + data + ")");

        }
        return mensagens;
    }

    private void mandarMensagem() {
        if (jTextFieldMensagens.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo mensagem não pode ser em branco");
        } else {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

            String dataFormatada = agora.format(formatoData);
            String horaFormatada = agora.format(formatoHora);
            String dataHoraFormatada = dataFormatada + " " + horaFormatada;

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                java.util.Date dataParseada = formato.parse(dataHoraFormatada);
                Time hora = new Time(dataParseada.getTime());

                mMenssagens = new Mensagem();
                mMenssagens.setConteudo(loginUsuario + ": " + jTextFieldMensagens.getText());
                mMenssagens.setHora(hora);
                mMenssagens.setData(new java.util.Date());
                mConversa.setApelido(loginUsuario);
                mMenssagens.setRemetente_id(idUsuario);
                mMenssagens.setConversa_id(mConversa.getConversa_id());

                try {
                    dMensagens.createMensagem(mMenssagens);
                    mensagemNova = mMenssagens.getConteudo() + " " + dataHoraFormatada + "\n";
                    mensagem += mensagemNova;
                    trataConversa(mensagem);

                    jTextFieldMensagens.setText("");  // Define o texto como vazio após recuperar o conteúdo

                    // Agora você pode usar 'dataFormatada', 'hora', e 'conteudo' conforme necessário.
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JLabel jLabelMenssagem;
    private javax.swing.JMenuItem jMenuItem;
    private javax.swing.JMenuItem jMenuItemLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelConversa;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JTextField jTextFieldMensagens;
    // End of variables declaration//GEN-END:variables

}
