
Start mongo by docker
```bash
docker pull mongo
docker run -p 27017:27017 --name mongo-local -d mongo:latest
```