## Aplicacion Hexagonal 

### Soporta la creación y búsqueda de usuarios

### Soporte de Base de Datos MySQL
```
docker run --name mysql-hexagonal -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=hexagonal -p 3306:3306 -d mysql:8.0
```
```
docker exec -it mysql-hexagonal mysql -uroot -ppassword
```

```
mvn flyway:migrate

mvn flyway:info

mvn flyway:clean

```