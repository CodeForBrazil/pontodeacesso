angular.module('myApp')
	.controller('TodoController', [ '$scope', '$http', function ($scope, $http) { 
		
$scope.newTodo = {}; 

$scope.loadTodos = function(){ 
		$http.get('todos/all') 
			.success(function(data, status, headers, config) { 
				$scope.todos = data; 
				})
			.error(function(data, status, headers, config) { 
				alert('Error loading Todos'); 
			});
}; 

$scope.addTodo = function(){
	$http.post('todos/salvar',$scope.newTodo) 
		.success(function(data, status, headers, config) { 
			$scope.newTodo = {}; 
			$scope.loadTodos(); 
		}) 
		.error(function(data, status, headers, config) { 
			alert('Error saving Todo'); 
		}); 
}; 

$scope.deleteTodo = function(todo){ 
	$http.post('todos/delete', todo.id)
		.success(function(data, status, headers, config) { 
			$scope.loadTodos(); 
		}) 
		.error(function(data, status, headers, config) { 
			alert('Error deleting Todo'); 
		}); 
}; 

$scope.loadTodos(); 

}]);

//Data
var cities = [
    {
        city : 'Toronto',
        desc : 'This is the best city in the world!',
        lat : 43.7000,
        long : -79.4000
    },
    {
        city : 'New York',
        desc : 'This city is aiiiiite!',
        lat : 40.6700,
        long : -73.9400
    },
    {
        city : 'Chicago',
        desc : 'This is the second best city in the world!',
        lat : 41.8819,
        long : -87.6278
    },
    {
        city : 'Los Angeles',
        desc : 'This city is live!',
        lat : 34.0500,
        long : -118.2500
    },
    {
        city : 'Las Vegas',
        desc : 'Sin City...\'nuff said!',
        lat : 36.0800,
        long : -115.1522
    }
];

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
    
    //for (i = 0; i < cities.length; i++){
        //createMarker(cities[i]);
    //}

    $scope.openInfoWindow = function(e, selectedMarker){
        e.preventDefault();
        google.maps.event.trigger(selectedMarker, 'click');
    }
    
    $scope.getMarkers();
}]);