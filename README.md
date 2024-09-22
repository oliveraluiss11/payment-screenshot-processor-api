# API para el Procesamiento de Capturas de Pantalla de Pagos

💡 Este proyecto tiene como objetivo desarrollar una API que permita a los usuarios enviar capturas de pantalla de pagos realizados a través de aplicaciones móviles. La API procesará las imágenes utilizando tecnología de Reconocimiento Óptico de Caracteres (OCR) para extraer información relevante sobre las transacciones y almacenarla en una base de datos en la nube. La información será devuelta al usuario en un formato seguro y encriptado.

## Objetivos

1. **Facilitar la Captura de Información**: Permitir a los usuarios enviar capturas de pantalla de sus transacciones de pago, simplificando la recolección de datos para procesos posteriores.
2. **Automatización del Procesamiento de Datos**: Utilizar OCR para extraer automáticamente información clave de las capturas de pantalla, eliminando la necesidad de entrada manual de datos.
3. **Almacenamiento Seguro y Eficiente**: Guardar los datos extraídos en una base de datos en la nube, garantizando su integridad y disponibilidad.
4. **Seguridad de la Información**: Asegurar que toda la información sensible sea encriptada antes de ser enviada al usuario, protegiendo la privacidad de los datos.

## Tecnologías Utilizadas

- **OCR**: Tecnología de Reconocimiento Óptico de Caracteres para extraer información de las imágenes.
- **MongoDB**: Base de datos en la nube para el almacenamiento de datos procesados.
- **Spring Framework**: Framework para desarrollar la API y manejar la lógica de negocio.
- **Encriptación**: Se utilizan algoritmos de encriptación para proteger los datos sensibles antes de ser devueltos al usuario.

## Diseño de la API

### Endpoints

| Método | Endpoint                      | Descripción                                     |
|--------|-------------------------------|-------------------------------------------------|
| POST   | /payments/screenshots         | Envía una captura de pantalla para procesamiento |
| POST   | /payments/screenshots/screenshot | Obtiene información de una captura encriptada   |
| GET    | /payments/screenshots         | Recupera la lista de capturas procesadas       |

### Headers y Request Parameters

| Método | Endpoint                      | Headers                                     | Request Parameters                          |
|--------|-------------------------------|---------------------------------------------|--------------------------------------------|
| POST   | /payments/screenshots         | `api-key`: Clave de API para autenticación | `file`: La imagen (MultipartFile)<br>`documentNumber`: Número de documento |
| POST   | /payments/screenshots/screenshot | `api-key`: Clave de API para autenticación | `encryptedValue`: Valor encriptado (en el cuerpo) |
| GET    | /payments/screenshots         | `api-key`: Clave de API para autenticación | N/A                                        |

### Responses

#### 1. **POST /payments/screenshots**

```json
{
  "encryptedResult": "8iwVfrfdI04wzOYRMUDJ/J...VjB849Kqb7QxJPFWhwMvyyla0xACxfm"
}
```
#### 2. **POST /payments/screenshots/screenshot**
```json
{
    "amount": 200.00,
    "currencyCode": "PEN",
    "transactionNumber": "77182527",
    "transactionDate": "20 Sep 2024 06:06 PM",
    "recipient": {
        "cellphone": "996057874",
        "wallet": "YAPE"
    },
    "sender": {
        "documentNumber": "77380599",
        "wallet": "PLIN"
    },
    "registrationDate": "2024-09-22T11:09:46.411"
}
```
#### 3. **POST /payments/screenshots/screenshot**
```json
{
    "screenshots": [
        {
            "encryptedResult": "8iwVfrfdI04wzOYRMUDJ/J...VjB849Kqb7QxJPFWhwMvyyla0xACxfm"
        }
    ]
}
```

## Autor

**Luis Olivera**  
Backend Developer Java  
[LinkedIn](https://www.linkedin.com/in/oliveraluis11/)

## Licencia

Este proyecto está licenciado bajo la **Licencia Creative Commons BY-NC-ND 4.0**.

### Resumen de la Licencia CC BY-NC-ND

- **Atribución**: Debes dar crédito al autor original.
- **No Comercial**: No puedes usar el material para fines comerciales.
- **Sin Derivados**: No puedes modificar el material.

Para más información, consulta [Creative Commons](https://creativecommons.org/licenses/by-nc-nd/4.0/).

