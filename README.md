# Profile Thumbnailer

Microsserviço responsável por gerar thumbnails das fotos de perfil dos zuppers. As imagens geradas recebem a marca d'água da Zup. Ela foi construída
usando as seguintes tecnologias:

- Java 11;
- Spring Boot 2.5.x;
- Thumbnailator 0.4.14;
- Maven;

## Ambiente de desenvolvimento

Para configurar o ambiente basta seguir os passos:

1. Clonar o repositório ou fazer seu download:

```shell
git clone https://github.com/zup-academy/treinamento-troubleshooting-projetos
cd profile-thumbnailer
```

2. Importar o projeto na IDE IntelliJ;

3. Iniciar a aplicação via IDE ou linha de comando:

```shell
./mvnw spring-boot:run
``` 

5. Consumir a API REST da aplicação via url http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ .

## Ambiente de produção

1. Execute o build da aplicação para gerar o executável (JAR):
```shell
./mvnw clean package
```

2. Copie o JAR para o ambiente de produção (encontra-se na pasta `target` gerada pelo Maven);

3. Execute a aplicação informando a configuração do banco de dados de produção via variáveis de ambiente:
```
java -server -Xms256m -Xmx256m -jar profile-thumbnailer-1.0.1.jar
```

## Duvidas e suporte

Basta entrar em contato com a equipe da Zup Edu no horário comercial. 