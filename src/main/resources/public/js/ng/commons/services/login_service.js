var module = angular.module("auctionhsa.services");

module.factory("LoginService", ["$http", function($http) {
	user = null;
	isLoggedIn = false;
	
	return {
		checkAuth : function(){
			$http.get("/user").then(function(data){
				isLoggedIn =true;
				user = data.userAuthentication.details.name;
			}, function(error){
				console.log('Its not authenticated!!');
				user = null;
				isLoggedIn = false;
			});
		},
		
		logout : function(){
			isLoggedIn =false;
			user = null;
		},
		
		isAuthenticated : function(){
			return isLoggedIn;
		},
		
		getPrincipal : function(){
			return user;
		}
	};
}]);