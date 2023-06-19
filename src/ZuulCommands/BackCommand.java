package ZuulCommands;

import Commands.ACommand;
import Character.Player;
import Game.AGame;
/**
* 此类是back命令的实现类
*/
public class BackCommand extends ACommand{
    public BackCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }

    public BackCommand() {}

    @Override
    /**
     * back的执行方法，通过player对象调用具体方法
     * 
     */
    public boolean execute(Player player)
    {
        player.back();
        return false;
    }
}
