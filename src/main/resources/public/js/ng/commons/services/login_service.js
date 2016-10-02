var module = angular.module("auctionhsa.services");

module.factory("LoginService", ["$http", "$location", function($http, $location) {
	user = null;
	isLoggedIn = false;
	
	return {
		checkAuth : function(){
			return $http.get("/user").success(function(data){
				isLoggedIn =true;
				console.log(data);
				user = data.userAuthentication.details.name;
			});
		},
		
		logout : function(){
			return $http.post('/logout', {}).success(function() {
				isLoggedIn =false;
				user = null;
				$location.path("/");
			});
		},
		
		isAuthenticated : function(){
			return isLoggedIn;
		},
		
		getPrincipal : function(){
			return user;
		}
	};
}]);