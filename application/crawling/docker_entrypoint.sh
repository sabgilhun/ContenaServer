#!/bin/bash

db_host="db"  
db_port=3306

rest_host="rest-api"
rest_port=8081

while ! nc $db_host $db_port; do  
  >&2 echo "db is unavailable - sleeping"
  sleep 5
done

while ! nc $rest_host $rest_port; do  
  >&2 echo "rest_api is unavailable - sleeping"
  sleep 5
done