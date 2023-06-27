// 슬라이드 컨테이너 요소 가져오기
const sliderContainer = document.querySelector(".slider-container");

// 슬라이드 이미지 요소들 가져오기
const slides = Array.from(document.querySelectorAll(".slider-slide"));

// 슬라이드 순서 목록 아이템들 가져오기
const paginationItems = Array.from(
  document.querySelectorAll(".slider-pagination li")
);

// 좌우 버튼 요소들 가져오기
const leftButton = document.querySelector(".slider-control.left");
const rightButton = document.querySelector(".slider-control.right");

// 현재 활성화된 슬라이드 인덱스
let currentIndex = 0;

// 자동 슬라이드 시간 (6초)
const autoSlideInterval = 6000;

// 자동 슬라이드 타이머 ID
let autoSlideTimer;

// 슬라이드를 활성화하는 함수
function activateSlide(index) {
  // 현재 활성화된 슬라이드 비활성화
  slides[currentIndex].classList.remove("active");
  paginationItems[currentIndex].classList.remove("active");

  // 새로운 슬라이드 활성화
  slides[index].classList.add("active");
  paginationItems[index].classList.add("active");

  // 현재 인덱스 업데이트
  currentIndex = index;
}

// 다음 슬라이드로 이동하는 함수
function nextSlide() {
  const nextIndex = (currentIndex + 1) % slides.length;
  activateSlide(nextIndex);
  resetAutoSlideTimer();
}

// 이전 슬라이드로 이동하는 함수
function previousSlide() {
  const prevIndex = (currentIndex - 1 + slides.length) % slides.length;
  activateSlide(prevIndex);
  resetAutoSlideTimer();
}

// 자동 슬라이드 타이머 초기화 함수
function resetAutoSlideTimer() {
  clearInterval(autoSlideTimer);
  autoSlideTimer = setInterval(nextSlide, autoSlideInterval);
}

// 좌우 버튼 클릭 이벤트 처리
leftButton.addEventListener("click", previousSlide);
rightButton.addEventListener("click", nextSlide);

// 슬라이드 순서 목록 아이템 클릭 이벤트 처리
paginationItems.forEach((item, index) => {
  item.addEventListener("click", () => {
    activateSlide(index);
    resetAutoSlideTimer();
  });
});

// 초기 슬라이드 활성화
activateSlide(currentIndex);

// 자동 슬라이드 타이머 시작
resetAutoSlideTimer();
