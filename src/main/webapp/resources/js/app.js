var myApp = angular
    .module('myApp', ['ngResource', 'ngRoute', 'ui.bootstrap']);
myApp.config(function ($routeProvider) {
    	
        $routeProvider.when('/flights', {
            templateUrl: 'partials/flights.html',
            controller: 'FlightsController',
        }).when('/stats', {
            templateUrl: 'partials/statistics.html',
            controller: 'StatsController',
        }).when('/database', {
            templateUrl: 'partials/airportDatabase.html',
            controller: 'AirportDatabaseController',
        }).otherwise({
                redirectTo: '/flights',
            });
    });
    
myApp.run(function ($rootScope) {
	$rootScope.currency = 'EUR';
	$rootScope.lang = 'en';
	
	
	//This should be done using ngTranslate of course
});