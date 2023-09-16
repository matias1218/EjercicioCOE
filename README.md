## Notas importantes:
Los endpoints creados para este ejercicio son los siguientes:

1.	localhost:8081/login
2.	localhost:8081/newUser
3.	localhost:8081/getUsers
4.	localhost:8081/getPhones
5.	localhost:8081/newPhone


El enpdoint para crear un usuario está protegido. Esto significa que solo se puede crear bajo un usuario autenticado con los permisos necesarios.
Para deshabilitar esta medida y liberar el endpoint, solo hay que comentar la línea 63 de la clase “SecurityConfig.java”

Bajo este supuesto, si se registra un usuario, los campos token y last_login vendrán nulos debido a que no realizan el proceso de autenticación 
(son procesos separados). El campo token solamente será devuelto una vez que el usuario se autentique con usuario y contraseña a través del primer 
endpoint, con el request mostrado a continuación.

```javascript
{
  "username": "username",
  "password": "password"
}
```

Para poder acceder a los endpoints 3,4 y 5 se debe ingresar el Bearer Token obtenido tras la autenticación como Headers en las consultas.
