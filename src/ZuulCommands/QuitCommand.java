package ZuulCommands;

import Game.AGame;
import Commands.ACommand;
import Character.Player;

/**
 *此命令类是退出命令
 * @author duoduo
 *
 */
public class QuitCommand extends ACommand
{
    public QuitCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public QuitCommand() {}
    
    /**
     * quit命令的执行命令
     */
    @Override
    public boolean execute(Player player)
    {       
        if (hasSecondWord())
        {
            AGame._out.println(AGame._messages.getString("quitWhat"));
            return false;
        }
        return true;
    }
}
