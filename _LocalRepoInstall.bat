@echo off
cd %~dp0
mvn clean package install
pause