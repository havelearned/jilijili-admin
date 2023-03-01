@echo off
title 启动jilijili-music.jar
color 03
mode con cols=40 lines=100
:: todo
cd D:\IDEA_Project\jilijili-music\target
java  -jar wang-jilijili-0.0.1-SNAPSHOT.jar -Xms512m -Xmx512m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError
pause