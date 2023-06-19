package Item;

/**
 * 这个类是对Item类制定的接口
 * @author duoduo
 *
 */
public interface IItem
{
	public String getItemName();//返回物品的名称。实现类应该提供这个方法来获取物品的名称。
    public int getWeight();//返回物品的重量。实现类应该提供这个方法来获取物品的重量。
    public void print();//打印物品的信息。实现类应该提供这个方法来自定义物品信息的打印方式。
}
