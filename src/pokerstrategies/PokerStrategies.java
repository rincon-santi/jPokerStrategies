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
import org.poker.sample.strategies.*;
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
            "Agressive Expert Bot",
            "Laid-back Expert Bot",
            "Random Bot"
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
                    pl1=new AgressiveExpertBot();
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
                case "Agressive Expert Bot":
                    pl2=new AgressiveExpertBot();
                    break;
                case "Laid-back Expert Bot":
                    pl2=new LaidBackExpertBot();
                    break;
                case "Random Bot":
                    pl2=new RandomStrategy();
                    break;
                default:
                    System.exit(0);
            }
            boolean welDone=false;
            long BB=0L;
            while(!welDone){
                 try{
                        welDone=true;
                        BB = Long.parseLong(JOptionPane.showInputDialog(null, "Select number of chips for Big Blind", "Big Blind", JOptionPane.DEFAULT_OPTION), 10);
                    }catch(Exception e){
                        welDone=false;
                        JOptionPane.showMessageDialog(null, "Please introduce a valid number", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
            }
            welDone=false;
            long NoBB=0L;
            while(!welDone){
                 try{
                        welDone=true;
                        NoBB = Long.parseLong(JOptionPane.showInputDialog(null, "How many Big Blinds as number of start chips? (between 5 and 20)", "Start Chips", JOptionPane.DEFAULT_OPTION), 10);
                        if ((NoBB>20L)||(NoBB<5L)){
                            welDone=false;
                            JOptionPane.showMessageDialog(null, "Please introduce a number between 5 and 20", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                 }catch(Exception e){
                        welDone=false;
                        JOptionPane.showMessageDialog(null, "Please introduce a valid number", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
            }
            Map<String,Double> scores=MainController.run(pl1, pl2, mode, rounds, BB, NoBB);
            JOptionPane.showMessageDialog(null, "First player won "+scores.get(pl1.getName()).toString()+" rounds and second player won "+scores.get(pl2.getName()).toString()+" rounds", "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(PokerStrategies.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    
}
