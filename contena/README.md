# Contena Rest Api Server

![contena-image](../assets/img/contena-image.jpg)

### 사용 기술
* Docker
* Spring

## 실행 Command 정리

### Docker image build
```
docker build -t rest_api_server:latest
```
-t (tag): image의 tag를 설정하는 옵션.


### Docker image run on container
```
docker run -d -p 8080:8080 --name rest_api_server rest_api_server:latest
```
-d (deattach): container를 background에서 동작 시키는 옵션.

-p: 외부 포트와 연결될 포트 설정
    ex) 브라우저에서 8080으로 접근할때 해당 server로 리다이렉션 시키고 싶다면  `-p 8080:8080` 추가

--name: container의 이름을 설정한다.