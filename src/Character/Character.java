package Character;

import Game.Room;
import Item.Item;

import java.util.HashMap;
import java.util.List;

/**
 * 此个类是“World of Zuul”应用程序的一部分。
 * "World of Zuul"是一款非常简单的基于文本的冒险游戏。
 *
 * 生成玩家，敌人等角色 ...
 * 
 * @author duoduo
 *
 */
public interface Character//定义接口
{
	
	public void setName(String name);  // 设置玩家名称
	public void setCurrentRoom(Room currentRoom);  // 设置当前房间
	public void setTotalWeight(int totalWeight); // 设置总重量
	
	public String getName(); // 得到名字
	public Room getCurrentRoom(); // 得到当前房间
	public Item getItemInventory(String item); // 得到来自仓库的物品
	public int getInventorySize(); // 得到仓库的大小
	public HashMap<String, Item> getInventory(); // 得到仓库
	public int getTotalWeight(); // 得到总重量
	public int getMaxWeight(); // 得到最大重量
	public List<String> getDetails(); // 得到详细信息

	public void back();//回退上一个房间
	public void look(); //看房间信息
	public void addItem(Item item); // 添加物品到仓库
	public void giveItem(String item, String person); // 给人物物品
	public void dropItem(String item); // 丢弃物品
}
