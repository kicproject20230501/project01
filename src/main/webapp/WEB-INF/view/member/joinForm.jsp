<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>회원가입</title>
</head>
<body>
	<br>
	<br>
	<br>
	<div class="container mt-3">
		<h2 align="center">회원가입</h2>
		<h6 align="center">
			<span style="color: red;">*</span>는 필수 입력 사항입니다.
		</h6>
	</div>
	<br>
	<div class="container mt-3">
		<form class="container" name="f"
			action="${pageContext.request.contextPath}/member/joinPro"
			method="post" >
			<div>
				<label><span style="color: red;">*</span> 아이디 (4글자 이상)</label> <input
					class="form-control" type="text" name="id" id="id" placeholder="Id"
					min="4" autocomplete="off" oninput="checkId()">
			</div>
			<div id="idCheckResult" class="mb-3"></div>

			<div class="mb-3">
				<label><span style="color: red;">*</span> 비밀번호</label> <input
					class="form-control" type="password" name="pass"
					placeholder="Password" id="pass">
			</div>
			
			<div>
				<label><span style="color: red;">*</span> 비밀번호 확인</label> <input
					class="form-control" type="password" onchange="checkPass()"
					placeholder="Password" id="chkPass">
			</div>
			<div id="passCheckResult" class="mb-3"></div>

			<div class="mb-3">
				<label><span style="color: red;">*</span> 이름</label> <input
					class="form-control" type="text" name="name" placeholder="Username"
					id="name">
			</div>

			<div class="mb-3">
				<p class="mb-0">
					<label><span style="color: red;">*</span> 성별</label>
				</p>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gender"
						value="1" checked> 남
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gender"
						value="2">여
				</div>
			</div>

			<div class="mb-3">
				<label><span style="color: red;">*</span> 전화번호</label> <input
					class="form-control" type="text" name="tel"
					placeholder="Phone Number" id="tel">
			</div>

			<div class="mb-3">
				<label><span style="color: red;">*</span> 이메일</label> <input
					class="form-control" type="text" name="email"
					placeholder="example@hotmail.com" id="email">
			</div>

			<div class="mb-3">
				<p class="mb-0">
					<label><span style="color: red;">*</span> 주소</label>
				</p>
				<div class="row">
					<div class="col">
						<input class="form-control" type="text" id="sample4_postcode"
							name="zipcode" placeholder="우편번호" readonly>
					</div>
					<div class="col">
						<input type="button" class="btn btn-outline-secondary"
							onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
					</div>
				</div>
			</div>

			<div class="mb-3">
				<div class="row">
					<div class="col">
						<input class="form-control" type="text" id="sample4_roadAddress"
							name="address" placeholder="도로명주소" readonly>
					</div>
					<div class="col">
						<input class="form-control" type="text" id="sample4_jibunAddress"
							placeholder="지번주소">
					</div>
				</div>
			</div>
			<span id="guide" style="color: #999; display: none"></span>
			<div class="mb-3">
				<div class="row">
					<div class="col">
						<input class="form-control" type="text" id="sample4_detailAddress"
							placeholder="상세주소">
					</div>
					<div class="col">
						<input class="form-control" type="text" id="sample4_extraAddress"
							placeholder="참고항목" readonly>
					</div>
				</div>
			</div>
			<div class="mt-5">
				<input class="form-control" id="signupBtn" type="submit" disabled = "disabled"
					value="회원가입">
			</div>
		</form>
	</div>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}
	
	<!-- 회원가입 아이디, 비밀번호 확인용 변수 -->
	var id_result = 0;
	var pass_result = 0;
	
	<!-- 회원가입 아이디 확인 ajax -->
	function checkId() {
		var inputId = $("#id").val();
		var blank_pattern1 = /^\s+|\s+$/g;
		var blank_pattern2 = /[\s]/g;
		
		$.ajax({
			data : {
				id : inputId // 입력한 아이디를 chkId라는 변수에 담기
			},
			url : "checkid", // data를 checkid url에 보낸다 (controller에서 처리)
			success : function(data) {
				if (inputId.trim().length < 4) {
					$("#idCheckResult").css('color', 'red').html('사용할 수 없는 아이디입니다.')
					$("#signupBtn").prop("disabled", true)
				} else {
					if (data == '1') { // 아이디가 중복일 경우
						$("#idCheckResult").css('color', 'red').html('사용 중인 아이디입니다.')
						$("#signupBtn").prop("disabled", true)
					} else {
						$("#idCheckResult").css('color', 'blue').html('사용가능한 아이디입니다.')
						id_result = 1
						if (id_result == 1 && pass_result == 1) {
							$("#signupBtn").prop("disabled", false)
							checkSignup()
						}
					}
				}
			}
		})	
	}
	
	<!-- 비밀번호 체크 -->
	function checkPass(){
		var pass = $("#pass").val();
		var chkPass = $("#chkPass").val();
		if (pass.trim() == "" && chkPass.trim() == "") {
			$("#passCheckResult").css('color', 'red').html("비밀번호를 입력해주세요.")
			$("#signupBtn").prop("disabled", true)
		} else if (pass != chkPass) {
			$("#passCheckResult").css('color', 'red').html("비밀번호가 일치하지 않습니다.")
			$("#signupBtn").prop("disabled", true)
		} else if (pass == chkPass) {
			$("#passCheckResult").css('color', 'blue').html("비밀번호가 일치합니다.")
			$("#signupBtn").prop("disabled", false)
			pass_result = 1
			if (id_result == 1 && pass_result == 1) {
				$("#signupBtn").prop("disabled", false)
				checkSignup()
			}
		}
	}
	
	function checkSignup() {
		var name = $("#name").val();
		var tel = $("#tel").val();
		var email = $("#email").val();
		var zipcode = $("sample4_postcode").val();
		var address = $("sample4_roadAddress").val();
		
		if (name.trim() == "" || tel.trim() == "" || email.trim() == ""
				|| zipcode.trim() == "" || address.trim() == "") {
			$("#signupBtn").prop("disabled", true);
		}
	}
	
	
</script>
</body>
</html>