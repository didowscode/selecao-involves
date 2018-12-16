# selecao-involves

Aplicação para verificar quais lojas cada funcionário consegue atender em um raio de no máximo 2 km.

# Instalação

> Para perfeito funcionamento da aplicação, os passos a seguir dever ser rodas na sequência que foram descritos.

### 1 - Front-end

```
cd selecao-involves/localizacao-web/src/main/web
```

```
npm install
```

### 2 - Docker

```
cd selecao-involves/docker
```

```
docker-compose up
```
### 3 - Projeto

```
cd selecao-involves/
```

```
mvn clean install
```

# Executar

```
cd selecao-involves/localizacao-server
```
```
mvn spring-boot:run
```

# Testar

```
cd selecao-involves/
```
```
mvn clean test
```

# Arquitetura

Backend em Java Spring-boot, com persistência em postgres (rodando no docker).

Frontend em Angular 6, com integração com GoogleMaps.


