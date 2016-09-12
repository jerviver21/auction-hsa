var module = angular.module("auctionhsa.controllers");
module.controller("UsrCtrl", ['$location', '$routeParams', "User", "popupService", "Common", 
function($location, $routeParams,  User, popupService, Common) {
	var self = this;
	self.user = new User();
	self.regions = Common.getRegions();
	
	self.edit = function(id){
		User.get({id:id}, function(data) {
			self.user = data;
		}, function(err) {
			self.errorMessage = "Reading process failed!! "+err;
			popupService.showAlert("Error", err.data.message);
		});
	}
	
	self.save = function () {  
	  User.save(self.user,
	    function(resp, headers){
		  self.user.id = resp.id;
	    },
	    function(err){
	      self.errorMessage = "Saving process failed!! "+err;
	      popupService.showAlert("Error", err.data.message);
	    });
	};
	
	self.remove = function(user){
		popupService.showConfirm('Desea borrar el User?','').then(function() {
			User.remove(user,
			    function(resp, headers){
				  self.user.id = resp.id;
				  popupService.showAlert("Success","The user was sucessfully remove!!");
				  self.users = User.query();
			    },
			    function(err){
			      self.errorMessage = "Saving process failed!! "+err;
			      popupService.showAlert("Error", err.data.message);
			    });
		}, function() {
		  console.log("No remover");
		});
	}
	
	self.getCities = function(){
		self.cities = Common.getCities({id: self.idRegion});
	}

	
	//Load the init data.
	if($routeParams.id){
		self.edit($routeParams.id);
	}else{
		self.users = User.query();
	}

}]);