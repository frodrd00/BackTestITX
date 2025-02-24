
# Back end test

## Para probar la app

* Tener docker instalado
* Descargar el proyecto de https://github.com/dalogax/backendDevTest y dentro de la carpeta de backendDevTest ejecutar:
  * docker-compose up -d simulado influxdb grafana
* Probar los endpoints de la app contra http://localhost:5000/product/{productId}/similar
  * Por ejemplo http://localhost:5000/product/2/similar