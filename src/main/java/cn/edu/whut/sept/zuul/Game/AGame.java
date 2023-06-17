package cn.edu.whut.sept.zuul.Game;

import java.util.ResourceBundle;
import java.util.List;
import java.util.Locale;

import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Commands.ACommandWords;
import cn.edu.whut.sept.zuul.Commands.Parser;
import cn.edu.whut.sept.zuul.Character.Player;
import cn.edu.whut.sept.zuul.GUI.GUI;
import cn.edu.whut.sept.zuul.ZuulInputOutput.*;

/**
 * 此类创建一个抽象游戏来加载其他游戏
 *
 * @author duoduo
 *
 */
public abstract class AGame
{
	public static final Out _out = new Out();
	public static final In _in = new In();
	public static ResourceBundle _messages;
	public static ACommandWords _commands;
	final private Parser _parser;
	protected Player _player;
	protected List<Room> _rooms;
	
	/**
	 * 构造函数
	 * @param language
	 * @param country
	 * @param commands
	 */
	public AGame( ACommandWords commands)
	{
		
		AGame._messages = ResourceBundle.getBundle("ZuulCommands.MessagesBundle");
		AGame._commands = commands;
		this._parser = new Parser("ZuulCommands");
		createRooms();
	}
	
	/**
	 * 游戏开始
	 */
	public void play()
	{
		boolean finished = false;
		GUI gui = new GUI(this._player, this._parser);
		
		printWelcome();
		while (!finished)
		{
			ACommand command = this._parser.getCommand();
			finished = processCommand(command);
		}
		AGame._out.println(AGame._messages.getString("goodbye"));
	}
	
	/**
	 *欢迎
	 */
	private void printWelcome()
	{
		getWelcomeStrings().stream().forEach((str) -> { AGame._out.println(str); });
	}
	
	/**
	 * 发送给玩家以执行命令
	 * @param command
	 * @return
	 */
	private boolean processCommand(ACommand command)
	{
		return command.execute(this._player);
	}
	
	/**
	 * 游玩者加入游戏
	 * @param player
	 */
	protected void setPlayer(Player player)
	{
		this._player = player;
	}
	
	/**
	 *
	 * @return
	 */
	protected Player getPlayer()
	{
		return this._player;
	}
	
	/**
	 * set rooms
	 * @param rooms
	 */
	protected void setRooms(List<Room> rooms)
	{
		this._rooms = rooms;
	}
	
	protected abstract List<String> getWelcomeStrings();
	protected abstract void createRooms();
}
