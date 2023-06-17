package cn.edu.whut.sept.zuul.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import cn.edu.whut.sept.zuul.Character.Player;
import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Commands.Parser;
import cn.edu.whut.sept.zuul.Game.Room;

/**
 * 此类用于将控制面板打印到 GUI 中
 * @author 多多
 *
 */
public class ControlsPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Player _player;
	private Parser _parser;
	private JButton buttonLook = new JButton("Look");
	private JButton buttonTake = new JButton("Take");
	private JButton buttonDrop = new JButton("Drop");
	private JButton buttonGive = new JButton("Give");
	private JButton buttonBack = new JButton("Back");
	private JButton buttonNorth = new JButton("North");
	private JButton buttonInventory = new JButton("Inventory");
	private JButton buttonWest = new JButton("West");
	private JButton buttonSouth = new JButton("South");
	private JButton buttonEast = new JButton("East");
	private Container window;
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private Graphics2D g2;
	private GamePanel _gamePanel;
	private JPanel inventory = new JPanel();

	/**
	 * Constructor
	 * @param cwindow
	 * @param player
	 * @param parser
	 * @param gamePanel
	 */
	public ControlsPanel(Container cwindow, Player player, Parser parser, GamePanel gamePanel)
	{
		super();
		this._gamePanel = gamePanel;
		this.window = cwindow;
		this._player = player;
		this._parser = parser;
		//纵向排列
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
		this.setMinimumSize(new Dimension(440,600));
		window.add(this);
		drawComponents();
	}
	
	/**
	 * @return 控制面板对象
	 */
	public ControlsPanel getControlsPanel()
	{
		return this;
	}
	
	/**
	 * Update 执行操作时的方法
	 */
	public void updateGamePanel()
	{
		this._gamePanel.drawComponents(this._gamePanel.getGraphics());
		addInInventory();
		checkButtonsEnabled();
	}
	
	/**
	 * 绘制控件面板组件的方法
	 */
	public void drawComponents()
	{
		drawInventory();
		drawControlsTable();
		addInInventory();
		checkButtonsEnabled();
	}
	
	/**
	 * 检查按钮是否可以启用的方法
	 */
	public void checkButtonsEnabled()
	{
		Room checkNorth = this._player.getCurrentRoom().getExit("north");
		Room checkSouth = this._player.getCurrentRoom().getExit("south");
		Room checkEast = this._player.getCurrentRoom().getExit("east");
		Room checkWest = this._player.getCurrentRoom().getExit("west");
		
		//
		if (checkNorth == null)
			this.buttonNorth.setEnabled(false);
		else
			this.buttonNorth.setEnabled(true);
		if (checkSouth == null)
			this.buttonSouth.setEnabled(false);
		else
			this.buttonSouth.setEnabled(true);
		if (checkEast == null)
			this.buttonEast.setEnabled(false);
		else
			this.buttonEast.setEnabled(true);
		if (checkWest == null)
			this.buttonWest.setEnabled(false);
		else
			this.buttonWest.setEnabled(true);
		// 检查玩家是否可以拿起物品并打印按钮（getAllItems()前提为房间内有物体且总重量不超重）
		if (this._player.getCurrentRoom().getAllItems().size() <= 0)
			this.buttonTake.setEnabled(false);
		else
			this.buttonTake.setEnabled(true);
		// 检查玩家是否可以丢弃物品并打印按钮
		if (this._player.getInventory().size() <= 0)
			this.buttonDrop.setEnabled(false);
		else
			this.buttonDrop.setEnabled(true);
		// 检查玩家是否可以给予物品并打印按钮
		if ((this._player.getInventory().size() <= 0) || (this._player.getCurrentRoom().getListCharacters().size() <= 0))
			this.buttonGive.setEnabled(false);
		else
			this.buttonGive.setEnabled(true);
		// 检查玩家是否取完了所有物品并打印按钮
		if (this._player.getInventory().isEmpty())
			this.buttonInventory.setEnabled(false);
		else
			this.buttonInventory.setEnabled(true);
		//检查玩家是否可以执行back命令，避免游戏开始时或是TProom结束后back情况的复杂性
		if(this._player.get_lastdirection()==null)
			this.buttonBack.setEnabled(false);
		else
			this.buttonBack.setEnabled(true);
	}
	
	/**
	 * 将物品添加到 GUI 的方法
	 */
	public void addInInventory()
	{
		model.clear();
		for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
		{
			model.addElement(mapKey);
		}
	}
	
	/**
	 * 使用命令按钮绘制列表的方法
	 */
	public void drawInventory()
	{
		//纵向
		inventory.setLayout(new BoxLayout(inventory, BoxLayout.Y_AXIS));
		//滚动条面板
		JScrollPane scrollList = new JScrollPane(list);
		DefaultListCellRenderer renderer = null;
		renderer = (DefaultListCellRenderer)list.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		Border empty = BorderFactory.createEmptyBorder();
		TitledBorder title = BorderFactory.createTitledBorder(empty, "Items in Room");
		title.setTitleJustification(TitledBorder.CENTER);
		scrollList.setBorder(title);
		inventory.add(scrollList);
		
        //空的刚性区域，用于button划分开来
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		// 添加新的按钮
		JPanel toolBar = new JPanel();
		//横向容器
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
		toolBar.setVisible(true);
		// 使用操作侦听器设置工具栏中的按钮
		//		添加look按钮
		buttonLook.addActionListener(this);
		toolBar.add(buttonLook);
		//		添加back按钮
		buttonBack.addActionListener(this);
		toolBar.add(buttonBack);
		// 		添加take按钮
		buttonTake.addActionListener(this);
		toolBar.add(buttonTake);
		//		 添加drop按钮
		buttonDrop.addActionListener(this);
		toolBar.add(buttonDrop);
		// 		添加give按钮
		buttonGive.addActionListener(this);
		toolBar.add(buttonGive);
		// 		在清单中添加工具栏，在控制面板中添加清单
		inventory.add(toolBar);
		this.add(inventory);
		//空的刚性区域，用于button划分开来
		inventory.add(Box.createRigidArea(new Dimension(10, 10)));
		// add the control panel in the window
		window.add(this);
	}
	
	/**
	 * 绘制控制器表的方法
	 */
	public void drawControlsTable()
	{
		// 添加面板以包含控件移动操作
		JPanel controlerTable = new JPanel();
		controlerTable.setPreferredSize(new Dimension(120, 200));
		controlerTable.setMaximumSize(getPreferredSize());
		controlerTable.setLayout(new BorderLayout());
		
		// 从控件移动设置按钮
		// 		设置北向按钮
		this.buttonNorth.setPreferredSize(new Dimension(20, 60));
		this.buttonNorth.setMaximumSize(getPreferredSize());
		this.buttonNorth.addActionListener(this);
		controlerTable.add(this.buttonNorth, BorderLayout.PAGE_START);
		// 		设置中心按钮
		this.buttonInventory.setPreferredSize(new Dimension(10, 10));
		this.buttonInventory.setMaximumSize(getPreferredSize());
		this.buttonInventory.addActionListener(this);
        controlerTable.add(this.buttonInventory, BorderLayout.CENTER);
        // 		设置西向按钮
        this.buttonWest.setPreferredSize(new Dimension(80, 10));
        this.buttonWest.setMaximumSize(getPreferredSize());
        this.buttonWest.addActionListener(this);
        controlerTable.add(this.buttonWest, BorderLayout.LINE_START);
        // 		设置南向按钮
        this.buttonSouth.setPreferredSize(new Dimension(10, 60));
        this.buttonSouth.setMaximumSize(getPreferredSize());
        this.buttonSouth.addActionListener(this);
        controlerTable.add(this.buttonSouth, BorderLayout.PAGE_END);
        // 		添加东向按钮
        this.buttonEast.setPreferredSize(new Dimension(80, 10));
        this.buttonEast.setMaximumSize(getPreferredSize());
        this.buttonEast.addActionListener(this);
        controlerTable.add(this.buttonEast, BorderLayout.LINE_END);
        // 		将控制器表添加到控制器组件面板
        this.add(controlerTable);
		//空的刚性区域，用于button划分开来
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        // 添加按钮
        window.add(this);
	}
	
	/**
	 * 按钮事件监听方法
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		updateGamePanel();
		// look按钮
		if(source == this.buttonLook)
		{
			ACommand command = this._parser.getCommandGUI("look");
			command.execute(this._player);
		}
		if(source == this.buttonBack)
		{
			ACommand command = this._parser.getCommandGUI("back");
			command.execute(this._player);
		}
		// Button Take
		else if (source == this.buttonTake)
		{
			if (this._player.getCurrentRoom().getAllItems().size() > 0)
			{

				int i = 0;
				String[] tab = new String[this._player.getCurrentRoom().getAllItems().size()];
				for (String mapKey : this._player.getCurrentRoom().getAllItems().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//下拉菜单，获得属性值，生成take item形式的command并执行相应逻辑方法
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "Choose an item to Take",
	                    "Take Command",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				
				ACommand command = this._parser.getCommandGUI("take" + " " + item);
				command.execute(this._player);
			}
		}
		// Drop按钮
		else if(source == this.buttonDrop)
		{
			//是否已经take了某些物品
			if (this._player.getInventory().size() > 0)
			{
				// 下拉菜单，选择后生成drop item 形式的command并执行相应逻辑方法
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "Choose an item to Drop",
	                    "Drop Command",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				ACommand command = this._parser.getCommandGUI("drop" + " " + item);
				command.execute(this._player);
			}
		}
		// Give按钮
		else if(source == this.buttonGive)
		{
			//前提条件当前玩家已经take了某些物品
			if (this._player.getInventory().size() > 0)
			{
				//
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//通过下拉菜单选择item
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    "Choose an item to Give",
	                    "Give Command",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
				//如果当前房间存在其他角色
				if (this._player.getCurrentRoom().getListCharacters().size() > 0)
				{
					// 选择基于那个角色
					int j = 0;
					String[] tab2 = new String[this._player.getCurrentRoom().getListCharacters().size()];
					for (String mapKey2 : this._player.getCurrentRoom().getListCharacters().keySet())
					{
						tab2[j] = mapKey2;
						++j;
					}
					//下拉菜单选择give哪个角色
					String who = (String)JOptionPane.showInputDialog(
		                    this.window,
		                    "Choose an charcater to Give",
		                    "Give Command",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    tab2,
		                    tab2[0]);
					// 形成形式类似于give item charcater的command语句并执行逻辑方法
					ACommand command = this._parser.getCommandGUI("give" + " " + item + " " + who);
					command.execute(this._player);
				}
			}
		}
		// 北向North
		else if(source == this.buttonNorth)
		{
			ACommand command = this._parser.getCommandGUI("go north");
			command.execute(this._player);
		}
		// Button Inventory
		else if(source == this.buttonInventory)
		{
			if (this._player.getInventory().size() > 0)
			{
				int i = 0;
				String[] tab = new String[this._player.getInventory().size()];
				for (String mapKey : this._player.getInventory().keySet())
				{
					tab[i] = mapKey;
					++i;
				}
				//查看已经获得物品清单以及总重量
				String info = "Weight : " + this._player.getTotalWeight() + " / " + this._player.getMaxWeight();
				String item = (String)JOptionPane.showInputDialog(
	                    this.window,
	                    info,
	                    "INVENTORY",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    tab,
	                    tab[0]);
			}
		}
		// Button West
		else if(source == this.buttonWest) 
		{
			ACommand command = this._parser.getCommandGUI("go west");
			command.execute(this._player);
		}
		// Button South
		else if(source == this.buttonSouth)
		{
			ACommand command = this._parser.getCommandGUI("go south");
			command.execute(this._player);
		}
		// Button East
		else if(source == this.buttonEast)
		{
			ACommand command = this._parser.getCommandGUI("go east");
			command.execute(this._player);
		}
		//添加物品到清单，以及设置button是否仍然可用
		updateGamePanel();
	}
}
