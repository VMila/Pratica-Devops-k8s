#!/bin/bash
set -e

echo ">>> Verificando o status do Minikube..."
if minikube status | grep -q "host: Running"; then
    echo ">>> Minikube já está em execução."
else
    echo ">>> Minikube não está rodando. Iniciando K8s..."
    minikube start
fi

echo ">>> Apontando o terminal para o ambiente Docker do Minikube..."
eval $(minikube -p minikube docker-env)

echo ">>> Construindo a imagem da aplicação 'bike-app'..."
docker build -t bike-app:latest .

echo ">>> Verificando as imagens disponíveis no Minikube..."
docker images | grep bike-app

echo ""
echo ">>> Processo concluído! A imagem 'bike-app:latest' está pronta para ser usada pelo K8s."

cd bikes-chart

echo ">>> Habilitando ingress."
minikube addons enable ingress

echo ">>> Esperando o Ingress Controller ficar pronto"
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=120s

echo ">>> Atualizando dependências do chart."
helm dependency update

echo ">>> Instalando bike-release."
helm upgrade bike-release . --install

echo ""
echo ">>> Build concluído!"
