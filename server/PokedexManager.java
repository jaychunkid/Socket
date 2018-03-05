package socket;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Map;

//负责对图鉴的管理，支持搜索操作
class PokedexManager {

	ArrayList<Pokemon> pokemonList;
	Map<String, Pokemon> nameIndex;
	Map<Integer, Pokemon> idIndex;

	public PokedexManager(ArrayList<Pokemon> list) {
		pokemonList = list;
		nameIndex = new Hashtable<>();
		idIndex = new Hashtable<>();
		ListIterator<Pokemon> iter = pokemonList.listIterator();
		// 读取Pokemon类的ArrayList，建立name-Pokemon的Map作为姓名索引
		while (iter.hasNext()) {
			Pokemon tmp = iter.next();
			nameIndex.put(tmp.getName(), tmp);
			idIndex.put(Integer.parseInt(tmp.getID().substring(1)), tmp);
		}
	}

	// 针对pm名字的搜索
	synchronized public boolean search(String name, Pokemon pm) {
		if (nameIndex.containsKey(name)) {
			Pokemon tmp = nameIndex.get(name);
			// 如果找到pm则对传入的pok对象赋该pm的信息
			pm.set(tmp.getID(), tmp.getName(), tmp.getType(), tmp.getEvolution(), tmp.getEvoluteWay(),
					tmp.getNextEvolution());
			return true;
		} else
			return false;
	}

	// 针对pm全国图鉴编号的搜索
	synchronized public boolean search(Integer id, Pokemon pm) {
		if (idIndex.containsKey(id)) {
			Pokemon tmp = idIndex.get(id);
			// 如果找到pm则对传入的pok对象赋该pm的信息
			pm.set(tmp.getID(), tmp.getName(), tmp.getType(), tmp.getEvolution(), tmp.getEvoluteWay(),
					tmp.getNextEvolution());
			return true;
		} else
			return false;
	}
}
