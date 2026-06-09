# PokeWorld

Es una aplicación para explorar los distintos Pokémon de la franquicia, junto con sus detalles, tipos y estadísticas.

### Stack Tecnológico
* **Frontend:** Kotlin + Jetpack Compose .
* **Backend:** Firebase Authentication (Manejo seguro de sesiones, registro de usuarios con `displayName` y login) y consumo de PokeAPI
* **Navegación:** Jetpack Compose Navigation.

## Requisitos para Clonar y Configurar

Para compilar este proyecto localmente desde Android Studio, es obligatorio vincular tu propio proyecto de Firebase para habilitar el inicio de sesión.

### Pasos en Firebase Console:
1. Ve a [Firebase Console](https://console.firebase.google.com/) e inicia sesión con tu cuenta de Google.
2. Haz clic en **Agregar proyecto** y nomúbralo exactamente: `PokeWorld`.
3. Una vez creado, añade una **Aplicación Android** al proyecto. 
4. Registra el nombre de paquete exacto de tu app (por ejemplo: `com.example.finalproject`).
5. Descarga el archivo generado **`google-services.json`**.
6. En el menú izquierdo de Firebase, ve a **Build > Authentication**, activa el método de inicio de sesión por **Correo electrónico/Contraseña** y guarda los cambios.

### Configuración en el Código:
Mueve el archivo descargado `google-services.json` a la raíz del módulo de tu aplicación en tu proyecto local (directorio `app/`).

```text
PokeWorld/
├── app/
│   ├── google-services.json  <-- ¡Pégalo aquí!
│   └── src/
└── build.gradle.kts
```
### Si solo deseas instalar el apk:
1. Ve a la sección de **Releases** en el lado derecho de este repositorio de GitHub.
2. Descarga el archivo `PokeWorld.v1.0.0.apk` de la última versión estable.
3. Instala eñ archivo en tu dispositivo Android.
4. Asegúrate de tener activa la opción "Permitir la instalación de aplicaciones de orígenes desconocidos" en los ajustes de seguridad de tu teléfono e instala el APK.
