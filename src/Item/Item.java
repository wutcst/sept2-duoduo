package Item;

/**
 * 
 * @author duoduo
 *
 */
public class Item implements IItem
{
	// Name of the item
	private final String _name;
	// Weight of the item
	private final int _itemWeight;
	
	/**
	 * constructor
	 * @param name
	 * @param itemWeight
	 */
	public Item(String name, int itemWeight)
	{
		this._name = name;
		this._itemWeight = itemWeight;
	}

	/**
	 * get the name
	 */
	public String getItemName()
	{
		return this._name;
	}
	
	/**
	 * get the weight
	 */
	public int getWeight()
	{
		return this._itemWeight;
	}

	public void print() { }

}
