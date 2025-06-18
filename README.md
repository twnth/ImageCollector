# 📸 ImageCollector


## ✨ 주요 기능

- 🔍 **이미지 검색**: 키워드 기반의 이미지 검색 및 무한 스크롤 로딩
- 💾 **보관함 저장**: 마음에 드는 이미지를 보관함에 저장/삭제
- ❤️ **즐겨찾기 표시**: 검색 목록에서도 하트로 보관 여부 확인
- 📁 **보관함 탭**: 저장한 이미지들을 모아볼 수 있는 독립 탭 제공
- ↔️ **화면 전환 제스처**: 하단 네비게이션 탭 외에도 좌우 슬라이드로 전환 가능

---

## 🛠 기술 스택

| 기술            | 설명                                      |
|----------------|-------------------------------------------|
| **Kotlin**     | 언어                                       |
| **Jetpack Compose** | UI 선언형 프레임워크                     |
| **Room (SQLite)**   | 로컬 데이터베이스                        |
| **Accompanist Pager** | 슬라이드 제스처 기반 화면 전환           |
| **Hilt**       | DI(의존성 주입)                           |
| **Coil**       | 이미지 로딩 라이브러리                     |
| **ViewModel / StateFlow** | 상태 및 데이터 흐름 관리              |

---

## 🚀 저장소
https://github.com/twnth/ImageCollector.git

---

## 📁 폴더 구조

```plaintext
📦 image-collector
├── 📂 app
│   ├── 📂 core                 # Application Class, Utils 등
│   ├── 📂 data                 # 로컬 DB 및 Api Repository 구현
│   ├── 📂 domain               # Model, Mapper, Repository, UseCase 등
│   ├── 📂 presentation         # UI 화면들 (Search, Gallery 등)
│   │   ├── 📂 search
│   │   ├── 📂 gallery
│   │   ├── 📂 navigation       # Navigation 구성
│   │   ├── MainActivity.kt     # 기본 Activity
│   │   └── MainViewModel.kt    # 전체 화면 공통 ViewModel
│   ├── 📂 ui
│   │   ├── 📂 theme            # Material3 기반 테마 구성
│   │   └── 📂 component        # 재사용 가능한 컴포넌트
└── build.gradle               # Gradle 설정