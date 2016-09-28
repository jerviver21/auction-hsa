var module = angular.module("auctionhsa.services");
module.factory("LoginService", ["$http", function($http) {
	
	//Pendiente ******************
	var service = {
		usuario: '',
		isLoggedIn: false,
		
		login : function(usr) {
			console.log('Authenticating with credentials... '+usr.usuario+' - '+usr.clave);
			service.usuario=usr;
			return  $http.post('login', usr).then(function(response){
				service.isLoggedIn=true;
				return response;
			});
		},
		
		logout : function() {
			console.log('Logout for... ', service.usuario.usuario);
			config ={method:"GET",
					 url:"/logout",
					 params: {"usr":service.usuario.usuario}};
			return $http(config).then(function(response) {
				service.isLoggedIn=false;
			});
		},
		
		session : function() {
			console.log('Is authenticated  ', service.usuario.usuario);
			config ={method:"GET",
					 url:"/session",
					 params: {"usr":service.usuario.usuario}};
			return $http(config).then(function(response){
				service.isLoggedIn=true;
				return response;
			});
		},
		
	};
	
	return service;
	//Pendiente ******************
	
	
	return {
		getPrincipal : function(){
			return $http.get("/user");
		}
	};
}]);