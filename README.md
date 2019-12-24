# Contena Server

![contena-image](./assets/img/contena-image.jpg)

* Docker
* Cron
* Python3

## Docker Command 정리
### Docker image build
```
docker build -t new_arrival_crawling:latest
```
-t (tag): image의 tag를 설정하는 옵션.


### Docker image run on container
```
docker run -d --name new_arrival_job new_arrival_crawling:latest
```
-d (deattach): container를 background에서 동작 시키는 옵션.

--name: container의 이름을 설정한다.


### Run bash on docker container (connect container)
```
docker exec -t -i new_arrival_job /bin/bash
```
-t - i(tty, interactive): contatiner의 bash에 커맨드를 넣고 응답을 보기 위한 옵션.
