package visao; // Declaração do pacote "visao"

import java.util.Timer; // Importa a classe Timer do pacote java.util
import java.util.TimerTask; // Importa a classe TimerTask do pacote java.util
import Conexao.conexao; // Importa a classe 'conexao' do pacote 'Conexao'
import dao.ConversaDAO; // Importa a classe ConversaDAO do pacote dao
import dao.MensagemDAO; // Importa a classe MensagemDAO do pacote dao
import java.awt.BorderLayout; // Importa a classe BorderLayout do pacote java.awt
import java.awt.Color; // Importa a classe Color do pacote java.awt
import java.awt.Dimension; // Importa a classe Dimension do pacote java.awt
import java.awt.Font; // Importa a classe Font do pacote java.awt
import java.awt.Image; // Importa a classe Image do pacote java.awt
import java.awt.event.ActionEvent; // Importa a classe ActionEvent do pacote java.awt.event
import java.awt.event.ActionListener; // Importa a classe ActionListener do pacote java.awt.event
import java.awt.event.KeyEvent; // Importa a classe KeyEvent do pacote java.awt.event
import java.awt.image.BufferedImage; // Importa a classe BufferedImage do pacote java.awt.image
import java.io.ByteArrayInputStream; // Importa a classe ByteArrayInputStream
import java.io.IOException; // Importa a classe IOException
import java.sql.SQLException; // Importa a classe SQLException do pacote java.sql
import java.sql.Time; // Importa a classe Time do pacote java.sql
import java.text.ParseException; // Importa a classe ParseException
import java.text.SimpleDateFormat; // Importa a classe SimpleDateFormat do pacote java.text
import java.time.LocalDateTime; // Importa a classe LocalDateTime do pacote java.time
import java.time.format.DateTimeFormatter; // Importa a classe DateTimeFormatter do pacote java.time.format
import java.util.ArrayList; // Importa a classe ArrayList do pacote java.util
import java.util.HashMap; // Importa a classe HashMap do pacote java.util
import java.util.List; // Importa a classe List do pacote java.util
import java.util.Locale; // Importa a classe Locale do pacote java.util
import java.util.Map; // Importa a classe Map do pacote java.util
import java.util.Objects; // Importa a classe Objects do pacote java.util
import java.util.logging.Level; // Importa a classe Level do pacote java.util.logging
import java.util.logging.Logger; // Importa a classe Logger do pacote java.util.logging
import java.util.regex.Matcher; // Importa a classe Matcher do pacote java.util.regex
import java.util.regex.Pattern; // Importa a classe Pattern do pacote java.util.regex
import javax.imageio.ImageIO; // Importa a classe ImageIO do pacote javax.imageio
import javax.swing.BorderFactory; // Importa a classe BorderFactory do pacote javax.swing
import javax.swing.BoxLayout; // Importa a classe BoxLayout do pacote javax.swing
import javax.swing.ImageIcon; // Importa a classe ImageIcon do pacote javax.swing
import javax.swing.JButton; // Importa a classe JButton do pacote javax.swing
import javax.swing.JFrame; // Importa a classe JFrame do pacote javax.swing
import javax.swing.JLabel; // Importa a classe JLabel do pacote javax.swing
import javax.swing.JOptionPane; // Importa a classe JOptionPane do pacote javax.swing
import javax.swing.JPanel; // Importa a classe JPanel do pacote javax.swing
import javax.swing.JScrollBar; // Importa a classe JScrollBar do pacote javax.swing
import javax.swing.JScrollPane; // Importa a classe JScrollPane do pacote javax.swing
import javax.swing.SwingConstants; // Importa a classe SwingConstants do pacote javax.swing
import javax.swing.SwingUtilities; // Importa a classe SwingUtilities do pacote javax.swing
import modal.Conversa; // Importa a classe Conversa do pacote modal
import modal.Mensagem; // Importa a classe Mensagem do pacote modal
import java.util.*; // Importa todas as classes do pacote java.util

/**
 * Classe que representa a Tela Principal da aplicação.
 *
 * @author RAG
 */
