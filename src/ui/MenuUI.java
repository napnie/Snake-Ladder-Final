package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuUI extends JPanel {

	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 480;
	private static JFrame window;

	private JPanel p = new JPanel();
	private JButton[] startButtons;
	private ImageIcon image;

	public MenuUI() {
		window = new JFrame("Snake and Ladder");
		window.getContentPane().add(this);
		window.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		initialize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			BufferedImage image = ImageIO.read(this.getClass().getResource("/image/menu.png"));
			g.drawImage(image, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
		} catch (IOException e) {

		}
	}

	public void start() {
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private void initialize() {
		this.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(null);

		startButtons = new JButton[3];
		for (int i = 0; i < startButtons.length; i++) {
			image = new ImageIcon(getClass().getResource("/image/" + (i + 2) + "player.png"));
			image.setImage(image.getImage().getScaledInstance(287, 92, Image.SCALE_SMOOTH));
			startButtons[i] = new JButton(image);
			startButtons[i].setBorderPainted(false);
			startButtons[i].addActionListener(createBoard(i + 2));
			startButtons[i].setBounds(0, 0, 287, 92);
			this.add(startButtons[i]);
		}
		int x = 50;
		int y = 200;
		int dx = 320;
		int dy = 140;
		startButtons[0].setLocation(x, y);
		startButtons[1].setLocation(x + dx, y);
		startButtons[2].setLocation(x, y + dy);

	}

	public ActionListener createBoard(int numPlayer) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BoardUI board = new BoardUI(numPlayer);
				window.dispose();
				board.run();
			}
		};
	}

}
