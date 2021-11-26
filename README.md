# RealState V2

#### Repositorio del proyecto **REALSTATE** del módulo de Acso a Datos y Programación de Servicios y Procesos 2021-22. ####

</br>

### Descargar JDK 17 de la página de Oracle, y también descargar IntelliJ IDEA Community Edition. Para poner en marcha el proyecto, una vez se ha abierto en *IntelliJ IDEA*, es ir a la pestaña de Maven, a la derecha de la ventana, abrir la carpeta de Plugins, abrir el apartado de *spring-boot* y pulsar donde diga *spring-boot:run*
### La aplicación nos permite manejar todas las funcionalidades de una página de Venta/Alquiler/Obra Nueva de *Viviendas*(`Vivienda`), en las cuales podemos consultar si dichas *Viviendas* son ofrecidas por un *Usuario Propietario*(`UserRole=PROPIETARIO`) común, o si además están controladas por alguna *Inmobiliaria*(`Inmobiliaria`). Además, cuenta con la función de ver la cantidad de *intereses* que tiene una vivienda en particular. También se pueden crear *Usuario Gestor*(`UserRole=GESTOR`), que son los que se asocian a las inmobiliarias, y también se pueden crear *Usuario Admin*(`UserRole=ADMIN`).
</br>


## Entidades de la aplicación:
* ### Inmobiliaria
* ### Vivienda
* ### UserEntity
* ### Interesa


  
</br>

## <p>Funcionalidades de Inmobiliaria:</p> ##
#### Asociación @OneToMany con Vivienda
* Crear nueva Inmobiliaria para
* Obtener todas la InmobiliariaService
* Obtener los datos de una Inmobiliaria concreta
* Borrar una Inmobiliaria concreta
</br>

## <p>Funcionalidades de Vivienda:</p> ##

#### Asociación @ManyToOne hacia Inmobiliaria 
#### Asociación @ManyToOne hacia UserEntity
#### Asocioación @OneToMany hacia Interesa


* Crear una nueva vivienda, dando de alta un usuario propietario a la vez
* Mostrar todas las viviendas existentes
* Ver los datos de una vivienda en concreto
* Modificar los datos de una vivienda
* Borrar una vivienda
* Hacer que una vivienda quede gestionada por una inmobiliaria concreta
* Elimina la gestión de una vivienda por parte de una inmobiliaria

</br>

## <p>Funcionalidades de Propietario:</p> ##

#### Asociación @OneToMany hacia Vivienda, además es hija de (extiende a) *Persona* 

* Crear un nuevo propietario(esto se hará para la parte del formulario de Vivienda en la parte *Fronted*)
* Ver todos los propietarios existentes
* Mostrar todos los datos de un propietario
*  Borrar un propietario y además las viviendas que tiene asociadas
</br>

## <p>Funcionalidades de Interesado-Interesa:</p> ##

#### *Interesado*: Asociación @OneToMany hacia Vivienda, además es hija de (extiende a) *Persona*

#### *Interesa*: @ManyToOne hacia Vivienda
#### *Interesa*: @ManyToOne hacia Interesado


* Crear un nuevo interesado, que se interese por una vivienda concreta
* Crear un nuevo interés para una vivienda concreta, por parte de un interesado ya existente
* Eliminar el interés de un interesado por una vivienda concreta
* Obtener todos los Interesados
* Mostrar todos los datos de un interesado
* Obtener las 10 viviendas por las que se hayan interesado
</br> 


## Realizado por:
* ### Jaime Jiménez
