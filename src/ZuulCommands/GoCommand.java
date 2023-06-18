package ZuulCommands;

import Commands.ACommand;
import Game.AGame;
import Character.Player;

/**
 * 此命令类是 go 命令
 * @author duoduo
 *
 */
public class GoCommand extends ACommand
{
	public GoCommand(String firstWord, String secondWord, String thirdWord)
	{
        super(firstWord, secondWord, thirdWord);
    }
    
    public GoCommand() {}
    
    /**
     * go的执行命令，通过player去调用具体方法
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("goWhere"));
            return false;
        }
        String direction = getSecondWord();
        player.goRoom(direction);
        return false;
    }
}
