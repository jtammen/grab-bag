rem set J2EE_HOME=<installation-location>
set J2EE_HOME=\\merkur\\j2sdkee1.2.1
set PATH=\\merkur\jdk1.2.2\bin;%PATH%
set CPATH=.;%J2EE_HOME%\lib\j2ee.jar

javac  -classpath %CPATH% CartClient.java
