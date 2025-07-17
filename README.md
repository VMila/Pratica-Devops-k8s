# Para rodar
No diretório bikes-rent:

```  Shell
chmod +x build-local.sh
./build-local.sh
```  

Com um arquivo .env contendo:
``` Shell
DB_HOST=db
DB_USER=user
DB_PASSWORD=password
DB_PORT=3306
DB_NAME=bikeRentSystem
```  

Para acessar a aplicação em http://k8s.local para a aplicação base e http://mail.k8s.local para o serviço de e-mail Mailhog

Alternativamente, em sistemas Windows só consegui executar a aplicação utilizando os comandos: 
``` Shell
kubectl port-forward svc/bike-release-bike-app-service 8080:8080
kubectl port-forward svc/bike-release-mailhog-service 8025:8025
``` 

Para depois acessar a aplicação em http://localhost:8080 e http://localhost:8025 

# Observação

É necessário configurar o host da sua aplicação local
Para isso, obtenha o ip minikube com:
``` Shell
minikube ip
```
Adicione o ip obtido no arquivo com 
``` Shell
{ip obtido} k8s.local
{ip obtido} mail.k8s.local
```
