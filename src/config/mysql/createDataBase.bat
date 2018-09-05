REM open with text editor to open/edit in eclipse
REM open with system editor to launch
cd /d "%~dp0"
set MYSQL_HOME=C:\Program Files\MariaDB 10.3
"%MYSQL_HOME%/bin/mysql" -u root -p < createDataBase.sql
pause
