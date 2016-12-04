angular.module('myApp').controller('AirportDatabaseController', function ($uibModal, $scope, $log, $http, $rootScope) {	
	
	$scope.tableData = {};
	$scope.searchTerm = "";
	
	$scope.size = 20;
	
	$scope.coordinatesLink = function (coordinates) {
		return "http://www.google.com/maps/place/" 
				+ coordinates.latitude 
				+ "," + coordinates.longitude;
	}
	
	$scope.error = 0;
	$scope.searching = false;
	$scope.search = function (page) {
		if ($scope.searchTerm.length == 0) {
			return;
		}
		$scope.currentPage = page;
		$scope.searching = true;
		$http.get('/rest/api/partial/' + $scope.size + '/' + page + '/' + $rootScope.lang + '/' + $scope.searchTerm).then(function (data) {
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