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
- **Testing**: JUnit para pruebas unitarias (opcional: Espresso para pruebas de UI)

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
- Para pruebas de UI, navega a la carpeta `src/androidTest` y ejecuta los tests. (Si aplicable)

## Capturas de Pantalla
