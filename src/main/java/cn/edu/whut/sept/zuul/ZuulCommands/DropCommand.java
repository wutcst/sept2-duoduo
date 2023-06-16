package cn.edu.whut.sept.zuul.ZuulCommands;

import cn.edu.whut.sept.zuul.Commands.ACommand;
import cn.edu.whut.sept.zuul.Character.Player;
import cn.edu.whut.sept.zuul.Game.AGame;

/**
 * 此命令类是 Drop 命令
 * @author duoduo
 *
 */
public class DropCommand extends ACommand
{
    
    public DropCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }
    
    public DropCommand() {}
   
    /**
     * 通过player去调用具体方法去执行操作
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord())
        {
            // 如果没有第二个词，就不知道玩家要丢弃什么物品.
            AGame._out.println(AGame._messages.getString("dropWhat"));
            return false;
        }
        String desc = getSecondWord();
        player.dropItem(desc);
        return false;
    }
}
