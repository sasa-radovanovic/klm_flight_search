angular
    .module('myApp').run(function ($rootScope) {	
	
    $rootScope.labels = {
    		searchTitle: {
    			en : "Fare Search",
    			nl: "Zoek vluchten"
    		},
    		selectOrigin: {
    			en: "Select origin airport",
    			nl: "Kies luchthaven herkomst"
    		},
    		selectDestination: {
    			en: "Select destination airport",
    			nl: "Kies luchthaven van bestemming"
    		},
    		findFares: {
    			en: "FIND FARES",
    			nl: "Vind tarieven"
    		},
    		searchingFares: {
    			en: "Searching for fares between",
    			nl: "Zoeken naar tarieven tussen"
    		},
    		noFaresFound: {
    			en: "No fares found. Try a different search.",
    			nl: "Geen tarieven gevonden. Probeer een andere zoekopdracht."
    		},
    		location : {
    			en: "Location",
    			nl: "Plaats"
    		},
    		noOriginAirport: {
    			en: "No origin airport selected",
    			nl: "Geen herkomst luchthaven geselecteerd"
    		},
    		noDestAirport: {
    			en: "No destination airport selected",
    			nl: "Geen luchthaven van bestemming geselecteerd"
    		},
    		selectRoute : {
    			en: "Select route",
    			nl: "Kies een route"
    		},
    		noDataRetrieved: {
    			en: "Oooops! No data retrieved.",
    			nl: "Oooops! Geen gegevens opgehaald."
    		},
    		enterHint: {
    			en: "Airport name, country or code",
    			nl: "Airport naam, land of code"
    		},
    		select : {
    			en: "Select ",
    			nl: "Kiezen "
    		},
    		search: {
    			en: "Search",
    			nl: "Zoeken"
    		},
    		searching: {
    			en: "Searching...",
    			nl: "Op zoek naar..."
    		}, 
    		noCharactersLength: {
    			en: "Please enter at least 3 characters",
    			nl: "Vul minimaal 3 karakters"
    		},
    		systemStatistics: {
    			en: "System statistics",
    			nl: "Statistieken systeem"
    		},
    		airportDatabase: {
    			en: "Airport Database",
    			nl: "Airport-database"
    		},
    		totalRequests: {
    			en: "Total requests",
    			nl: "Totaal aantal verzoeken"
    		},
    		totalOKRequests: {
    			en: "Total OK requests",
    			nl: "Totaal OK verzoeken"
    		},
    		total4XXRequests: {
    			en: "Total 4XX requests",
    			nl: "Totaal 4XX verzoeken"
    		}
    		,
    		total5XXRequests: {
    			en: "Total 5XX requests",
    			nl: "Totaal 5XX verzoeken"
    		},
    		minimalRspTime : {
    			en: "Minimal response time",
    			nl: "Minimale responstijd"
    		},
    		maximalRspTime: {
    			en: "Maximal response time",
    			nl: "Maximale responstijd"
    		},
    		avgRspTime : {
    			en: "Average response time",
    			nl : "Gemiddelde antwoord tijd"
    		}
    		
    		
    };	
    	
});