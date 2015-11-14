/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcolossus.mapeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import projectcolossus.gamelogic.cards.Card;
import projectcolossus.gamelogic.cards.Deck;

/**
 *
 * @author Andrea
 */
public class DeckEditorUI extends javax.swing.JFrame {
    
    private DefaultListModel<Card> cardListModel;
    
    /**
     * Creates new form DeckEditorUI
     */
    public DeckEditorUI() {
        initComponents();
        
        String[] cardNames = Card.getAvailableCards();
        
        cardListModel = new DefaultListModel<Card>();
        
        cmoCardSelection.setModel(new DefaultComboBoxModel<String>(cardNames));
        lstCards.setModel(cardListModel);
        
        fileChooser.setFileFilter(
            new FileFilter() {

                @Override
                public boolean accept(File file) {
                    if(file.isDirectory() || file.getName().endsWith(".pcd"))
                        return true;
                    else
                        return false;
                }

                @Override
                public String getDescription() {
                    return "Project Colossus Deck File (*.pcd)";
                }
            }        
        );
        
        updateCardNumber();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCards = new javax.swing.JList();
        cmoCardSelection = new javax.swing.JComboBox();
        cmdAddCard = new javax.swing.JButton();
        cmdRemoveCard = new javax.swing.JButton();
        lblCards = new javax.swing.JLabel();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        nniFile = new javax.swing.JMenu();
        mnuFileNew = new javax.swing.JMenuItem();
        mnuFileOpen = new javax.swing.JMenuItem();
        mnuFileSave = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator mnuSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deck Editor");

        lstCards.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "ciao", "ciao" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstCards);

        cmdAddCard.setText("+");
        cmdAddCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddCardActionPerformed(evt);
            }
        });

        cmdRemoveCard.setText("-");
        cmdRemoveCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRemoveCardActionPerformed(evt);
            }
        });

        lblCards.setText("Cards");

        nniFile.setText("File");

        mnuFileNew.setText("New");
        mnuFileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileNewActionPerformed(evt);
            }
        });
        nniFile.add(mnuFileNew);

        mnuFileOpen.setText("Open...");
        mnuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileOpenActionPerformed(evt);
            }
        });
        nniFile.add(mnuFileOpen);

        mnuFileSave.setText("Save as...");
        mnuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileSaveActionPerformed(evt);
            }
        });
        nniFile.add(mnuFileSave);
        nniFile.add(mnuSeparator1);

        mnuExit.setText("Exit");
        nniFile.add(mnuExit);

        jMenuBar1.add(nniFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmoCardSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdAddCard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRemoveCard, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmoCardSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAddCard)
                    .addComponent(cmdRemoveCard)
                    .addComponent(lblCards))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddCardActionPerformed
        // TODO add your handling code here:
        
        cardListModel.addElement(Card.forName((String)cmoCardSelection.getSelectedItem()));
        updateCardNumber();
        //lstCards.setModel(cardListModel);
    }//GEN-LAST:event_cmdAddCardActionPerformed

    private void cmdRemoveCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRemoveCardActionPerformed
        // TODO add your handling code here:
        if(lstCards.getSelectedIndex() >= 0)
            cardListModel.remove(lstCards.getSelectedIndex());
        
        updateCardNumber();
    }//GEN-LAST:event_cmdRemoveCardActionPerformed

    private void mnuFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileOpenActionPerformed
        // TODO add your handling code here:
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try {
                FileInputStream in = new FileInputStream(file);
                Deck result = Deck.read(in);
                in.close();
                
                cardListModel.removeAllElements();
                
                for(Card c : result.getCards())
                    cardListModel.addElement(c);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
            
        }
        
        updateCardNumber();
    }//GEN-LAST:event_mnuFileOpenActionPerformed

    private void mnuFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileSaveActionPerformed
        // TODO add your handling code here:
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try {
                
                Deck deck = new Deck();
                
                for(int i = 0; i < cardListModel.size(); i++)
                    deck.addCard(cardListModel.elementAt(i));
                
                FileOutputStream out = new FileOutputStream(file);
                
                Deck.write(out, deck);
                
                out.close();
               
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
            
        }
        
        updateCardNumber();
    }//GEN-LAST:event_mnuFileSaveActionPerformed

    private void mnuFileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileNewActionPerformed
        // TODO add your handling code here:
        cardListModel.removeAllElements();
        updateCardNumber();
    }//GEN-LAST:event_mnuFileNewActionPerformed

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
            java.util.logging.Logger.getLogger(DeckEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeckEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeckEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeckEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeckEditorUI().setVisible(true);
            }
        });
    }
    
    private void updateCardNumber() {
        lblCards.setText(lstCards.getModel().getSize() + "/" + Deck.MAX_CARDS);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddCard;
    private javax.swing.JButton cmdRemoveCard;
    private javax.swing.JComboBox cmoCardSelection;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCards;
    private javax.swing.JList lstCards;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuFileNew;
    private javax.swing.JMenuItem mnuFileOpen;
    private javax.swing.JMenuItem mnuFileSave;
    private javax.swing.JMenu nniFile;
    // End of variables declaration//GEN-END:variables
}