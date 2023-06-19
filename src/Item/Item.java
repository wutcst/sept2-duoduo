package Item;

/**
 * Item类用于例举出在游戏中所有的物品
 *  @author duoduo
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
	public Item(String name, int itemWeight)//接受物品的名称和重量作为参数，并在对象创建时对 name 和 itemWeight 进行赋值。
	{
		this._name = name;
		this._itemWeight = itemWeight;
	}

	/**
	 * 获取物品名称
	 */
	public String getItemName()//实现了 IItem 接口中的 getItemName() 方法，返回物品的名称
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

	public void print() { }//实现了 IItem 接口中的 print() 方法

}
