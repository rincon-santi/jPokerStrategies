/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerstrategies;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.poker.api.game.IStrategy;
import org.poker.gui.ImageManager;
import org.poker.main.MainController;
import org.poker.sample.strategies.PlayerStrategy;
import org.poker.sample.strategies.RandomStrategy;
import strategies.UserStrategy1;
import strategies.UserStrategy2;

/**
 *
 * @author tina
 */
public class PokerStrategies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String [] opts_for_pl1={
            "Human Player",
            "My Strategy 1"
        };
        String [] opts_for_pl2={
            "My Strategy 2",
            "Expert Bot"
        };
        String [] modes={
            "Visual",
            "Not Visual"
        };
        Icon icon= new ImageIcon(ImageManager.INSTANCE.getImage(ImageManager.IMAGES_PATH+"poker-chip.png")); 
        IStrategy pl1=null;
        IStrategy pl2=null;
        int rounds=1;
        int mode=MainController.VISUAL_GAME;
        try {
            String finOpt_pl1=(String) JOptionPane.showInputDialog(null, "Select first player", "Player Selection", JOptionPane.DEFAULT_OPTION, icon, opts_for_pl1, opts_for_pl1[0]);
            String finOpt_pl2=(String) JOptionPane.showInputDialog(null, "Select second player", "Player Selection", JOptionPane.DEFAULT_OPTION, icon, opts_for_pl2, opts_for_pl2[0]);
            switch (finOpt_pl1){
                case "Human Player":
                    pl1=new PlayerStrategy();
                    mode=MainController.VISUAL_GAME;
                    boolean weDone=false;
                    while(!weDone) try{
                        weDone=true;
                        rounds = Integer.parseInt(JOptionPane.showInputDialog(null, "Select number of rounds", "Number Of Rounds", JOptionPane.DEFAULT_OPTION), 10);
                    }catch(Exception e){
                        weDone=false;
                        JOptionPane.showMessageDialog(null, "Please introduce a valid number", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                    break;
                case "My Strategy 1":
                    pl1=new RandomStrategy("1");
                    boolean welDone=false;
                    while(!welDone) try{
                        welDone=true;
                        rounds = Integer.parseInt(JOptionPane.showInputDialog(null, "Select number of rounds", "Number Of Rounds", JOptionPane.DEFAULT_OPTION), 10);
                    }catch(Exception e){
                        welDone=false;
                        JOptionPane.showMessageDialog(null, "Please introduce a valid number", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                    String modeGame=(String) JOptionPane.showInputDialog(null, "Select game mode", "Mode Selection", JOptionPane.DEFAULT_OPTION, icon, modes, modes[0]);
                    switch(modeGame){
                        case "Visual":
                            mode=MainController.VISUAL_GAME;
                            break;
                        case "Not Visual":
                            mode=MainController.NO_VISUAL_GAME;
                            break;
                    }
                    break;
                default:
                    System.exit(0);
            }
            switch (finOpt_pl2){
                case "My Strategy 2":
                    pl2=new UserStrategy2();
                    break;
                case "Expert Bot":
                    pl2=new RandomStrategy("2");
                    break;
                default:
                    System.exit(0);
            }
            Map<String,Double> scores=MainController.run(pl1, pl2, mode, rounds);
            JOptionPane.showMessageDialog(null, "First player won "+scores.get(pl1.getName()).toString()+" rounds and second player won "+scores.get(pl2.getName()).toString()+" rounds", "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(PokerStrategies.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    
}
