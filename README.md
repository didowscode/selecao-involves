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

# Considerações futuras

1. Rodar toda a aplição com docker e não somente o banco
2. Implementar teste no Frontend



