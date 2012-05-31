set J2EE_HOME=C:\programme\sun\SDK
set PATH=C:\programme\java\jdk1.5.0_05\bin;%PATH%
set CPATH=.;%J2EE_HOME%\lib\j2ee.jar

javac  -classpath %CPATH% ConverterClient.java
