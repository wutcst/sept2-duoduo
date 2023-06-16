package cn.edu.whut.sept.zuul.ZuulCommands;

import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Character.Player;
import cn.edu.whut.sept.zuul.Game.AGame;

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
