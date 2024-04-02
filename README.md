# NotesApp
## Descripción
Notas App es una aplicación Android desarrollada utilizando Kotlin que permite a los usuarios crear, leer, actualizar y eliminar notas. Este proyecto se estructura siguiendo Clean Architecture y utiliza MVVM como patrón de diseño de la arquitectura de la UI, además de implementar Room para la persistencia de datos, y Hilt para la inyección de dependencias.

## Características
- Crear, leer, actualizar y eliminar notas.
- Búsqueda de notas por título o contenido.
- Diseño responsivo que se adapta a diferentes tamaños de pantalla y orientaciones.
- Persistencia de datos local con Room.
- Uso de Clean Architecture para una mayor separabilidad de componentes y facilidad de testing.

## Tecnologías y Herramientas
- **Lenguaje de Programación**: Kotlin
- **Arquitectura de la Aplicación**: Clean Architecture, MVVM
- **Gestión de Dependencias**: Hilt
- **Base de Datos Local**: Room
- **Testing**: JUnit para pruebas unitarias

## Estructura del Proyecto
El proyecto se divide en las siguientes capas principales:

- `domain`: Contiene las entidades y casos de uso.
- `data`: Define las interfaces de los repositorios y sus implementaciones, además de las fuentes de datos.
- `presentation`: Incluye las actividades, fragmentos y ViewModels.
- `di`: Módulos para la configuración de la inyección de dependencias con Hilt.

## Cómo Ejecutar
Para ejecutar este proyecto, sigue estos pasos:

1. Clona este repositorio.
2. Abre el proyecto en Android Studio.
3. Ejecuta el proyecto en un emulador o dispositivo Android.

## Pruebas
- Para ejecutar las pruebas unitarias, navega a la carpeta `src/test` y ejecuta los tests.

## Capturas de Pantalla
![template](https://github.com/cvalera18/NotesApp/assets/57680708/f2f3eb45-8062-4657-bf20-67697c71f8ad) ![template (1)](https://github.com/cvalera18/NotesApp/assets/57680708/5ee8cf4b-f4af-4ce7-80b6-2eb2c67182ae) ![template (2)](https://github.com/cvalera18/NotesApp/assets/57680708/ac54741f-b8ab-4de6-adb0-e2e9e057af50) ![template (3)](https://github.com/cvalera18/NotesApp/assets/57680708/4d058ab9-6cb9-47ab-8c85-1f7e1829443a)



