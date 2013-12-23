#!/bin/bash

export JAVA_HOME=/home/fqa/java/
DBNAME=plf
DBUSER=plf 
DBPASS=plf 

echo "Open Mysql Environment"
#mysql -uroot -pexo -e "CREATE USER '$DBUSER'@'localhost' IDENTIFIED BY '$DBPASS'; GRANT ALL PRIVILEGES ON *.* TO '$DBUSER'@'localhost' WITH GRANT OPTION;FLUSH PRIVILEGES;"
mysql -uroot -pexo -e "GRANT ALL ON *.* TO '$DBUSER'@'localhost' IDENTIFIED BY '$DBPASS';"
mysql -uroot -pexo -e "DROP DATABASE IF EXISTS $DBNAME;CREATE DATABASE IF NOT EXISTS $DBNAME; GRANT ALL PRIVILEGES ON $DBNAME.* TO '$DBUSER'@'localhost' IDENTIFIED BY '$DBPASS';FLUSH PRIVILEGES;"
