package Commands;

import Character.Player;

/**
 * 此类创建一个抽象command命令
 * @author duoduo
 *
 */
public abstract class ACommand
{
    private String _commandWord;
    private String _secondWord;
    private String _thirdWord;

    public ACommand(String firstWord, String secondWord, String thirdWord)
    {
        this._commandWord = firstWord;
        this._secondWord = secondWord;
        this._thirdWord = thirdWord;
    }
    
    public ACommand () {}
    
    /**
     * 添加命令中的关键词
     * @param firstWord
     * @param secondWord
     * @param thirdWord
     */
    public void addWords(String firstWord, String secondWord, String thirdWord) {
        this._commandWord = firstWord;
        this._secondWord = secondWord;
        this._thirdWord = thirdWord;

    }

    /**
     * 获得 commandword
     * @return
     */
    public String getCommandWord()
    {
    	return this._commandWord;
    }

    /**
     * 获得 seconndword
     * @return
     */
    public String getSecondWord()
    {
    	return this._secondWord;
    }

    /**
     * 获得 thirdword
     * @return
     */
    public String getThirdWord()
    {
    	return this._thirdWord;
    }
    
    /**
     * 检查 command 是否已知存在
     * @return
     */
    public boolean isUnknown()
    {
    	if (this._commandWord == null)
    		return true;
    	else
    		return false;
    }

    /**
     * 检查是否存在 second word
     * @return
     */
    public boolean hasSecondWord()
    {
    	if (this._secondWord != null)
    		return true;
    	else
    		return false;
    }
    
    /**
     * 检查是否存在 a second word
     * @return
     */
    public boolean hasThirdWord()
    {
    	if (this._thirdWord != null)
    		return true;
    	else
    		return false;
    }
    
    public abstract boolean execute(Player player);
}

