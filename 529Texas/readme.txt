***开启服务器:
在目录  520Texas\Server\下开启命令行
>>>java -cp "bin;json-simple-1.1.jar;sqlite-jdbc-3.30.1.jar" Server

编译
在目录  520Texas\Server\
>>>javac -cp "bin;json-simple-1.1.jar;sqlite-jdbc-3.30.1.jar"  -d bin src\*.java


***开启图形界面：
在目录 520Texas\GUI下开启命令行
>>>java -cp "bin;json-simple-1.1.jar" LoginUI

编译
在目录  520Texas\GUI\
>>>javac -cp "bin;json-simple-1.1.jar" -d bin src\*.java

jar cvfm Texas.jar MANIFEST.MF *.class
jar cvfm Server.jar MANIFEST.MF *.class