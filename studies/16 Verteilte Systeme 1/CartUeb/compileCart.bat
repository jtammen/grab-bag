rem set J2EE_HOME=<installation-location>
set J2EE_HOME=\\merkur\j2sdkee1.2.1
set CPATH=.;%J2EE_HOME%\lib\j2ee.jar
set PATH=\\merkur\jdk1.2.2\bin;%PATH%
javac -classpath %CPATH% CartEJB.java CartHome.java Cart.java