public class TelaPrincipal extends javax.swing.JFrame {

    TelaCadastro TelaC = new TelaCadastro(); // Cria uma instância da classe TelaCadastro
    private boolean cadastroAberto = false; // Variável que controla se a janela de cadastro está aberta
    Conversa mConversa = new Conversa(); // Cria uma instância da classe Conversa
    Mensagem mMenssagens = new Mensagem(); // Cria uma instância da classe Mensagem
    ConversaDAO dConversa = new ConversaDAO(); // Cria uma instância da classe ConversaDAO
    MensagemDAO dMensagens = new MensagemDAO(); // Cria uma instância da classe MensagemDAO
    conexao conn = new conexao(); // Cria uma instância da classe conexao

    Map<String, String> usuarioNomes = new HashMap<>(); // Mapa para armazenar os nomes dos usuários
    int idConversa = 0, idUsuario; // Variável para armazenar o ID da conversa, Variável para armazenar o ID do usuário
    String loginUsuario, nomeUsuarioSelecionado, mensagem = "", mensagemNova; // Variáveis para armazenar informações sobre a mensagem
    private int lastSelectedIndex = -1; // Índice do último item selecionado

    /**
     * Construtor da classe TelaPrincipal.
     *
     * @param usuario_id ID do usuário
     * @param login Login do usuário
     */
    public TelaPrincipal(int usuario_id, String login) {
        initComponents(); // Inicializa os componentes da interface gráfica
        try {
            adicionaBotoesUsuarios(usuario_id); // Adiciona botões de usuários à interface
            idUsuario = usuario_id; // Atribui o ID do usuário à variável
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.loginUsuario = login; // Atribui o login do usuário à variável
        ImageIcon icon = new ImageIcon("/storage/RAG/ZapREDHAT/lib/IMG/2311531.png"); // Cria um ícone a partir de uma imagem
        java.awt.Image img = icon.getImage(); // Obtém a imagem do ícone
        jButtonMenu.setSize(30, 30); // Define o tamanho do botão do menu
        java.awt.Image newimg = img.getScaledInstance(jButtonMenu.getWidth(), jButtonMenu.getHeight(), java.awt.Image.SCALE_SMOOTH); // Redimensiona a imagem
        icon = new ImageIcon(newimg); // Cria um novo ícone com a imagem redimensionada
        jButtonMenu.setIcon(icon); // Define o ícone do botão
        jButtonMenu.repaint(); // Redesenha o botão
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento da janela
    }

    /**
     * Método chamado quando o usuário pressiona Enter no campo de mensagens.
     *
     * @param evt Evento de ação
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
                .addGap(315, 315, 315)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMenssagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldMensagens, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldMensagens))
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
    /**
     * Método chamado quando uma tecla é pressionada no campo de mensagens.
     *
     * @param evt Evento de tecla
     */
    private void jTextFieldMensagensKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMensagensKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { // Correção aqui
            jButtonEnviarActionPerformed(null); // Chama o método de ação do botão de enviar
        }
    }//GEN-LAST:event_jTextFieldMensagensKeyPressed
    /**
     * Método chamado quando o botão de enviar é pressionado.
     *
     * @param evt Evento de ação
     */
    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        mandarMensagem(); // Chama o método para enviar a mensagem

    }//GEN-LAST:event_jButtonEnviarActionPerformed
    /**
     * Método chamado quando o botão de menu é pressionado.
     *
     * @param evt Evento de ação
     */
    private void jButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuActionPerformed
        jPopupMenu.show(jButtonMenu, 0, jButtonMenu.getHeight()); // Exibe o menu pop-up no botão de menu
    }//GEN-LAST:event_jButtonMenuActionPerformed

    /**
     * Método chamado quando um item do menu pop-up é selecionado.
     *
     * @param evt Evento de ação
     */
    private void jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemActionPerformed
        if (!cadastroAberto) {
            TelaC = new TelaCadastro(); // Cria uma nova instância da tela de cadastro
            TelaC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento de fechamento da janela de cadastro
            TelaC.setVisible(true); // Torna a janela de cadastro visível
            cadastroAberto = true; // Define a variável que controla se a janela de cadastro está aberta para verdadeiro

            // Adicione um listener para detectar o fechamento da janela de cadastro
            TelaC.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    cadastroAberto = false; // Define a variável que controla se a janela de cadastro está aberta para falso ao fechar
                }
            });
        } else {
            // Se a janela já estiver aberta, traga-a para o topo
            TelaC.toFront();
        }
    }//GEN-LAST:event_jMenuItemActionPerformed
    /**
     * Método chamado quando o menu pop-up está prestes a se tornar visível.
     *
     * @param evt Evento de menu pop-up
     */
    private void jPopupMenuPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenuPopupMenuWillBecomeVisible
        jMenuItem = new javax.swing.JMenuItem(); // Cria uma nova instância de JMenuItem

    }//GEN-LAST:event_jPopupMenuPopupMenuWillBecomeVisible
    /**
     * Método chamado quando o item de logout é selecionado no menu pop-up.
     *
     * @param evt Evento de ação
     */
    private void jMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogoutActionPerformed
        TelaLogin t = new TelaLogin(); // Cria uma nova instância da tela de login
        t.setVisible(true); // Torna a tela de login visível
        dispose(); // Fecha a tela principal

    }//GEN-LAST:event_jMenuItemLogoutActionPerformed
    /**
     * Método chamado quando o tamanho do componente da mensagem é alterado.
     *
     * @param evt Evento de hierarquia
     */
    private void jLabelMenssagemAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabelMenssagemAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelMenssagemAncestorResized
    /**
     * Método que atualiza as mensagens na interface.
     *
     * @throws SQLException Exceção de SQL
     */
    public void atualizaMensagens() throws SQLException {

    }

    /**
     * Método que trata a exibição da conversa na interface.
     *
     * @param conversa String representando a conversa
     */
    private void trataConversa(String conversa) {
        List<String> mensagens = extrairMensagem(conversa); // Extrai as mensagens da conversa
        usuarioNomes.put("1", "<html><b>" + loginUsuario + "</b>"); // Adiciona o nome do usuário atual ao mapa
        usuarioNomes.put("2", "<html><b>" + nomeUsuarioSelecionado + "</b>"); // Adiciona o nome do usuário selecionado ao mapa

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Formato para data e hora
        SimpleDateFormat titleFormat = new SimpleDateFormat("d 'de' MMMM", new Locale("pt", "BR")); // Formato para o título

        JPanel panelMensagens = new JPanel(); // Cria um painel para exibir as mensagens
        panelMensagens.setLayout(new BoxLayout(panelMensagens, BoxLayout.Y_AXIS)); // Configura o layout vertical

        if (mensagens.isEmpty()) { // Se não houver mensagens
            jLabelMenssagem.removeAll(); // Remove todos os componentes do rótulo de mensagens
            jLabelMenssagem.setLayout(new BorderLayout()); // Define o layout como BorderLayout
            JLabel labelVazio = new JLabel("A conversa está vazia."); // Cria um rótulo indicando que a conversa está vazia
            labelVazio.setHorizontalAlignment(SwingConstants.CENTER); // Alinha o rótulo no centro
            jLabelMenssagem.add(labelVazio, BorderLayout.CENTER); // Adiciona o rótulo ao rótulo de mensagens
            revalidate(); // Atualiza o layout da janela
            repaint(); // Repinta a janela
            setVisible(true); // Torna a janela visível
        } else {
            String dataAnterior = null; // Variável para armazenar a data da última mensagem
            for (String mensagem : mensagens) { // Itera sobre as mensagens
                String dataMensagem = extrairDataMensagem(mensagem); // Extrai a data da mensagem
                try {
                    Date dateM = dateFormat.parse(dataMensagem); // Converte a data para o formato Date
                    String tituloDia = titleFormat.format(dateM); // Formata a data para o título do dia
                    String horaMinutos = new SimpleDateFormat("HH:mm").format(dateM); // Formata a hora e minutos

                    if (!Objects.equals(dataAnterior, tituloDia)) { // Se a data for diferente da anterior
                        JLabel labelTitulo = new JLabel("<html><div style='text-align: center; font-weight: bold;'>" + tituloDia + "</div></html>"); // Cria um rótulo para o título do dia
                        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER); // Configura o alinhamento no centro
                        panelMensagens.add(labelTitulo); // Adiciona o rótulo ao painel de mensagens

                        dataAnterior = tituloDia; // Atualiza a data anterior
                    }

                    String nomeRemetente = extrairNomeRemetente(mensagem); // Extrai o nome do remetente
                    String soMensagens = extrairMensagens(mensagem); // Extrai as mensagens
                    String mensagemFormatada = mensagem; // Inicializa a mensagem formatada
                    if (nomeRemetente != null) { // Se o nome do remetente não for nulo
                        String cssClass = nomeRemetente.equals(loginUsuario) ? "user-1" : (nomeRemetente.equals(nomeUsuarioSelecionado) ? "user-2" : ""); // Classe CSS baseada no remetente
                        String cssStyle = "<style>" // Estilo CSS para formatar a mensagem
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
                    JLabel label = new JLabel(mensagemFormatada); // Cria um rótulo com a mensagem formatada
                    label.setBackground(Color.DARK_GRAY); // Define a cor de fundo
                    label.setOpaque(true); // Torna o rótulo opaco

                    if (nomeRemetente != null && nomeRemetente.equals(usuarioNomes.get("1"))) { // Se o remetente for o usuário atual
                        label.setHorizontalAlignment(SwingConstants.RIGHT); // Alinha à direita
                        label.setForeground(Color.DARK_GRAY); // Define a cor do texto
                    } else if (nomeRemetente != null) { // Se o remetente for o outro usuário
                        label.setHorizontalAlignment(SwingConstants.LEFT); // Alinha à esquerda
                        label.setForeground(Color.DARK_GRAY); // Define a cor do texto
                    }
                    panelMensagens.add(label); // Adiciona o rótulo ao painel de mensagens

                } catch (ParseException e) { // Tratamento de exceção para a análise de data
                    e.printStackTrace(); // Imprime o rastreamento da pilha
                }
            }
            JScrollPane scrollPane = new JScrollPane(panelMensagens); // Adiciona um JScrollPane ao painel de mensagens
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Configura a política da barra de rolagem

            jLabelMenssagem.removeAll(); // Remove todos os componentes do rótulo de mensagens
            jLabelMenssagem.setLayout(new BorderLayout()); // Define o layout como BorderLayout
            jLabelMenssagem.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane ao rótulo de mensagens

            // Deixa o ScrollBar no máximo para baixo
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getMaximum()); // Define o valor máximo da barra de rolagem
                    scrollPane.revalidate(); // Atualiza o layout do JScrollPane
                    scrollPane.repaint(); // Repinta o JScrollPane
                }
            });
            // FIM
            revalidate(); // Atualiza o layout da janela
            repaint(); // Repinta a janela
            setVisible(true); // Torna a janela visível
            Timer timer = new Timer(); // Cria um temporizador
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        atualizaMensagens(); // Atualiza as mensagens
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex); // Tratamento de exceção para SQL
                    }
                }
            };
            timer.schedule(task, 0, 5000); // Agendamento da tarefa de atualização a cada 5 segundos
        }

    }

    /**
     * Adiciona botões representando usuários ao painel de conversas.
     *
     * @param idUsuario ID do usuário atual
     * @throws IOException Exceção de E/S
     */
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

    /**
     * Inicia ou recupera uma conversa entre dois usuários.
     *
     * @param usuario String contendo informações do usuário selecionado
     * @param idUsuario ID do usuário atual
     */
    private void conversa(String usuario, String idUsuario) {
        mensagem = ""; // Inicializa a variável de mensagens vazia
        String[] partes = usuario.split("-");
        String idUsuarioSelecionado = partes[0].trim();

        // Executa uma consulta SQL para verificar se já existe uma conversa entre os dois usuários
        conn.executaSQL("SELECT * FROM conversas WHERE (usuario1_id = " + idUsuario
                + " AND usuario2_id = " + idUsuarioSelecionado + ") OR (usuario1_id = " + idUsuarioSelecionado
                + " AND usuario2_id = " + idUsuario + ");");

        try {
            if (conn.rs.next()) {
                idConversa = conn.rs.getInt("CONVERSA_ID"); // Obtém o ID da conversa existente

                mConversa = dConversa.getConversasById(idConversa); // Obtém detalhes da conversa pelo ID

                // Executa uma consulta SQL para obter as mensagens associadas a essa conversa
                conn.executaSQL("SELECT * FROM mensagens WHERE CONVERSA_ID = " + idConversa + ";");

                while (conn.rs.next()) {
                    // Concatena as mensagens recuperadas
                    mensagem = mensagem + conn.rs.getString("conteudo") + " " + conn.rs.getDate("data") + " " + conn.rs.getTime("hora") + "\n";
                }
                // Processa a conversa, exibindo as mensagens
                trataConversa(mensagem);
            } else {
                // Se não existir uma conversa, cria uma nova
                Conversa mConversa = new Conversa();
                mConversa.setUsuario1_id(Integer.parseInt(idUsuario));
                mConversa.setUsuario2_id(Integer.parseInt(idUsuarioSelecionado));
                mConversa.setApelido(loginUsuario);

                dConversa.createConversa(mConversa);

                // Agora recupera o ID da conversa recém-criada
                conn.executaSQL("SELECT CONVERSA_ID FROM Conversas WHERE usuario1_id = " + idUsuario
                        + " AND usuario2_id = " + idUsuarioSelecionado + ";");

                if (conn.rs.next()) {
                    idConversa = conn.rs.getInt("CONVERSA_ID");
                }

                // Processa a conversa, exibindo as mensagens
                trataConversa(mensagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Extrai as mensagens formatadas de uma conversa.
     *
     * @param conversa String contendo a conversa a ser analisada
     * @return Uma string contendo as mensagens extraídas
     */
    private String extrairMensagens(String conversa) {
        // StringBuilder é usado para construir eficientemente strings mutáveis
        StringBuilder mensagens = new StringBuilder();

        // Define um padrão de expressão regular para capturar mensagens formatadas
        // A expressão inclui tags HTML para nomes de remetentes e timestamp
        // O padrão DOTALL permite que o ponto (.) corresponda a qualquer caractere, incluindo quebras de linha
        Pattern pattern = Pattern.compile("<b>.*?</b>:<br>(.*?)<br>\\(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\)", Pattern.DOTALL);

        // Cria um matcher para encontrar correspondências no texto da conversa usando o padrão definido
        Matcher matcher = pattern.matcher(conversa);

        // Itera sobre todas as correspondências encontradas pelo matcher
        while (matcher.find()) {
            // Obtém a mensagem (grupo 1 no padrão) e remove espaços em branco desnecessários
            String mensagem = matcher.group(1).trim();

            // Adiciona a mensagem ao StringBuilder, seguida por uma quebra de linha
            mensagens.append(mensagem).append("\n");
        }

        // Converte o conteúdo do StringBuilder para uma string e retorna
        return mensagens.toString();
    }

    /**
     * Extrai o nome do remetente de uma mensagem formatada.
     *
     * @param mensagem String contendo a mensagem formatada
     * @return O nome do remetente ou null se não for possível extrair
     */
    private String extrairNomeRemetente(String mensagem) {
        // Usa uma expressão regular para extrair o nome do remetente
        // A expressão regular assume que o nome do remetente é seguido por ":" na mensagem.
        Pattern pattern = Pattern.compile("<b>(.*?)</b>:");

        // Cria um matcher para encontrar correspondências na mensagem usando o padrão definido
        Matcher matcher = pattern.matcher(mensagem);

        // Verifica se há uma correspondência
        if (matcher.find()) {
            // O grupo 1 da expressão regular contém o nome do remetente
            // Retorna o nome do remetente formatado em HTML negrito
            return "<html><b>" + matcher.group(1).trim() + "</b>";
        }

        // Retorna null se não for possível extrair o nome do remetente
        return null;
    }

    /**
     * Extrai a data da mensagem formatada.
     *
     * @param mensagem String contendo a mensagem formatada
     * @return A data da mensagem ou null se não for possível extrair
     */
    private String extrairDataMensagem(String mensagem) {
        // Usa uma expressão regular para extrair a data entre parênteses na mensagem
        Pattern pattern = Pattern.compile("\\((.*?)\\)");

        // Cria um matcher para encontrar correspondências na mensagem usando o padrão definido
        Matcher matcher = pattern.matcher(mensagem);

        // Verifica se há uma correspondência
        if (matcher.find()) {
            // O grupo 1 da expressão regular contém a data
            // Retorna a data extraída da mensagem
            return matcher.group(1);
        } else {
            // Se não encontrar a data, imprime uma mensagem de aviso no console
            System.out.println("Data não encontrada na mensagem.");
        }

        // Retorna null se não for possível extrair a data da mensagem
        return null;
    }

    /**
     * Extrai as mensagens formatadas da string da conversa.
     *
     * @param conversa String da conversa
     * @return Lista de mensagens formatadas
     */
    private List<String> extrairMensagem(String conversa) {
        List<String> mensagens = new ArrayList<>();

        // Use uma expressão regular para encontrar mensagens na string com nomes de remetentes e timestamps
        Pattern pattern = Pattern.compile("([A-Za-z ]+): (.*?)(?: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}))?$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(conversa);

        while (matcher.find()) {
            // Obtém o nome do remetente, a mensagem e a data da correspondência da expressão regular
            String nomeRemetente = matcher.group(1);
            String mensagem = matcher.group(2);
            String data = matcher.group(3);

            // Cria formatação de datas
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("d 'de' MMMM", new Locale("pt", "BR"));

            // Substitui o nome do remetente pelo nome do usuário correspondente
            if (usuarioNomes.containsKey(nomeRemetente)) {
                mensagem = mensagem.replace(nomeRemetente + ":", usuarioNomes.get(nomeRemetente) + ":");
            }

            // Adiciona a mensagem formatada à lista
            mensagens.add("<html><b>" + nomeRemetente + "</b>:<br>" + mensagem + "<br>(" + data + ")");
        }
        return mensagens;
    }

    // Envia uma mensagem para a conversa, armazenando-a no banco de dados.
    private void mandarMensagem() {
        // Verifica se o campo de mensagem está vazio
        if (jTextFieldMensagens.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo mensagem não pode ser em branco");
        } else {
            // Obtém a data e hora atuais
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Formata a data e hora
            String dataFormatada = agora.format(formatoData);
            String horaFormatada = agora.format(formatoHora);
            String dataHoraFormatada = dataFormatada + " " + horaFormatada;

            // Cria um formato de data para conversão
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                // Parseia a data e converte para um objeto Time
                java.util.Date dataParseada = formato.parse(dataHoraFormatada);
                Time hora = new Time(dataParseada.getTime());

                // Cria uma nova mensagem
                mMenssagens = new Mensagem();
                mMenssagens.setConteudo(loginUsuario + ": " + jTextFieldMensagens.getText());
                mMenssagens.setHora(hora);
                mMenssagens.setData(new java.util.Date());
                mConversa.setApelido(loginUsuario);
                mMenssagens.setRemetente_id(idUsuario);
                mMenssagens.setConversa_id(mConversa.getConversa_id());

                try {
                    // Armazena a mensagem no banco de dados
                    dMensagens.createMensagem(mMenssagens);
                    // Atualiza a string de mensagens e exibe a conversa atualizada
                    mensagemNova = mMenssagens.getConteudo() + " " + dataHoraFormatada + "\n";
                    mensagem += mensagemNova;
                    trataConversa(mensagem);

                    // Limpa o campo de mensagem após o envio
                    jTextFieldMensagens.setText("");

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
