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
public class Player implements Character//实现接口的功能
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
		this._itemsInventory = new HashMap<>();//创建哈希表存贮仓库这个变量
	}
	   
	/**
	 * 设置名字
	 */
	@Override
	public void setName(String name)
	{
		this._name = name;//转递name这个变量
	}

	/**
	 * 得到名字
	 */
	@Override
	public String getName()
	{
		return this._name;//返回name
	}
	
	/**
	 * 设置当前房间
	 */
	@Override
	public void setCurrentRoom(Room currentRoom)
	{
		this._currentRoom = currentRoom;//转递当前房间变量
	}

	/**
	 * 得到当前房间
	 */
	@Override
	public Room getCurrentRoom()//room类型
	{
		return this._currentRoom;//返回当前房间变量
	}
	
	//得到上一个方向
	public String get_lastdirection()
	{
		return this._lastdirection;//返回上一个方向
		}

	/**
	 * 得到详细信息
	 */
	@Override
	public List<String> getDetails()
	{
		return getCurrentRoom().getDetails();//返回得到当前房间的得到的信息
	}

	/**
	 * 得到仓库的物品
	 */
	@Override
	public Item getItemInventory(String item)
	{
		return _itemsInventory.get(item);//返回得到仓库物品
	}
    //得到仓库大小
	@Override
	public int getInventorySize()
	{
		return this._itemsInventory.size();//返回物品仓库大小
	}

	/**
	 * 得到仓库
	 */
	@Override
	public HashMap<String, Item> getInventory()
	{
		return this._itemsInventory;//返回物品仓库
	}
	
	/**
	 * 检查是否存在物品item
	 * @param desc
	 * @return
	 */
	public boolean hasItem(String desc)//判断仓库里是否有这个物品
	{
		return this._itemsInventory.containsKey(desc);
	}

	/**
	 * 检查当前总重加上物品是否超重
	 * @param item
	 * @return
	 */
	public boolean tooHeavy(Item item)//判断物品是否超重
	{
		return (item.getWeight() + this._totalWeight > getMaxWeight());
	}

	/**
	 * 添加物品
	 */
	@Override
	public void addItem(Item item)
	{
		this._itemsInventory.put(item.getItemName(), item);//添加物品到当前物品仓库
	}
	
	/**
	 * take命令实现
	 * @param desc
	 */
	 public void take(String desc)
	    {//如果转递的物品不在当前房间
	        if (!getCurrentRoom().containsItem(desc))
	        {
	            AGame._out.println(desc + " " + AGame._messages.getString("room"));//显示该物品不在房间
	            return;
	        }
	        Item item = getCurrentRoom().getItem(desc);//实例化让item指向当前房间得到的物品
	        if (tooHeavy(item))//如果转递的物品超重
	        {
	            AGame._out.println(desc + " " + AGame._messages.getString("heavy"));//显示该物品超重量
	            return;
	        }
	        item = getCurrentRoom().removeItem(desc);
	        this._itemsInventory.put(desc, item);//物品仓库加入该物品
	        
	        this._totalWeight += item.getWeight();//总重累加
	    }
	
	 /**
		 * drop命令实现
		 * @param name
		 */
		@Override
		public void dropItem(String name)
		{
			if (!hasItem(name))//如果没有这个物品
			{
				AGame._out.println(AGame._messages.getString("dontHave") + " " + name);//显示没有这个物品
	            return;
			}
	        Item item = this._itemsInventory.remove(name);//物品仓库移除该物品
	        this._totalWeight -= item.getWeight();//总重累加
	        this._currentRoom.addItem(name, item);//当前房间增加该物品
		}
		
		/**
		 * give命令实现
		 * @param desc
		 */
		@Override
	    public void giveItem(String desc, String character)
		{//如果当前房间没有该人物
	        if (!this._currentRoom.hasCharacter(character))
	        {
	            AGame._out.println(character + " " + AGame._messages.getString("room"));//显示该人物不在该房间
	            return;
	        }//如果物品仓库没有包含该物品
	        if (!this._itemsInventory.containsKey(desc))
	        {
	            AGame._out.println(AGame._messages.getString("room") + " " + desc);//显示该物品不在该房间
	            return;
	        }//如果当前人物得到的物品重量加上仓库得到的重量大于人物得到物品的最大总重量
	        if (this._currentRoom.getCharacter(character).getTotalWeight() + this._itemsInventory.get(desc).getWeight() > this._currentRoom.getCharacter(character).getMaxWeight())
	        {
	        	AGame._out.println(character + " " + AGame._messages.getString("heavy") + " " + desc);//显示该人物得到的物品超重
	            return;
	        }
	        Item item = this._itemsInventory.remove(desc);
	        this._totalWeight -= item.getWeight();//总重累减
	        this._currentRoom.getCharacter(character).addItem(item); //当前房间增加该物品       
	    }
	/**
	 * look命令实现
	 */
	public void look()
	{
		for (String str : getCurrentRoom().getDetails())//输出该房间的信息
			AGame._out.println(str);
	}
	//back命令实现
	public void back(){
		String newdirection = null;
		switch (_lastdirection){
			case "west":newdirection="east";break;//西变东
			case "east":newdirection="west";break;//东变西
			case "south":newdirection="north";break;//南变北
			case "north":newdirection="south";break;//北变南

		}
		Room Backroom=getCurrentRoom().getExit(newdirection);
		setCurrentRoom(Backroom);//调用方法转递backroom变量
		_lastdirection=null;
		look();

	}
	
	/**
	 * go room实现
	 * @param direction
	 */
    public void goRoom(String direction)
    {
        Room nextRoom = getCurrentRoom().getExit(direction);//指向当前房间的出口

        if (nextRoom == null)//如果下一个房间为空
        {
            AGame._out.println(AGame._messages.getString("door"));//输出下一个门不存在
        }
        else{
            setCurrentRoom(nextRoom);
			_lastdirection=direction;
            look();
            //如果得到的当前房间的描述符合TProom
			if(Objects.equals(getCurrentRoom().getDescription(), "TProom and you will tp to a random room")){

				String destination = null;
				Random ran1=new Random();
				int count=ran1.nextInt(4);
				switch(count){
					case 0:destination="east";break;//0的话反向是东
					case 1:destination="west";break;//1的话反向是西
					case 2:destination="north";break;//2的话是北
					case 3:destination="south";break;//3的话是南
				}
				Room TPRoom = getCurrentRoom().getExit(destination);//TProom指向的是destination
				setCurrentRoom(TPRoom);//设置的房间是TProom
				_lastdirection=null;
				look();

			}

    }}

	
    /**
     * 设置总重量
     */
	public void setTotalWeight(int totalWeight)
	{
		this._totalWeight = totalWeight;//转递totalWeight变量
	}

	/**
	 * 得到总重量
	 */
	public int getTotalWeight()
	{
		return this._totalWeight;//返回totalWeight
	}
	
	/**
	 * 得到最大总量
	 */
	public int getMaxWeight()
	{
		return _MAX_WEIGHT;//返回最大尺寸
	}
}
