package GUI;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

/**
 *  此类创建一个扩展到 JFrame 的窗口
 *  主窗口
 * @author duoduo
 *
 */
public class WindowFrame extends JFrame
{
	/**
	 * Constructor
	 * @param width
	 * @param height
	 */
	public WindowFrame(int width, int height)
	{
		super();
		
		setUp(width, height);
	}

	/**
	 * Set up the parameters for the main window
	 * @param width
	 * @param height
	 */
	private void setUp(int width, int height)
	{
		Container container = new Container();
		this.setTitle("Zuul Game");
		this.setSize(width, height);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = this.getContentPane();
		//布局管理器，横向排放
		container.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * get the window
	 * @return
	 */
	public WindowFrame getWindow()
	{
		return this;
	}
}
