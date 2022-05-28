<h1>KPJ final homework</h1>

<h3>Steps:</h3>
1. run docker

2. build the app and docker image:

```
mvn clean install
```

3. run docker image:

```
docker-compose up -d
```

<b>RabbitMQ address</b> is being loaded from the environment variable: <i>RABBIT_ADDRESS</i>,
example value:  <i>amqp://guest:guest@host.docker.internal:5672</i>

<b>SERVICE_NAME</b>, <b>SERVICE_EXPOSED_PORT</b>, <b>RABBITMQ_FANOUT_NAME</b> and <b>RABBITMQ_QUEUE_NAME</b> values can be changed in the <i>.env</i> file