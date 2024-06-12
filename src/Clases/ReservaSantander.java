package Clases;

import Interfaces_Recepcionista.Principal_Hotel;
import com.formdev.flatlaf.IntelliJTheme;
import javax.swing.UIManager;

public class ReservaSantander {

    public static void main(String[] args) {
        // TODO code application logic here
        //setLookAndFeel
        try {
            IntelliJTheme.setup(ReservaSantander.class.getResourceAsStream("/Solarized Light.theme.json"));
        } catch (Exception e) {
            System.err.println("Failed to load theme");
            e.printStackTrace();
        }
        Principal_Hotel PH = new Principal_Hotel();
        PH.setVisible(true);
        UIManager.put("TextComponent.arc", 100);
        UIManager.put("Button.arc", 100);
    }    
}
