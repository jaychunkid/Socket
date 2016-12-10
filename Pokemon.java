package socket;

//存放每种pm的信息
class Pokemon {

	private String id;  //pm全国图鉴编号
	private String name;  //pm简体译名
	private String type;  //pm属性
	private String evolution;  //pm的进化阶段
	private String evoluteWay;  //进化到该pm的方式，如果pm未进化则为“0”
	private String nextEvolution;  //进化到下一阶段的方式，如果没有下一阶段则为“无”

	public Pokemon() {
		id = null;  
		name = null;
		type = null;
		evolution = null;
		evoluteWay = null;
		nextEvolution = null;
	}

	public Pokemon(String num, String nam, String typ, String evo, String evw, String nev) {
		set(num, nam, typ, evo, evw, nev);
	}

	public void set(String num, String nam, String typ, String evo, String evw, String nev) {
		id = num;
		name = nam;
		type = typ;
		evolution = evo;
		evoluteWay = evw;
		nextEvolution = nev;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getEvolution() {
		return evolution;
	}

	public String getEvoluteWay() {
		return evoluteWay;
	}

	public String getNextEvolution() {
		return nextEvolution;
	}
}
