## x-consistent-hash

```shell
# 플러그인 사용을 위해 bash 접속
docker exec -it rabbitmq bash

# rabbitmq 플러그인 리스트 확인
rabbitmq-plugins list

# rabbitmq_consistent_hash_exchange 사용 설정
rabbitmq-plugins enable rabbitmq_consistent_hash_exchange

```

rabbitmq 에는 x-consistent-hash 라는 Exchange Type 이 있다.

아래의 경우에 사용할 수 있다.
- Exchange 에 연결된 Queue 가 여러개 존재하고
- 각 Queue 에는 동일한 라우팅 키만을 가진 메시지들을 위치시키고 싶은 경우.

예를들어,

"A" Exchange 에는 아래의 Queue 들이 바인딩되어 있다.

"A-1" Queue  
"A-2" Queue  
"B-1" Queue  

"A-1" Queue 에는 아래의 라우팅 키로 보내진 메세지들만 존재한다.  
"A-1-key"

"A-2", "B-1" Queue 에는 아래의 라우팅 키로 보내진 메세지들만 존재한다.  
"A-2-key"  
"B-1-key"
