set PATH=\\merkur\jdk1.2.2\bin;D:\j2sdkee1.2.1\bin;.;%PATH%
rem set J2EE_HOME=<installation-location>
set java_home=\\merkur\jdk1.2.2
set J2EE_HOME=\\merkur\j2sdkee1.2.1
set CPATH=\\merkur\jdk1.2.2;%J2EE_HOME%\lib\j2ee.jar;.;wi-pc01CartAppClient.jar
java -Dorg.omg.CORBA.ORBInitialHost=merkur.fh-konstanz.de -classpath  "%CPATH%" CartClient 
