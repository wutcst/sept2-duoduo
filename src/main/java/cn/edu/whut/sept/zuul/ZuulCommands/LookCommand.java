package cn.edu.whut.sept.zuul.ZuulCommands;

import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Game.AGame;
import cn.edu.whut.sept.zuul.Character.Player;

/**
 * 此类是look命令
 * @author duoduo
 *
 */
public class LookCommand extends ACommand
{
    public LookCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public LookCommand() {}
    
    /**
     *look的执行方法，通过player调用具体指令
     */
    @Override
    public boolean execute(Player player)
    {
        if (hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("lookWhat"));
            return false;
        }
        player.look();
        return false;
    }
}
