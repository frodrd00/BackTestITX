
# Back end test

## Para probar la app

* Tener docker instalado
* Descargar el proyecto de https://github.com/dalogax/backendDevTest y dentro de la carpeta de backendDevTest ejecutar:
  * docker-compose up -d simulado influxdb grafana
* Probar los endpoints de la app contra http://localhost:5000/product/{productId}/similar
  * Por ejemplo http://localhost:5000/product/2/similar
    la respuesta serán los productos similar al producto 2
    ```json
    [{"id":"3","name":"Blazer","price":29.99,"availability":false},{"id":"100","name":"Trousers","price":49.99,"availability":false},{"id":"1000","name":"Coat","price":89.99,"availability":true}]
    ````