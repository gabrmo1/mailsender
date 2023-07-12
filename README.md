# MailSender

## Configuração e Execução

### Com Docker-compose

#### Requisitos: Jdk17, maven, docker

Se você tiver o Docker instalado e atender aos requisitos, siga os passos abaixo para executar o projeto:

1. Certifique-se de que as portas **8080, 5173 e 5432** estejam abertas e desocupadas.
2. Abra um terminal e navegue até a raíz do projeto.
3. Gere um jar do backend utilizando o seguinte comando na pasta backend:
   * Na pasta backend execute o comando:
   ```bash
   mvn clean package -DskipTests
   ```

4. Instale as dependências do frontend:
   *  Na pasta frontend execute o comando:
   ```bash
   npm install
   ```

5. Na raiz do projeto, execute o seguinte comando para iniciar os containers do backend, frontend e banco de dados:
   * Na pasta raiz do projeto execute o comando:
    ```bash
    docker-compose up
    ```

### Sem Docker-compose

#### Requisitos: NodeJS, Jdk17, maven, Banco de dados

Se você preferir executar o projeto sem o Docker-compose, garanta que seu sistema atenda aos requisitos e siga os passos
abaixo:

1. Abra o terminal e navegue até o diretório raiz do projeto.
2. altere o arquivo de configuração de banco de dados no
   backend ([application.yml](backend/src/main/resources/application.yml)) para as configurações da sua preferência.
3. Inicie o backend:
    * Na pasta backend execute o comando:
   ```bash
   mvn spring-boot:run
   ```
    * Obs: Certifique-se de que a conexão com o banco de dados foi estabelecida antes de continuar.

4. Instale as dependências do frontend e execute o serviço:
* Na pasta frontend execute os comandos:
    ```bash
    npm install
    npm run dev
    ```

## Instruções de uso

Ao iniciar corretamente a aplicação, haverá um schedule que executará o envio das notícias cadastradas no dia anterior
para todos os clientes, mas estes também receberão as notícias postadas no site do ibge.

Após o cliente realizar o cadastro, automaticamente ele recebe as notícias do dia.

Conselho de uso: Cadastre primeiramente uma notícia, depois, no cadastro do cliente, informe um e-mail válido e depois
confira sua caixa de entrada.

## Contribuição

Fique à vontade para contribuir com este projeto. Você pode enviar pull requests para correções de bugs, melhorias e novas funcionalidades.
