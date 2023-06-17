package Character;

import Game.Room;
import Item.Item;

import java.util.HashMap;
import java.util.List;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 *
 * 生成玩家，敌人等角色 ...
 * 
 * @author duoduo
 *
 */
public interface Character
{
	
	public void setName(String name);  // set player name
	public void setCurrentRoom(Room currentRoom);  // set the current room
	public void setTotalWeight(int totalWeight); // set the weight items wear the player
	
	public String getName(); // return the player name
	public Room getCurrentRoom(); // return the current room
	public Item getItemInventory(String item); // return an item from the inventory
	public int getInventorySize(); // return the size of the inventory
	public HashMap<String, Item> getInventory(); // return the entire inventory
	public int getTotalWeight(); // return the weight of items from the player
	public int getMaxWeight(); // return the max weight
	public List<String> getDetails(); // return details

	public void back();
	public void look(); //look around in the room
	public void addItem(Item item); // add an item in the inventory
	public void giveItem(String item, String person); // give an item at a Character
	public void dropItem(String item); // drop an item at a character
}
