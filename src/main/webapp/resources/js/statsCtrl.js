angular
    .module('myApp').controller('StatsController', function ($uibModal, $scope, $log, $http, $rootScope) {	
	
    	
    	$scope.stats = {};
    	$scope.error = false;
    	$http.get('/rest/system_stats').then(function (data) {
    		$scope.error = false;
    		console.log(data.data);
    		$scope.stats = data.data;
    	}, function (error) {
    		$scope.error = true;
    	});
    	
    	$scope.roundNum = function (num) {
    		num = num / 1000;
    		return Math.round(num * 100) / 100;
    	}
   
});