package Game;

import java.util.ResourceBundle;
import java.util.List;
import java.util.Locale;

import Commands.ACommand;
import Commands.ACommandWords;
import Commands.Parser;
import Character.Player;
import GUI.GUI;
import ZuulInputOutput.*;

/**
 * 此类创建一个抽象游戏用于创建一个抽象的游戏框架来加载其他具体的游戏
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
         *接受一个 ACommandWords 对象作为参数，并在构造函数中初始化 _messages、_commands 和 _parser 对象
	 *并调用 createRooms() 方法来创建游戏的房间。
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
         *游戏开始的方法。在该方法中，创建了一个 GUI 对象，并打印欢迎信息。然后进入一个循环，不断获取玩家输入的命令，
	 *并通过调用 processCommand() 方法来处理命令，直到游戏结束。最后打印 "goodbye" 信息
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
         *打印欢迎信息的方法。通过调用 getWelcomeStrings() 方法获取欢迎信息的列表，并使用 AGame._out.println() 方法逐行打印。
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
	{//处理玩家输入的命令的方法。调用命令的 execute() 方法来执行命令，并将玩家对象传递给命令进行处理。
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
	 * 把房间加入游戏中
	 * @param rooms
	 */
	protected void setRooms(List<Room> rooms)
	{
		this._rooms = rooms;
	}
	
	protected abstract List<String> getWelcomeStrings();
	protected abstract void createRooms();
}
