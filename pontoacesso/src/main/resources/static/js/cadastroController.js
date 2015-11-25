angular.module('myApp').controller('CadastroCtrl',[ '$scope', '$http', '$timeout', function ($scope, $http, $timeout) {
	
	$scope.cadastroView = {
		nome : '',
		caracteristicas : new Array(),
		foto : '',
		latitude: 0.0,
		longitude: 0.0,
		tipo : 0,
		sucesso: false,
		erro: false
	};
	$scope.ids = {};
	
	$scope.markers = new Array();
	
	$scope.salvarDados = function(){
		var i;
    	for( i in $scope.ids){
    		if($scope.ids[i] == true){
    			$scope.cadastroView.caracteristicas.push(i);
    		}
    	}
    	
    	
    	$scope.cadastroView.latitude = $scope.markers[0].getPosition().lat();
		$scope.cadastroView.longitude = $scope.markers[0].getPosition().lng();
		$scope.cadastroView.tipo = $scope.data.singleSelect;
		
		$http.post('markers/add', $scope.cadastroView)
			.success(function (response) {
				location.reload();
				$scope.cadastroView.sucesso = true;
		});
    };
	
	 var readURL = function(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();

	            reader.onload = function (e) {
	                $('.profile-pic').attr('src', e.target.result);
	                $scope.cadastroView.foto = e.target.result;
	            }
	    
	            reader.readAsDataURL(input.files[0]);
	            
	        }
	    }
	    

	    $(".file-upload").on('change', function(){
	        readURL(this);
	    });
	    
	    $(".upload-button").on('click', function() {
	       $(".file-upload").click();
	    });
	    
	    var mapOptions = {
                zoom: 16,
                center: new google.maps.LatLng(-25.4284, -49.2733),
                mapTypeId: google.maps.MapTypeId.TERRAIN,
                maxZoom: 25,
                minZoom: 1
    };
    	
	if($scope.map === undefined) {
		$scope.map = new google.maps.Map(document.getElementById('mapCadastro'), mapOptions);
		google.maps.event.addListener($scope.map, 'click', function(event) {
		    placeMarker(event.latLng);
		  });
	}
	
	function placeMarker(location) {
		removeMarkers();
		  var marker = new google.maps.Marker({
		      position: location, 
		      map: $scope.map
		  });
		  $scope.markers.push(marker);
		  $scope.map.setCenter(location);
	};
	
	function removeMarkers(){
	    for(i=0; i < $scope.markers.length; i++){
	    	$scope.markers[i].setMap(null);
	    }
	}
    	 
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
}]);

