var module = angular.module("auctionhsa.controllers");
module.controller("ItemCtrl", ['$location', '$routeParams', "Item", "popupService", "fileUpload", 
function($location, $routeParams,  Item, popupService, fileUpload) {
	var self = this;
	self.item = new Item();
	
	self.edit = function(item_id){
		Item.get({id:item_id}, function(data) {
			data.auctionEnd = new Date(data.auctionEnd);
			self.item = data;
		}, function(err) {
			self.errorMessage = "Reading process failed!! "+err;
			popupService.showAlert("Error", err.data.message);
		});
	}
	
	self.save = function () {  
	  Item.save(self.item,
	    function(resp, headers){
		  self.item.id = resp.id;
		  //popupService.showAlert("Success","The item was sucessfully saved!!");
		  $location.path("/item_edit_image/"+self.item.id);
	    },
	    function(err){
	      self.errorMessage = "Saving process failed!! "+err;
	      popupService.showAlert("Error", err.data.message);
	    });
	};
	
	self.remove = function(item){
		popupService.showConfirm('Desea borrar el Item?','').then(function() {
			console.log("Remover: "+item.id);
			Item.remove(item,
			    function(resp, headers){
				  self.item.id = resp.id;
				  popupService.showAlert("Success","The item was sucessfully remove!!");
				  self.items = Item.query();
			    },
			    function(err){
			      self.errorMessage = "Saving process failed!! "+err;
			      popupService.showAlert("Error", err.data.message);
			    });
		}, function() {
		  console.log("No remover");
		});
	}
	
	self.uploadFile = function(){
		console.log('file is ' );
        console.dir(self.file);
        var uploadUrl = "/items/image/upload";
        fileUpload.uploadFileToUrl(self.file, uploadUrl, self.item.id).then(
    		function(data){
    			console.log(data);
    		},
    		function(err){
    			popupService.showAlert("Error", err.data.message);
    		}
        );
		
	}
	
	//Load the init data.
	if($routeParams.item_id){
		self.edit($routeParams.item_id);
	}else{
		self.items = Item.query();
	}

}]);