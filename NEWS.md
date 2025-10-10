# Historial de Cambios

## [No Publicado] - 2025-10-06

### Estructura de Archivos y sus Relaciones

1. **`User.java` (Modelo de Dominio)**
   - Agregado enum `Role` con valores: ADMIN, MONITOR, USER
   - Relacionado con: `UserEntity` (mapeo a entidad de persistencia)

2. **`UserEntity.java` (Entidad de Persistencia)**
   - Mapea la tabla de usuarios en la base de datos
   - Incluye la columna `role` para almacenar el rol del usuario
   - Se relaciona con el modelo de dominio a través de `UserMapper`
   - Cambios en la base de datos:
     - Se requiere una migración para agregar la columna `role` (VARCHAR) a la tabla de usuarios
     - La columna debe permitir valores nulos o tener un valor por defecto
     - Se recomienda crear un índice para búsquedas por rol si es un campo de consulta frecuente

3. **`UserRepository.java` (Puerto de Salida)**
   - Interfaz que define los contratos del repositorio
   - Relacionado con: `UserRepositoryAdapter` (implementación) y `User` (modelo de dominio)
   - Cambios principales:
     - Modificado `findByDni` para devolver `List<User>`
     - Agregado `findAll()`

4. **`UserJpaRepository.java` (Repositorio JPA)**
   - Extiende `JpaRepository` para operaciones CRUD
   - Relacionado con: `UserEntity` (entidad JPA)
   - Métodos implementados:
     - `findByEmail(String email)`
     - `findByDni(String dni)`
     - `findByLastName(String lastName)`
     - `findByAgeLessThan(int age)`

5. **`UserRepositoryAdapter.java` (Adaptador de Repositorio)**
   - Implementa `UserRepository`
   - Relacionado con: `UserJpaRepository` y `UserMapper`
   - Se encarga de convertir entre `User` (dominio) y `UserEntity` (persistencia)

### Mejoras
- Se agregó soporte para roles de usuario (ADMIN, MONITOR, USER) en la clase `User`
- Se implementaron nuevos métodos en el repositorio para búsquedas avanzadas

### Cambios
- Se modificó el tipo de retorno de `findByDni` para que devuelva `List<User>` en lugar de `Optional<User>`
- Se estandarizaron los tipos de retorno en las interfaces del repositorio
- Se agregó el método `findAll()` para obtener todos los usuarios

### Correcciones
- Se corrigió la inconsistencia en los tipos de retorno entre `UserRepository` y `UserJpaRepository`
- Se agregaron los imports faltantes en las clases del repositorio

### Nuevas Características
- Se implementó búsqueda por apellido (`findByLastName`)
- Se implementó búsqueda por DNI (`findByDni`)
- Se implementó búsqueda por edad menor a (`findByAgeLessThan`)
