package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelPortada  extends JPanel{
	public PanelPortada() {
		JLabel lblPortada = new JLabel("GESTI�N DE BIBLIOTECA V1.0");
		lblPortada.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPortada.setForeground(Color.BLACK);
		lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPortada);
	}

}
