<h1>KPJ final homework</h1>

<h3>Steps:</h3>

1. build the app and docker image:

```
mvn clean install
```

2. run docker image:

```
docker-compose up -d
```

<b>RabbitMQ address</b> is being loaded from the environment variable: <i>RABBIT_ADDRESS</i>,
if it's null then the default address should be loaded: <i>amqp://guest:guest@host.docker.internal:5672</i>

<b>Service exposed port</b> can be changed in the docker-compose.yml file, just change the value of <i>service_exposed_port</i> parameter if needed.