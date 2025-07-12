#!/bin/bash

# Este script constrói a imagem da aplicação e a carrega no Docker do Minikube.

# Garante que o script pare se algum comando falhar
set -e

echo ">>> 1. Apontando o terminal para o ambiente Docker do Minikube..."
eval $(minikube -p minikube docker-env)

echo ">>> 2. Construindo a imagem da aplicação 'bike-app'..."
# O nome da imagem 'bike-app' deve ser o mesmo que você usará no seu Helm Chart.
# O '.' no final indica que o Dockerfile está no diretório atual.
docker build -t bike-app:latest .

echo ">>> 3. Verificando as imagens disponíveis no Minikube..."
# Lista as imagens para confirmar que 'bike-app' foi carregada com sucesso.
docker images | grep bike-app

echo ""
echo ">>> Processo concluído! A imagem 'bike-app:latest' está pronta para ser usada pelo seu cluster Kubernetes."