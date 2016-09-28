var module = angular.module("auctionhsa.controllers");
module.controller("LoginCtrl", ['LoginService',  function(LoginService) {
	var self = this;
	
	LoginService.getPrincipal().success(function(data) {
      self.user = data.userAuthentication.details.name;
      self.authenticated = true;
    }).error(function() {
      self.user = "N/A";
      self.authenticated = false;
    });

}]);