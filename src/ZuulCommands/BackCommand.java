package ZuulCommands;

import Commands.ACommand;
import Character.Player;
import Game.AGame;

public class BackCommand extends ACommand{
    public BackCommand(String firstWord, String secondWord, String thirdWord)
    {
        super(firstWord, secondWord, thirdWord);
    }

    public BackCommand() {}

    @Override
    public boolean execute(Player player)
    {
        player.back();
        return false;
    }
}
