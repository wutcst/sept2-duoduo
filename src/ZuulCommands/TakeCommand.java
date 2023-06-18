package ZuulCommands;

import Game.AGame;
import Commands.ACommand;
import Character.Player;

/**
 * 此命令类是 take 命令
 * @author duoduo
 *
 */
public class TakeCommand extends ACommand
{
    public TakeCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public TakeCommand() {}
    
    /**
     * take的执行方法
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
       
            AGame._out.println(AGame._messages.getString("takeWhat"));
            return false;
        }
        String desc = getSecondWord();
        player.take(desc);
        return false;
    }
}
