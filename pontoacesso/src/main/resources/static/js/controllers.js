//Angular App Module and Controller
angular.module('myApp').controller('MapCtrl',[ '$scope', '$http', '$timeout', function ($scope, $http, $timeout) {

    var createMarker = function (info){
        
        var marker = new google.maps.Marker({
            map: $scope.map,
            position: new google.maps.LatLng(info.latitude, info.longitude),
            title: info.nome
        });
        marker.content = '<div class="infoWindowContent">' + info.descricao + '</div>';
        
        google.maps.event.addListener(marker, 'click', function(){
            infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
            infoWindow.open($scope.map, marker);
        });
        
        $scope.markers.push(marker);
        
    }
    
    $scope.getMarkers = function(){ 
    	$http.get('markers/all')
    		.success(function(data, status, headers, config) {
    			$scope.newMarkers = data;
    			for (i = 0; i < $scope.newMarkers.length; i++){
    		        createMarker($scope.newMarkers[i]);
    		    } 
    		}) 
    		.error(function(data, status, headers, config) { 
    			alert('Erro ao obter marcadores'); 
    		}); 
    };
    
    $scope.addMarker = function(value){ 
    	$http.post('markers/add', {endereco: value})
    		.success(function(data, status, headers, config) {
    			$scope.newMarkers = data;
    			for (i = 0; i < $scope.newMarkers.length; i++){
    		        createMarker($scope.newMarkers[i]);
    		    } 
    		}); 
    };
       
    $scope.openInfoWindow = function(e, selectedMarker){
        e.preventDefault();
        google.maps.event.trigger(selectedMarker, 'click');
    };
    
    	var mapOptions = {
    
            zoom: 16,
            center: new google.maps.LatLng(-25.4284, -49.2733),
            mapTypeId: google.maps.MapTypeId.TERRAIN
        };
        
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
            
        $scope.markers = [];
        $scope.newMarkers = [];
        
        var infoWindow = new google.maps.InfoWindow();

    

    $scope.getMarkers();
}]);

angular.module('myApp').controller('MarkerCtrl',[ '$scope', '$http', '$timeout', function ($scope, $http, $timeout) {
	
	var pontoAcessoView = {
		endereco : ''	
	};
	
	$scope.allMarkers = function(){ 
    	$http.get('markers/all')
    		.success(function(data, status, headers, config) {
    			$scope.newMarkers = data;
    			console.log($scope.newMarkers);
    			for (i = 0; i < $scope.newMarkers.length; i++){
    		        createMarker($scope.newMarkers[i]);
    		    } 
    		}) 
    		.error(function(data, status, headers, config) { 
    			alert('Erro ao obter marcadores'); 
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
}]);