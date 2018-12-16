# selecao-involves

Aplicação para verificar quais lojas cada funcionário consegue atender em um raio de no máximo 2 km.

# Pré requisitos

> node

> maven

> docker

> java

# Instalação

> Para perfeito funcionamento da aplicação, os passos a seguir devem ser rodados na sequência que foram descritos.

### 1 - Front-end

```
cd selecao-involves/localizacao-web/src/main/web
```

```
npm install
```

### 2 - Docker

```
cd selecao-involves/docker ou cd ../../../../docker/
```

```
docker-compose up -d
```
### 3 - Projeto

```
cd selecao-involves/ ou cd ../
```

```
mvn clean install
```

# Executar

```
cd selecao-involves/localizacao-server ou cd localizacao-server/
```
```
mvn spring-boot:run
```

http://localhost:8080/


# Testar

```
cd selecao-involves/ ou cd ../
```
```
mvn clean test
```

# Arquitetura

O Projeto consiste em dois módulos: Backend e Frontend

#### Backend
Backend em Java Spring-boot, com persistência em postgres (rodando no docker).
Os dados dos aquivos .CSV são carregados quando a aplicação sobe e salvos no banco de dados. Esse banco é criado quando a aplicação inicia e deletado quando a aplicação finaliza. (Para facilitar o desenvolvimento).
É utilizado a biblioteca Math do Java para os cálculos das funções trigonométricas.

#### Frontend
Frontend desenvolvido em Angular 6. 

> Integração com GoogleMaps

> Gerador de Avatar (http://avatars.adorable.io/)

> Interface visual utilizando AdmintLte

> Feature de inclusão de novo representante

# Implementações futuras

1. Rodar toda a aplição com docker e não somente o banco
2. Implementar teste no Frontend

# Screenshots

![alt text](https://github.com/didowscode/selecao-involves/blob/master/lista.png)

![alt text](https://github.com/didowscode/selecao-involves/blob/master/visualizar.png)

![alt text](https://github.com/didowscode/selecao-involves/blob/master/adicionar.png)


