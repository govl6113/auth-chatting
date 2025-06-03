### chat

###### HTTP는 연결을 지속하지 않고, 클라이언트가 서버로 단방향 요청만 가능하다.

###### websorket은 statefull한 성격으로 실시간 통신이 필요한 채팅에서 hankshaking 과정을 덜어줌. but. 현재 누가 접속중인지 알아야 함. (채팅방마다 세션 관리 필요)

###### websocket과 STOMP 차이

| 질문                         | 답변                                                                                                                                         |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| STOMP와 WebSocket 차이는?      | WebSocket은 저수준 양방향 통신 (직접 send/receive 처리), STOMP는 Pub/Sub 구조 포함한 websocket 위의 고수준 메시징 프로토콜 (/topic/chat 구독, /app/message 전송 등 간단하게 구성 가능) |
| STOMP에서 pub/sub은 누가 제공?    | STOMP + Spring에서 내장 제공함. 직접 구현할 필요 없음                                                                                                      |
| WebSocket만 사용할 때 pub/sub은? | 직접 구현해야 함 (메시지 라우팅, 토픽 구독 처리 등)                                                                                                            |

###### STOMP(Simple Text Oriented Message Protocol)는 websocket위에서 pub/sub 방식으로 동작하며 채팅 과정을 쉽게 만들어줌. stomp는 구독만 하면 알아서 보내줌. 기본 브로커는 in-memory 기반이기 때문에 application 다운되면 데이터 날아감 + broker 메세지 유실할 수 있음.

###### 따라서 외부 broker를 사용해야 함.

MSA
Client → STOMP(WebSocket) → Server A <br>
Server A : 외부 broker publish <br>
Server B : 외부 broker subscribe → WebSocket으로 클라이언트에게 전달

"WebSocket으로 클라이언트에게 전달" 자세한 과정<br>
메시지를 받은 B 서버 → "이 메시지를 누구에게 보내야 하지?"<br>
→ 그 사용자 세션이 MSA에서 어떤 서버에 연결되어 있는지를 알아야 함 <br>
→ Redis 등 공유 저장소에 세션 정보를 저장해야 함<br>
→ 저장 내용을 조회해서 WebSocket으로 클라이언트에게 전달<br>

redis 저장 내용

| 정보                  | 설명                | 저장 위치                    |
|---------------------|-------------------|--------------------------|
| userId ↔ sessionId  | 어떤 유저가 어떤 세션인지    | Redis Key-Value          |
| userId ↔ 접속 서버 인스턴스 | 이 유저가 어느 서버에 붙었는지 | Redis or Consistent Hash |
| 채팅방 ↔ 참여자 목록        | 채팅방 구성원 추적        | Redis Set/List           |

외부 broker 종류

- Redis : NoSQL 유형의 데이터베이스
    - 다른 메시지 브로커와 다르게 메시지 지속성이 없다. 즉, 메시지를 전송한 후 해당 메시지는 삭제되며 Redis 어디에도 저장되지 않는다.
    - 실시간 데이터 처리에 매우 적합하지만, 메시지 전송 신뢰성을 보장하지 않기 때문에 단점을 보완할 별도의 추가 구현이 필요할 수 있다.
- RabbitMQ : 대표적인 메시지 브로커
    - 이벤트 메시지가 전달되었다고 판단되면, 큐에서 메시지가 삭제되기때문에 다시 이벤트를 재생하기 어렵다.
    - 앱의 트래픽이 증가하여도 수평적 확장 어려움
- Kafka : 대표적인 이벤트 스트리밍 플랫폼
    - 이벤트 스트리밍 플랫폼은 이벤트가 생성되면, 레코드 로그를 streamer에 기록하게 됩니다.
      consumer가 topic을 가져간 후에도 이벤트 스트림에서 로그를 계속 유지하기 때문에 에러나 기타 문제가 생겼을 경우 이벤트를 재생할 수 있음.
    - 전통적인 메시지 브로커(RabbitMQ)에 비해 좀 더 유연하고 느슨한 결합을 가능 -> 격리와 확장이 쉬움.
    - Zookeeper가 실행중이여야 사용 가능. Zookeeper는 분산 코디네이션 시스템을 제공합니다. Kafaka Cluster의 리더를 발탁하는 방식도 Zookeeper가 제공하고
      있음.

<br>
=> RabbitMQ는 설정을 조금 추가해주면 사용할 수 있음. (https://brunch.co.kr/@springboot/695)

=> Kafka는 살짝 어렵고 라우팅이 불가능.

=> 고가용성의 설계를 가진 애플리케이션을 만들어보자는 목표에 부합하기 위해서는 트래픽이 많고, 장애가 생겼을때 대응할 수 있는 포인트가 조금 더 명확한 Kafka를 사용하기로 결정하였습니다.