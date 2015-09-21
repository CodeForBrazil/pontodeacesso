//Angular App Module and Controller
angular.module('myApp').controller('MapCtrl',[ '$scope', '$http', function ($scope, $http) {

    var mapOptions = {
        zoom: 4,
        center: new google.maps.LatLng(40.0000, -98.0000),
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }

    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);

    $scope.markers = [];
    $scope.newMarkers = [];
    
    var infoWindow = new google.maps.InfoWindow();
    
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
    			console.log($scope.newMarkers);
    			for (i = 0; i < $scope.newMarkers.length; i++){
    		        createMarker($scope.newMarkers[i]);
    		    } 
    		}) 
    		.error(function(data, status, headers, config) { 
    			alert('Erro ao obter marcadores'); 
    		}); 
    }; 
    
    $scope.openInfoWindow = function(e, selectedMarker){
        e.preventDefault();
        google.maps.event.trigger(selectedMarker, 'click');
    }
    
    $scope.getMarkers();
}]);