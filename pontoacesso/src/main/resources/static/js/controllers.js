angular.module('myApp').controller('MarkerCtrl',[ '$scope', '$http', '$timeout', function ($scope, $http, $timeout) {


		var pontoAcessoView = {
			endereco : '',
		};
		
		$scope.markers = [];
		$scope.newMarkers = [];
	    var infoWindow = new google.maps.InfoWindow();
		var mapOptions = {
	                zoom: 16,
	                center: new google.maps.LatLng(-25.4284, -49.2733),
	                mapTypeId: google.maps.MapTypeId.TERRAIN,
	                maxZoom: 16,
	                minZoom: 3
	    };
	    	
		if($scope.map === undefined)
			$scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
	    	 
	    if(navigator.geolocation) {
	         browserSupportFlag = true;
	         navigator.geolocation.getCurrentPosition(function(position) {
	         initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
	         $scope.map.setCenter(initialLocation);
	             }, function() {
	               handleNoGeolocation(browserSupportFlag);
	         });
	             // Browser doesn't support Geolocation
	    } else {
	        browserSupportFlag = false;
	        handleNoGeolocation(browserSupportFlag);
	    }
	             
	    function handleNoGeolocation(errorFlag) {
	        if (errorFlag == true) {
	           alert("Geolocation service failed.");
	           initialLocation = newyork;
	        } else {
	           alert("Your browser doesn't support geolocation. We've placed you in Siberia.");
	           initialLocation = siberia;
	        }
	        $scope.map.setCenter(initialLocation);
        }
	    
	    $scope.allMarkers = function(){ 
	    	$http.get('markers/all')
	    		.success(function(data, status, headers, config) {
	    			$scope.newMarkers = data;
	    			for (i = 0; i < $scope.newMarkers.length; i++){
	    				$scope.createMarker($scope.newMarkers[i]);
	    		    } 
	    		});
	    };
	    
	    $scope.addMarker = function(value){
	    	$http.post('markers/add', {endereco: value})
	    		.success(function(data, status, headers, config) {
	    			 $scope.closeModalAddMarker();
	    			 window.location.reload();
	    		}); 
	    };
	    
	    $scope.openModalAddMarker = function(){
	    	$('.modal').modal('hide');
	    	$('#addMarker').modal('show');
	    };
	    
	    $scope.closeModalAddMarker = function(){
	    	$('#addMarker').modal('hide');
	    };
	    
	    $scope.filterMarkers = function(add, cat){
	    	if(add){
	    		for (var i=0; i<$scope.markers.length; i++) {
	    	        if ($scope.markers[i].categoria == cat) {
	    	        	$scope.markers[i].setVisible(true);
	    	        }
	    		}
	    	} else {
	    		for (var i=0; i<$scope.markers.length; i++) {
	    	        if ($scope.markers[i].categoria == cat) {
	    	        	$scope.markers[i].setVisible(false);
	    	        	infoWindow.close();
	    	        }
	    		}
	    	}
	    };
	    
	    
	    $scope.createMarker = function (info){
	        var marker = new google.maps.Marker({
	            map: $scope.map,
	            position: new google.maps.LatLng(info.latitude, info.longitude),
	            title: info.nome,
	            icon: '/img/markers/marker.png'
	        });
	        console.log(info.foto);
	        marker.content = '<div class="infoWindowContent"> <img src="' + info.foto + '" height="80" width="80"/></div>';
	        marker.categoria = info.categoria.id;
	        
	        google.maps.event.addListener(marker, 'click', function(){
	            infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
	            infoWindow.open($scope.map, marker);
	        });
	        $scope.markers.push(marker);
	    };
	    
	    $scope.openInfoWindow = function(e, selectedMarker){
	        e.preventDefault();
	        google.maps.event.trigger(selectedMarker, 'click');
	    };
	   
	    $scope.allMarkers();
}]);