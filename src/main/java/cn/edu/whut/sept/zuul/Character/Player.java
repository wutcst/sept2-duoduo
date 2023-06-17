package cn.edu.whut.sept.zuul.Character;

import cn.edu.whut.sept.zuul.Item.Item;
import cn.edu.whut.sept.zuul.Game.Room;
import cn.edu.whut.sept.zuul.Game.AGame;
import cn.edu.whut.sept.zuul.Game.Game;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 此个类是“World of Zuul”应用程序的一部分。
 * "World of Zuul"是一款非常简单的基于文本的冒险游戏。
 * 
 * 玩家就是角色。它继承自字符接口。
 * Player类.
 * 我们有一个名字，一个当前房间和物品.
 * 
 * @author duoduo
 *
 */
public class Player implements Character
{
	private String _name;  // 玩家名字
	private Room _currentRoom; // 玩家所在的当前房间
	private String _lastdirection;//上一个房间
	private int _totalWeight; // 总重量
	private static int _MAX_WEIGHT = 10;
	private HashMap<String, Item> _itemsInventory;//物品仓库
	
	/**
	 * Constructor
	 * @param name
	 * @param startRoom
	 */
	//玩家基本信息
	public Player(String name, Room startRoom)
	{
		this._name = name;
		this._currentRoom = startRoom;
		this._totalWeight = 0;
		this._itemsInventory = new HashMap<>();
	}
	   
	/**
	 * 设置名字
	 */
	@Override
	public void setName(String name)
	{
		this._name = name;
	}

	/**
	 * 得到名字
	 */
	@Override
	public String getName()
	{
		return this._name;
	}
	
	/**
	 * 设置当前房间
	 */
	@Override
	public void setCurrentRoom(Room currentRoom)
	{
		this._currentRoom = currentRoom;
	}

	/**
	 * 得到当前房间
	 */
	@Override
	public Room getCurrentRoom()//room类型
	{
		return this._currentRoom;
	}
	
	//得到上一个方向
	public String get_lastdirection()
	{
		return this._lastdirection;
		}

	/**
	 * 得到详细信息
	 */
	@Override
	public List<String> getDetails()
	{
		return getCurrentRoom().getDetails();
	}

	/**
	 * 得到仓库的物品
	 */
	@Override
	public Item getItemInventory(String item)
	{
		return _itemsInventory.get(item);
	}
    //得到仓库大小
	@Override
	public int getInventorySize()
	{
		return this._itemsInventory.size();
	}

	/**
	 * 得到仓库
	 */
	@Override
	public HashMap<String, Item> getInventory()
	{
		return this._itemsInventory;
	}
	
	/**
	 * 检查是否存在物品item
	 * @param desc
	 * @return
	 */
	public boolean hasItem(String desc)
	{
		return this._itemsInventory.containsKey(desc);
	}

	/**
	 * 检查当前总重加上物品是否超重
	 * @param item
	 * @return
	 */
	public boolean tooHeavy(Item item)
	{
		return (item.getWeight() + this._totalWeight > getMaxWeight());
	}

	/**
	 * 添加物品
	 */
	@Override
	public void addItem(Item item)
	{
		this._itemsInventory.put(item.getItemName(), item);
	}
	
	/**
	 * take命令实现
	 * @param desc
	 */
    public void take(String desc)
    {
        if (!getCurrentRoom().containsItem(desc))
        {
            AGame._out.println(desc + " " + AGame._messages.getString("room"));
            return;
        }
        Item item = getCurrentRoom().getItem(desc);
        if (tooHeavy(item))
        {
            AGame._out.println(desc + " " + AGame._messages.getString("heavy"));
            return;
        }
        item = getCurrentRoom().removeItem(desc);
        this._itemsInventory.put(desc, item);
        
        this._totalWeight += item.getWeight();
    }
	
	/**
	 * drop命令实现
	 * @param name
	 */
	@Override
	public void dropItem(String name)
	{
		if (!hasItem(name))
		{
			AGame._out.println(AGame._messages.getString("dontHave") + " " + name);
            return;
		}
        Item item = this._itemsInventory.remove(name);
        this._totalWeight -= item.getWeight();
        this._currentRoom.addItem(name, item);
	}
	
	/**
	 * give命令实现
	 * @param desc
	 */
	@Override
    public void giveItem(String desc, String character)
	{
        if (!this._currentRoom.hasCharacter(character))
        {
            AGame._out.println(character + " " + AGame._messages.getString("room"));
            return;
        }
        if (!this._itemsInventory.containsKey(desc))
        {
            AGame._out.println(AGame._messages.getString("room") + " " + desc);
            return;
        }
        if (this._currentRoom.getCharacter(character).getTotalWeight() + this._itemsInventory.get(desc).getWeight() > this._currentRoom.getCharacter(character).getMaxWeight())
        {
        	AGame._out.println(character + " " + AGame._messages.getString("heavy") + " " + desc);
            return;
        }
        Item item = this._itemsInventory.remove(desc);
        this._totalWeight -= item.getWeight();
        this._currentRoom.getCharacter(character).addItem(item);        
    }
	/**
	 * look命令实现
	 */
	public void look()
	{
		for (String str : getCurrentRoom().getDetails())
			AGame._out.println(str);
	}
	//back命令实现
	public void back(){
		String newdirection = null;
		switch (_lastdirection){
			case "west":newdirection="east";break;
			case "east":newdirection="west";break;
			case "south":newdirection="north";break;
			case "north":newdirection="south";break;

		}
		Room Backroom=getCurrentRoom().getExit(newdirection);
		setCurrentRoom(Backroom);
		_lastdirection=null;
		look();

	}
	
	/**
	 * go room实现
	 * @param direction
	 */
    public void goRoom(String direction)
    {
        Room nextRoom = getCurrentRoom().getExit(direction);

        if (nextRoom == null)
        {
            AGame._out.println(AGame._messages.getString("door"));
        }
        else{
            setCurrentRoom(nextRoom);
			_lastdirection=direction;
            look();

			if(Objects.equals(getCurrentRoom().getDescription(), "TProom and you will tp to a random room")){

				String destination = null;
				Random ran1=new Random();
				int count=ran1.nextInt(4);
				switch(count){
					case 0:destination="east";break;
					case 1:destination="west";break;
					case 2:destination="north";break;
					case 3:destination="south";break;
				}
				Room TPRoom = getCurrentRoom().getExit(destination);
				setCurrentRoom(TPRoom);
				_lastdirection=null;
				look();

			}

    }}

	
    /**
     * 设置总重量
     */
	public void setTotalWeight(int totalWeight)
	{
		this._totalWeight = totalWeight;
	}

	/**
	 * 得到总重量
	 */
	public int getTotalWeight()
	{
		return this._totalWeight;
	}
	
	/**
	 * 得到最大总量
	 */
	public int getMaxWeight()
	{
		return _MAX_WEIGHT;
	}
}
