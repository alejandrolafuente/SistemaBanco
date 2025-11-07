#!/bin/bash
# torne executavel com => $ chmod +x start.sh
code .

echo "Iniciando Sistema Banco..."

echo "Iniciando containers Docker..."
docker start bancoDB
docker start pgadmin-container

sleep 5

echo "Iniciando servidor..."
gnome-terminal --tab --title="Servidor" --working-directory="$PWD/bankserver" -- bash -c "./mvnw spring-boot:run -Dspring-boot.run.profiles=local; exec bash"

sleep 3

echo "Iniciando cliente..."
gnome-terminal --tab --title="Cliente" --working-directory="$PWD/front-end" -- bash -c "ng serve; exec bash"
