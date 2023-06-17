package cn.edu.whut.sept.zuul.ZuulCommands;

import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Character.Player;
import cn.edu.whut.sept.zuul.Game.AGame;

/**
 * 此命令类用于接收未知命令
 * @author duoduo
 *
 */
public class UnknownCommand extends ACommand
{
    public UnknownCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public UnknownCommand() {}
    
    /**
     * 执行未知指令
     */
    @Override
    public boolean execute(Player player)
    {
        AGame._out.println(AGame._messages.getString("unknown"));
        return false;
    }
}
