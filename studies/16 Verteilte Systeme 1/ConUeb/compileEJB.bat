set J2EE_HOME=C:\programme\sun\SDK
set CPATH=.;%J2EE_HOME%\lib\j2ee.jar
set PATH=C:\programme\java\jdk1.5.0_05\bin;%PATH%
javac -classpath %CPATH% ConverterEJB.java ConverterHome.java Converter.java
