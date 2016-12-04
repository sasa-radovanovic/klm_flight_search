angular
    .module('myApp').controller('FlightsController', function ($uibModal, $scope, $log, $http, $rootScope) {		
    	
	$scope.origin = {
    			code: '',
    			name: ''
    	};
    	$scope.destination = {
    			code: '',
    			name: ''
    	};
    	$scope.airportModal = function (typeM) {
    	    var modalInstance = $uibModal.open({
    	      animation: true,
    	      size: 'lg',
    	      ariaLabelledBy: 'modal-title',
    	      ariaDescribedBy: 'modal-body',
    	      templateUrl: 'originModalInstance.html',
    	      controller: 'OriginModalInstanceCtrl',
    	      resolve: {
    	    	  type: function () {
    	    		  return typeM;
    	    	  }
    	      }
    	    });
    	    
    	    modalInstance.result.then(function (selectedItem) {
    	    	$scope.fareData = {};
    	        if (selectedItem.type == 'origin') {
    	        	$scope.origin.code = selectedItem.code;
    	        	$scope.origin.name = selectedItem.name;
    	        } else {
    	        	$scope.destination.name = selectedItem.name;
    	        	$scope.destination.code = selectedItem.code;
    	        }
    	    });
    	};
    	
    	$scope.coordinatesLink = function (code) {
    		return "http://www.google.com/maps/place/" 
    				+ $scope.fareData.airports[code].coordinates.latitude 
    				+ "," + $scope.fareData.airports[code].coordinates.longitude;
    	}
    	
    	$scope.error = 0;
    	$scope.searchingFares = false;
    	$scope.fareData = {};
    	$scope.findFares = function () {
    		$scope.error = 0;
    		$scope.searchingFares = true;
    		$http.get('rest/api/fares/' + $scope.origin.code + '/' + $scope.destination.code + '/' + $scope.currency).then(function (data) {
    			if (data.status  == 200) {
    				$scope.error = 0;
    		    	$scope.searchingFares = false;
    		    	$scope.fareData = data.data;
    			} else {
    				$scope.error = 1;
    		    	$scope.searchingFares = false;
    			}
    		}, function (error) {
    			$scope.error = 1;
    			$scope.searchingFares = false;
    		});
    	}
    	
    }).controller('OriginModalInstanceCtrl', function ($uibModalInstance, $http, $rootScope, $scope, type) {
    	
    	$scope.type = type;
    	
    	$scope.searchTerm = "";
    	$scope.error = 0;
    	
    	$scope.currentPage = 1;
    	
        $scope.select = function(code, name) {
        	$uibModalInstance.close({
        		"type" : $scope.type,
        		"code" : code,
        		"name" : name
        	});  
        };
    	
    	$scope.tableData = {};
    	
    	$scope.search = function (page) {
    		$scope.currentPage = page;
    		if ($scope.searchTerm.length < 3) {
    			$scope.error = 1;
    			return;
    		}
    		$scope.searching = true;
    		$http.get('/rest/api/partial/10/' + page + '/' + $rootScope.lang + '/' + $scope.searchTerm).then(function (data) {
    			console.log(data);
    			if (data.status == 200) {
    				$scope.tableData = data.data;
    				$scope.searching = false;
    				$scope.error = 0;
    			} else {
    				$scope.searching = false;
    				$scope.error = 2;
    			}
    		}, function (error) {
    			$scope.searching = false;
				$scope.error = 2;
    		});
    	}
    	
    });