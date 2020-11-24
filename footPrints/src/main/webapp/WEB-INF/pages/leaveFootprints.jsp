<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>footprints main</title>
        <link rel="icon" type="image/x-icon" href="resources/assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="resources/css/styles.css" rel="stylesheet" />
        <!-- kakao map 불러오기 -->
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	2505cedff552111a7d6811a427587c7d"></script>
        <!-- services와 clusterer, drawing 라이브러리 불러오기 -->
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
    </head>
    <body id="page-top">
    <input type="hidden" value="${mem_id}" id="mem_id" name="mem_id">
    <input type="hidden" value="${wk_info_id}" id="wk_info_id" name="wk_info_id">
        <!-- Portfolio Grid-->
        <section class="page-section bg-light" id="portfolio">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Footprints</h2>
                    <div id="walkingState">
	                    <c:if test="${wk_info_id eq null}">
	                    	<h3 class="section-subheading text-muted"><a id="startWalking">Start Walking</a></h3>
	                    </c:if>
	                    <c:if test="${wk_info_id ne null}">
	                    	<h3 class="section-subheading text-muted"><a id="endWalking">end Walking</a></h3>
	                    </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg">
                        <div id="map" style="width:100%;height:500px;"></div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="resources/assets/mail/jqBootstrapValidation.js"></script>
        <script src="resources/assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="resources/js/scripts.js"></script>
        <script>
        //map
        document.addEventListener("DOMContentLoaded",function(){
        	function getLocation(position){
        		// **현재위치**
        		var currentLatitud = position.coords.latitude; //X좌표(위도)
        		var currentLongitude = position.coords.longitude; //Y좌표 (경도)
        		console.log("현재위치:"+currentLatitud+":"+currentLongitude);
        		
        		// **지도 설정**
        		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        		var options = { //지도를 생성할 때 필요한 기본 옵션
        			center: new kakao.maps.LatLng(currentLatitud, currentLongitude), //지도의 중심좌표.
        			level: 3 //지도의 레벨(확대, 축소 정도)
        		};
        		// 지도 생성 및 객체 리턴
        		var map = new kakao.maps.Map(container, options); 
        		
        		// 지도 컨트롤
        		// 지도타입 컨트롤 생성
        		var mapTypeControl = new kakao.maps.MapTypeControl(); 
        		// 지도에 컨트롤 표시(TOPRIGHT는 오른쪽 위를 의미)
        		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT); 
        		
        		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
        		var zoomControl = new kakao.maps.ZoomControl();
        		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
        		
        		// **마커 설정**
        		// 마커가 표시될 위치
        		var markerPosition = new kakao.maps.LatLng(currentLatitud,currentLongitude); 
        		// 마커 생성(기본마커)
        		var marker = new kakao.maps.Marker({position:markerPosition}); 
        		marker.setMap(map);
        		
        		// **폴리라인 그리기**
        		
        		//db에 있는 정보로 폴리라인 그리기
        		var linePath = [];
        		var wk_info_id = "${wk_info_id}";
        		
        		if(! wk_info_id ==null || !wk_info_id == ""){ //db에 정보가 있는 경우
        			var recordInfo = "${recordInfo}" ;
        			console.log("데이터 확인1"+recordInfo);
      
        			<c:forEach var="item" items="${recordInfo}">
        				if("${item.wk_record_marker}"==1){ //기록 시작 위치에 마커
        					console.log("11111111111");
        					//start 마커 설정
							var imageSrc = './resources/assets/img/footprints.png', // 마커이미지의 주소    
		                    imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기
		                    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정
		                      
		    	            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성
		    	            var startMarker = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		    	                markerPosition = new kakao.maps.LatLng("${item.wk_latitude}", "${item.wk_longitude}"); // 마커가 표시될 위치입니다
		    	
		    	            // 마커를 생성
		    	            var startMarker = new kakao.maps.Marker({
		    	                position: markerPosition, 
		    	                image: startMarker // 마커이미지 설정 
		    	            });
		    	
		    	            // 마커가 지도 위에 표시되도록 설정합니다
		    	            marker.setMap(null);
		    	            startMarker.setMap(map);
        				}
        				// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
        				linePath.push(new kakao.maps.LatLng("${item.wk_latitude}", "${item.wk_longitude}"));
        				
        			</c:forEach>
        			
            		console.log("linePath 확인::::"+linePath);
            		console.log("linePath 데이터 타입 확인:::"+typeof(linePath));

    				// 지도에 표시할 선을 생성합니다
    				var polyline = new kakao.maps.Polyline({
    				    path: linePath, // 선을 구성하는 좌표배열 입니다
    				    strokeWeight: 5, // 선의 두께 입니다
    				    strokeColor: '#FFAE00', // 선의 색깔입니다
    				    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    				    strokeStyle: 'solid' // 선의 스타일입니다
    				});
    				
    				// 지도에 선을 표시합니다 
    				polyline.setMap(map);  
    				
    				//실시간으로 이동 위치 폴리라인 그리기 (10초마다 반복 실행)
            		var polyLineInterval = setInterval(function(){
            			linePath.push(new kakao.maps.LatLng(currentLatitud,currentLongitude));
            			console.log("linePath 확인::::"+linePath);
            		},10000);
        		}
        		
        		
        		
        		
        		
        		//start walking을 클릭 시
        		$('#startWalking').click(function(){
                	//alert('시작');
  
    	            //기록 시작 시 walkingInfo 테이블에 insert
    	            //walkingRecord에 insert 
    	            $.ajax({
						url: "/footprints/walkingInfoInsert.do" ,
						type: "get",
						data:  {
									"mem_id" : $('#mem_id').val(),
									"currentLatitud" : currentLatitud,
									"currentLongitude" : currentLongitude
								},
						async: false ,
						success: function(data){ //익명으로 함수 생성
							if(data != null){
								//data로 받아온 wk_info_id hidden에 넣기
								document.getElementById("wk_info_id").value = data;
								
								//start 마커 설정
								var imageSrc = './resources/assets/img/footprints.png', // 마커이미지의 주소    
			                    imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기
			                    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정
			                      
			    	            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성
			    	            var startMarker = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
			    	                markerPosition = new kakao.maps.LatLng(currentLatitud, currentLongitude); // 마커가 표시될 위치입니다
			    	
			    	            // 마커를 생성
			    	            var startMarker = new kakao.maps.Marker({
			    	                position: markerPosition, 
			    	                image: startMarker // 마커이미지 설정 
			    	            });
			    	
			    	            // 마커가 지도 위에 표시되도록 설정합니다
			    	            marker.setMap(null);
			    	            startMarker.setMap(map);
			    	            
			    	            //10초마다 현재위치 가져오기
			    	            var polyLineInterval = setInterval(function(){
			            			linePath.push(new kakao.maps.LatLng(currentLatitud,currentLongitude));
			            			console.log("linePath 확인::::"+linePath);
			            		},10000);
							}
						
						},
						error: function(a,b,c){ //ajax 실패시 원인
							alert("에러2"+c);
						}
					})
					$('#walkingState').empty();
    	            $('#walkingState').append('<h3 class="section-subheading text-muted"><a id="endWalking">end Walking</a></h3>');
    	            
                });
        		
        		//end walking 클릭 시
        		$('#endWalking').click(function(){
        			var wk_info_id = $('#wk_info_id').val();
        			alert("기록 종료 : "+wk_info_id);
        			$.ajax({
						url: "/footprints/walkingFinish.do" ,
						type: "get",
						data:  {
									"wk_info_id" : wk_info_id,
									"currentLatitud" : currentLatitud,
									"currentLongitude" : currentLongitude
								},
						success: function(data){ 
							//실시간 기록 종료하기
							
							clearTimeout(polyLineInterval);
							
							alert(data);
							//마이페이지 이동 -------------------------(미완성)
						},
						error: function(a,b,c){ //ajax 실패시 원인
							alert("에러2"+c);
						}
					})
        			
        		});
        		
        	}
            if(navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(getLocation, function(error) {
                    consol.log(error.message);    
                });
            } else {
                consol.log("Geolocation을 지원하지 않는 브라우저 입니다.");
            }
            
        });
        
        
        
        </script>
    
     </body>   
    
    
    
    