package Game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Game.AGame;
import Item.Item;
import Character.Player;
import ZuulCommands.BadExitException;
/**
 * 这个类是游戏的房间类
 * @author duoduo
 *
 */
public class Room
{
	// 房间描述（名称）
    final public String _description;
    // 从房间出口
    final private Map<String, Room> _exits;
    // 房间内物品的地图
    private HashMap<String, Item> _itemsPresent;
    // 人物地图
    final private HashMap<String, Player> _characters;

    public Room(String description) 
    {
        this._description = description;
        this._itemsPresent = new HashMap<>();
        this._characters = new HashMap<>();
        this._exits = new HashMap<>();
    }
     //设置出口
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        try
        {
    	    setExit(AGame._messages.getString("north"), north);
    	    setExit(AGame._messages.getString("east"), east);
    	    setExit(AGame._messages.getString("south"), south);
    	    setExit(AGame._messages.getString("west"), west);
    	}
        catch (BadExitException e)
        {
    	    e.printStackTrace();
    	    System.exit(1);
    	}
    }
    
    public void setExit(String direction, Room room) throws BadExitException
    {
    	if (room == null)
    	    return;
        if (direction == null)
            throw new BadExitException(direction, room);
        this._exits.put(direction, room);
    }
    //public Room getRoom(String direction)
   
    /**
    *获取出口
    */
    public Room getExit(String direction)
    {
    	return this._exits.get(direction);
    }
   /**
    * 获取房间描述
    */
    public String getDescription()
    {
        return this._description;
    }
    /**
     * 设置物品名字和物品重量
     */
    public void addItem(String name, int weight)
    {
    	Item item = new Item(name, weight);
   		this._itemsPresent.put(name, item);
    }
    /**
     * 将物品信息加入哈希表
     */
    public void addItem(String name, Item item)
    {
    	if (item != null)
    	{
    		this._itemsPresent.put(name, item);
    	}
    }
    /**
     * 获取物品数量
     */
    public int getNumberItems()
    {
    	return this._itemsPresent.size();
    }
    /**
     * 获取物品
     */
    public Item getItem(String item)
    {
    	return this._itemsPresent.get(item);
    }
    /**
     * 哈希表中所有的物品
     */
    public HashMap<String, Item> getAllItems()
    {
    	return this._itemsPresent;
    }
    /**
     * 移除物品
     */
    public Item removeItem(String description)
    {
    	if (this._itemsPresent.containsKey(description))
    		return this._itemsPresent.remove(description);
    	else
    	{
    		AGame._out.println(description + " " + AGame._messages.getString("room"));
    		return null;
    	}
    }
    /**
     *判断是否包含物品
     */
    public boolean containsItem(String description)
    {
    	return this._itemsPresent.containsKey(description);
    }
    /**
     * 添加角色
     */
    public void addCharacter(Player character)
    {
    	this._characters.put(character.getName(), character);
    }
    /**
     * 获取角色
     */
    public Player getCharacter(String name)
    {
    	return this._characters.get(name);
    }
    /**
     * 移除角色
     */
    public void removeCharacter(String name)
    {
    	if (name != null)
    		this._characters.remove(name);
    }
    /**
     * 判断是否有角色
     */
    public boolean hasCharacter(String character)
    {
    	return this._characters.containsKey(character);
    }
    /**
     * 哈希表中的角色
     */
    public HashMap<String, Player> getListCharacters()
    {
    	return this._characters;
    }
    /**
     * 物品和角色的详细信息
     */
    public List<String> getDetails()
    {   	
    	List<String> details = new LinkedList<>();
    	
    	details.add(AGame._messages.getString("in") + " " + getDescription());
        String tmp = AGame._messages.getString("exits") + ": ";
        tmp = this._exits.keySet().stream()
                .map((dir) -> dir + ' ')
                .reduce(tmp, String::concat);
        details.add(tmp);
        
        tmp = AGame._messages.getString("items") + ": ";
        tmp = this._itemsPresent.keySet().stream()
                .map((desc) -> desc + '(' + this._itemsPresent.get(desc).getWeight() + ')')
                .reduce(tmp, String::concat);
        details.add(tmp);
        
        tmp = AGame._messages.getString("characters") + ": ";
        tmp = this._characters.keySet().stream()
        		.map((desc) -> desc + ' ')
        		.reduce(tmp, String::concat);    
        details.add(tmp);
        
        return details;
    }
}
