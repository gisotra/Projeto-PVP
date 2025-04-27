package gameloop;

import javax.swing.*;

public class GFrame extends JFrame{
    /*
    Classe responsável por criar a janela dos meus componentes na minha tela. 
    Ela atua de mãos dadas com o GPanel (GamePanel), de forma que o GFrame 
    (GameFrame) é o equivalente à um "quadro", e o GPanel é a pintura, a "foto"
    que será ali colocada.
    */
        public GFrame()
     {
        setTitle("Jogo 2D pvp");                    // Título do nosso Jogo
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // Fecha a thread Main quando clicado no botão "Exit"
        setUndecorated(true);                       // Remove barra de título e bordas
        setExtendedState(JFrame.MAXIMIZED_BOTH);    // Maximiza a janela
        add(new GPanel());                          // Adiciona o GPanel para pintar os elementos
        setResizable(false);                        // Usuário não pode dar resize na janela
        setLocationRelativeTo(null);                // Centralizado na tela
        setVisible(true);
     }
}
