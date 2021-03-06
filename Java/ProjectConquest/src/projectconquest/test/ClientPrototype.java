/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectconquest.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import projectconquest.data.map.Map;
import projectconquest.net.Protocol;
import projectconquest.net.client.GameClient;
import projectconquest.net.client.GameClientCommand;
import projectconquest.util.ConsoleLog;
import projectconquest.util.Log;

/**
 *
 * @author Andrea
 */
public class ClientPrototype extends javax.swing.JFrame implements GameClient.Listener {
    
    public static final Resolution[] RESOLUTIONS = {
        new Resolution(320, 480),
        new Resolution(480, 800),
        new Resolution(768, 1280)
    };
    
    private GameClient client; 
    
    private Log log;
    
    /**
     * Creates new form ClientPrototype
     */
    public ClientPrototype() {
        initComponents();
        
        log = new ConsoleLog();
        
        for(Resolution r : RESOLUTIONS) {
            
            JResolutionMenuItem item = new JResolutionMenuItem(r);
            
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    mnuChangeResolutionActionPerformed(ae);
                }
            });
            
            mnuResolution.add(item);
            
        }
        
        changeResolution(RESOLUTIONS[0]);
        
        
    }
    
    protected void changeResolution(Resolution r) {
        pnlGame.setResolution(r);
        pack();        
    }
    
    protected void mnuChangeResolutionActionPerformed(ActionEvent ae) {
        Resolution r = ((JResolutionMenuItem)ae.getSource()).getResolution();
        changeResolution(r);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGame = new projectconquest.test.GamePanel();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuConnect = new javax.swing.JMenuItem();
        mnuResolution = new javax.swing.JMenu();
        javax.swing.JMenu mnuGame = new javax.swing.JMenu();
        mnuEndTurn = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlGame.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        mnuFile.setText("File");

        mnuConnect.setText("Connect");
        mnuConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConnectActionPerformed(evt);
            }
        });
        mnuFile.add(mnuConnect);

        jMenuBar1.add(mnuFile);

        mnuResolution.setText("Resolution");
        jMenuBar1.add(mnuResolution);

        mnuGame.setText("Game");

        mnuEndTurn.setText("End turn");
        mnuEndTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEndTurnActionPerformed(evt);
            }
        });
        mnuGame.add(mnuEndTurn);

        jMenuBar1.add(mnuGame);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConnectActionPerformed
        // TODO add your handling code here:
        
        if(client == null) {
            client = new GameClient();
            client.addListener(this);
            try {
                client.connect(InetAddress.getLocalHost(), RunServer.SERVER_PORT);
                String map = client.getMapName();
                
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(map));
                pnlGame.setMap((Map)is.readObject());
                is.close();
            } 
            catch (UnknownHostException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex2) {
                JOptionPane.showMessageDialog(this, ex2.getMessage(), ex2.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            }    
            catch(ClassNotFoundException ex3) {
                JOptionPane.showMessageDialog(this, ex3.getMessage(), ex3.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            }            
            
        }
        
    }//GEN-LAST:event_mnuConnectActionPerformed

    private void mnuEndTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEndTurnActionPerformed
        // TODO add your handling code here:
        try {
            client.endTurn();
        }
        catch(IOException ex) {
            log.e(client, ex.getMessage());
        }
    }//GEN-LAST:event_mnuEndTurnActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ClientPrototype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientPrototype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientPrototype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientPrototype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientPrototype().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem mnuConnect;
    private javax.swing.JMenuItem mnuEndTurn;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuResolution;
    private projectconquest.test.GamePanel pnlGame;
    // End of variables declaration//GEN-END:variables

    @Override
    public void commandRecevied(GameClientCommand cmd) {
        switch(cmd.getCommand()) {
            case Protocol.M_GS_END_TURN:
                log.i(client, "End turn");
                            
                try {
                    if(client.isMyTurn())
                        JOptionPane.showMessageDialog(this, "It's your turn", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(IOException ex) {
                    log.e(client, ex.getMessage());
                }               
                
                break;
            case Protocol.M_GS_WAIT_FOR_PLAYERS:
                pnlGame.waitForPlayers();
                log.i(client, "Waiting for players...");
                break;
            case Protocol.M_GS_RUN_GAME: 
                pnlGame.runGame();
                
                try {
                    if(client.isMyTurn())
                        JOptionPane.showMessageDialog(this, "It's your turn", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(IOException ex) {
                    log.e(client, ex.getMessage());
                }
                
                log.i(client, "Game is running...");
                break;
            default:
                log.w(client, "Unimplemented command: " + cmd.getCommand());
                break;
        }
    }
    
    public static class Resolution {
       
        private int width, height;
        
        public Resolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public String toString() {
            return width + " x " + height;
        }
        
        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }
    
    private class JResolutionMenuItem extends JMenuItem {
        
        private Resolution resolution;
        
        public JResolutionMenuItem(Resolution r) {
            super(r.toString());
            this.resolution = r;
        }
        
        public Resolution getResolution() { return resolution; }
        
    }
    
}
