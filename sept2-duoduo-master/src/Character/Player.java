package Character;

import Item.Item;
import Game.Room;
import Game.AGame;
import Game.Game;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
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
	private String _name;  // name of the player
	private Room _currentRoom; // current room where the player is
	private String _lastdirection;
	private int _totalWeight; // weight total of items can wear
	private static int _MAX_WEIGHT = 10;
	private HashMap<String, Item> _itemsInventory;
	
	/**
	 * Constructor
	 * @param name
	 * @param startRoom
	 */
	public Player(String name, Room startRoom)
	{
		this._name = name;
		this._currentRoom = startRoom;
		this._totalWeight = 0;
		this._itemsInventory = new HashMap<>();
	}
	   
	/**
	 * set the name
	 */
	@Override
	public void setName(String name)
	{
		this._name = name;
	}

	/**
	 * get the name
	 */
	@Override
	public String getName()
	{
		return this._name;
	}
	
	/**
	 * set the current room
	 */
	@Override
	public void setCurrentRoom(Room currentRoom)
	{
		this._currentRoom = currentRoom;
	}

	/**
	 * get the current room
	 */
	@Override
	public Room getCurrentRoom()
	{
		return this._currentRoom;
	}
	public String get_lastdirection()	{return this._lastdirection;}

	/**
	 * get details
	 */
	@Override
	public List<String> getDetails()
	{
		return getCurrentRoom().getDetails();
	}

	/**
	 * get a item
	 */
	@Override
	public Item getItemInventory(String item)
	{
		return _itemsInventory.get(item);
	}

	@Override
	public int getInventorySize()
	{
		return this._itemsInventory.size();
	}

	/**
	 * get inventory
	 */
	@Override
	public HashMap<String, Item> getInventory()
	{
		return this._itemsInventory;
	}
	
	/**
	 * 检查是否存在item
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
	 * add an item
	 */
	@Override
	public void addItem(Item item)
	{
		this._itemsInventory.put(item.getItemName(), item);
	}
	
	/**
	 * 关键词命令 take
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
	 * 命令关键词 drop
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
	 * 命令关键词给予 give
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
	 * 命令关键词 look
	 */
	public void look()
	{
		for (String str : getCurrentRoom().getDetails())
			AGame._out.println(str);
	}
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
	 * 命令给关键词 go room
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
     * set the total weight
     */
	public void setTotalWeight(int totalWeight)
	{
		this._totalWeight = totalWeight;
	}

	/**
	 * get the total weight
	 */
	public int getTotalWeight()
	{
		return this._totalWeight;
	}
	
	/**
	 * get the maw weight
	 */
	public int getMaxWeight()
	{
		return _MAX_WEIGHT;
	}
}
