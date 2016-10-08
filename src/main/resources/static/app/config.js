/**
 * Created by gresodimedjo on 5/21/15.
 */
(function() {
    'use strict';

    var app = angular.module('walenoApp');
    
    app.config(
        function($stateProvider, 
        		 $urlRouterProvider,
        		 $locationProvider) {
        	
            $urlRouterProvider.otherwise('/navigation/dashboard');
            
            $stateProvider
	           .state('navigation', {
	        	   url: "/navigation",
	               templateUrl: "app/navigation/navigation.html"
	            })
	            
	            .state('navigation.dashboard', {
	               url: "/dashboard",
	               templateUrl: "app/dashboard/dashboard.html"
				});
            
            $locationProvider.html5Mode({
          	  enabled: true,
          	  requireBase: false
            });
        }
   );
   
})();

