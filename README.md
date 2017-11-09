## Akka Http Example

### Start service command
```
$ sbt run
```

### Test service command
```
curl -X GET http://localhost:8080/hello
```

### How to build jar file?
```
# sbt package copyJar
```

### How to use the command line to running akka web server?
```
# cd target
# java -cp .:./lib/*:akka-http-example_2.11-0.1-SNAPSHOT.jar idv.jack.akka.http.WebServer
```

### Add cliect connect to server command
```
$ curl -H "Content-Type: application/json" --data '{"name":"jack","favoriteNumber":1}' -X POST http://localhost:8080/testjson
```
