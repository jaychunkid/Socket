## Socket练习
大二上学期java课程socket与多线程的练习，一个简单的精灵宝可梦图鉴。服务器读取文本文件中的图鉴信息，并生成图鉴资料。客户端支持通过pm的名字或全国图鉴编号查找pm的信息。服务器应用多线程技术，支持多个客户端同时访问。<br>
* `Pokemon.java`: pm类，包含pm的编号、名字、属性、进化信息等。<br>
* `FileScanner.java`: 文件读取类，从文件从读取信息，并生成pm类链表。<br>
* `PokedexManager.java`: 图鉴信息管理类，提供检索功能。<br>
* `PokedexClient.java`,`ClientInterface.java`：服务器的逻辑功能类与UI类。<br>
* `PokedexServer.java`,`PokedexInterface.java`：客户端的逻辑功能类与UI类。<br>

## Socket Practice
This project is a simple online pokedex. The server scans the text document to get the information of pm, and generate pokedex data. The client could request the pm information with its name or id. What's more, the server support multiple clients with multithreading technology.<br>
* `Pokemon.java`: A set of pm's information, including id, name, type, evolution information and so on.<br>
* `FileScanner.java`: Provide method `start()` which can scan the data in documents and generate a list of pms.<br>
* `PokedexManager.java`: Manager the pokedex data and provide method to search for the pm with its id or name.<br>
* `PokedexClient.java`,`ClientInterface.java`: Client implementations.<br>
* `PokedexServer.java`,`ServerInterface.java`: Server implementations.<br>
