var module = angular.module("auctionhsa.controllers");
module.controller("MainCtrl", ['LoginService',  function(LoginService) {
	var self = this;
	
	LoginService.checkAuth();
	
	self.isAuthenticated = function(){
		return LoginService.isAuthenticated();
	}
	
	self.getUserName = function(){
		return LoginService.getPrincipal();
	}
	
	self.logout = function(){
		return LoginService.logout();
	}

}]);