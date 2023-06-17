package cn.edu.whut.sept.zuul.Item;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class hold an enumeration of all items in the game.
 * It is used to create some items with  ...
 * 
 * @author duoduo
 *
 */
public class Item implements IItem
{
	// 物品名称
	private final String _name;
	// 物品重量
	private final int _itemWeight;
	
	/**
	 * 构造函数
	 * @param name
	 * @param itemWeight
	 */
	public Item(String name, int itemWeight)
	{
		this._name = name;
		this._itemWeight = itemWeight;
	}

	/**
	 * 获取物品名称
	 */
	public String getItemName()
	{
		return this._name;
	}
	
	/**
	 * 获取物品重量
	 */
	public int getWeight()
	{
		return this._itemWeight;
	}

	public void print() { }

}
