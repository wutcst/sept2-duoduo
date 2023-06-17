package cn.edu.whut.sept.zuul.ZuulCommands;

import cn.edu.whut.sept.zuul.Character.Player;
import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Game.AGame;

/**
 * 此命令类是给命令
 *
 */
public class GiveCommand extends ACommand
{
	public GiveCommand(String firstWord, String secondWord, String thirdWord)
	{
        super(firstWord, secondWord, thirdWord);
    }
    
    public GiveCommand() {}
    
    /**
     * 给命令的执行命令，通过player去调用具体的方法
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("giveWhat"));
            return false;
        }
        if (!hasThirdWord())
        {
        	AGame._out.println(AGame._messages.getString("giveWho"));
            return false;
        }
        String desc = getSecondWord();
        String whom = getThirdWord();
        player.giveItem(desc, whom);
        return false;
    }
}
