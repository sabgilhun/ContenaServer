#!/bin/bash

db_host="db"  
db_port=3306

#while ! nc $db_host $db_port; do  
#  >&2 echo "db is unavailable - sleeping"
#  sleep 5
#done

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar