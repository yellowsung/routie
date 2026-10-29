# 🏃 Routie

운동을 '루틴'으로 만들어 꾸준히 수행하도록 돕고, 그 성취를 캐릭터 성장과 랭킹이라는 눈에 보이는 보상으로 연결해주는 **운동 루틴 관리 서비스**입니다.

Android(모바일/Wear OS) 앱과 Spring Boot 백엔드로 구성된 풀스택 프로젝트이며, 2025학년도 졸업작품으로 제작되었습니다.

<br>

## 앱 주제

하루하루의 운동을 작은 성공 경험으로 쪼개어 꾸준한 습관으로 만드는 **운동 루틴 트래킹 & 동기부여 앱**입니다. 사용자는 요일별로 자신만의 운동 루틴을 구성하고, 이를 수행함으로써 보상(골드)을 얻고, 다른 사용자와 기록을 겨루며 운동을 지속할 동기를 얻습니다.

## 목표

- **지속 가능한 운동 습관 형성**: 요일별 루틴 설계 → 수행 → 보상의 선순환 구조로 운동을 게임처럼 즐기며 지속하게 만든다.
- **게이미피케이션을 통한 동기부여**: 골드·가챠·캐릭터 꾸미기·칭호·랭킹 등 보상 요소를 결합해 운동 자체의 지속률을 높인다.
- **건강 데이터 연동으로 실제 활동과의 연결**: Samsung Health, Wear OS(걸음 수 센서) 데이터를 실제 운동 기록과 연결해 신뢰도 있는 트래킹을 제공한다.
- **모바일 ↔ 워치 연동 경험 제공**: Wear OS 앱과 Data Layer 통신을 통해 손목에서도 루틴 확인·운동 기록이 가능하도록 한다.

## 앱 기능

### 운동 루틴
- 요일별 루틴 생성 / 조회 / 수정 / 삭제
- 루틴에 포함된 운동(Exercise) 구성 및 카테고리별 관리
- 루틴 시작/종료, 수행 기록(RoutineLog) 적립
- 걷기 세션(Walk Session) 시작/종료 및 걸음 수·소모 칼로리 기록

### 보상 & 성장
- 루틴/걷기 목표 달성 시 골드(재화) 보상 지급
- 골드로 상점에서 아이템 구매, 장바구니 기능
- 티켓을 사용한 가챠(랜덤 뽑기)로 아이템 획득
- 특정 조건 달성 시 **칭호(Achievement)** 부여

### 캐릭터 & 마이룸
- 보유 아이템으로 캐릭터 꾸미기(장착/해제)
- 나만의 방(마이룸)에서 캐릭터와 아이템 전시

### 커뮤니티 & 랭킹
- 칼로리/걸음 수 등 기준별 사용자 랭킹 제공
- 다른 사용자와 운동 성과 비교

### 알림 & 디바이스 연동
- 아침/저녁 알람 등 시스템 알림으로 루틴 리마인드 (`AlarmManager` + `BootReceiver`로 재부팅 후에도 유지)
- Samsung Health 연동으로 걸음 수·칼로리 데이터 수집
- **Wear OS 앱**: 워치에서 루틴 확인, 걷기 세션 측정(가속도 센서), 운동 타이머, 워치페이스 컴플리케이션으로 걸음 수 표시
- 모바일 ↔ 워치 간 Wearable Data Layer API를 통한 사용자 정보/루틴 동기화

### 계정
- 회원가입 / 로그인 / 로그아웃
- 사용자 정보 조회 및 수정, 비밀번호 변경, 프로필 이미지 설정

## 사용 기술

### Mobile (Android · `routie-mobile/app`)
- **언어**: Kotlin, Java
- **UI**: XML View (Fragment/Navigation Component) + Jetpack Compose 혼용, Material Components
- **네트워크**: Retrofit2, OkHttp, Gson
- **이미지 로딩**: Glide
- **비동기**: Kotlin Coroutines
- **헬스 연동**: Samsung Health Data SDK
- **디바이스 간 통신**: Google Play Services Wearable (Data Layer API)
- **알림/백그라운드**: AlarmManager, BroadcastReceiver, Notification

### Wear OS (`routie-mobile/routie-wear`)
- **언어**: Kotlin
- **UI**: Jetpack Compose for Wear OS (Wear Compose Material/Foundation/Navigation)
- **센서**: Step Sensor(가속도계), Health Services Client
- **워치페이스**: Watch Face Complications Data Source API
- **네트워크**: Retrofit2

### Backend (`routie-server`)
- **언어/프레임워크**: Java 17, Spring Boot 3.4
- **데이터 접근**: Spring Data JPA, MySQL
- **보안**: Spring Security (API 인증/인가 처리)
- **검증**: Spring Validation
- **빌드**: Gradle
- **기타**: Lombok, JAXB

### Infra / DevOps
- GitHub Actions 기반 CI/CD → AWS EC2 자동 배포 (`.github/workflows/deploy.yml`)
- MySQL 데이터베이스

## 프로젝트 구조

```
routie/
├─ routie-mobile/
│  ├─ app/              # Android 모바일 앱
│  └─ routie-wear/       # Wear OS 워치 앱
└─ routie-server/        # Spring Boot 백엔드 서버
```

## 팀원 및 담당 분야

| 이름 | 역할 |
| --- | --- |
| 황승희 | 팀장 · 백엔드 |
| 이지수 | 백엔드 |
| 전성훈 | 백엔드 |
| 김하은 | 프론트엔드(Android) |

<br>

---

### 실행 방법 (예시)

```bash
# 모바일/Wear OS 앱
cd routie-mobile
./gradlew assembleDebug

# 백엔드 서버 (application.properties에 DB 정보 설정 필요)
cd routie-server
./gradlew bootRun
```
~~~
