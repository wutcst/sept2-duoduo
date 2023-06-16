package ZuulCommands;

import Commands.ACommandWords;

/**
 * 此类是抽象命令单词类的实现，用于确定有效命令
 * @author duoduo
 *
 */
public class CommandWords extends ACommandWords
{
    // 保存所有有效命令字的常量数组
    private static final String[] validCommands = 
    	{
        	"go",
        	"quit",
        	"help",
        	"look",
        	"take",
        	"drop",
        	"give",
            "back"
        };

    /**
     *构造函数
     */
    public CommandWords() {}
    
    /** 
     * @return the validCommands
     * */
    @Override
    public  String[] getValidCommands()
    {
    	return validCommands;
    }

}
