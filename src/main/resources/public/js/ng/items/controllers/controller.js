var module = angular.module("auctionhsa.controllers");
module.controller("ItemCtrl", ['$location', '$routeParams', "Item", "popupService", "fileUploadService", 
function($location, $routeParams,  Item, popupService, fileUploadService) {
	var self = this;
	self.item = new Item();
	self.currentImage = 0;
	
	self.edit = function(item_id){
		Item.get({id:item_id}, function(data) {
			data.auctionEnd = new Date(data.auctionEnd);
			self.item = data;
			if(self.item.images != null && self.item.images.length > 0){
				self.item.images[self.currentImage].selected = true;
			}
		}, function(err) {
			self.errorMessage = "Reading process failed!! "+err;
			popupService.showAlert("Error", err.data.message);
		});
	}
	
	self.save = function () {  
	  Item.save(self.item,
	    function(resp, headers){
		  self.item.id = resp.id;
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
	
	
	//Process of images
	self.loadImages = function(){
		popupService.showFormDialog("/view/items/dialogs/load_image.tmpl.html").then(
		function(object) {
			var uploadUrl = "/items/image/upload";
			fileUploadService.uploadFileToUrl(object.file, uploadUrl, self.item.id).then(
	    		function(res){
	    			self.item = res.data;
	    			self.currentImage = self.item.images.length -1;
	    			self.item.images[self.currentImage].selected = true;
	    		},
	    		function(err){
	    			popupService.showAlert("Error", err.data.message);
	    		}
	        );
		}, function() {
	    });
	}
	
	self.previousImage = function(){
		if(self.currentImage > 0){
			self.item.images[self.currentImage].selected = false;
			self.currentImage = self.currentImage - 1;
			self.item.images[self.currentImage].selected = true;
		}
	}
	
	
	self.nextImage = function(){
		if(self.item.images != null && self.currentImage < (self.item.images.length - 1) ){
			self.item.images[self.currentImage].selected = false;
			self.currentImage = self.currentImage  + 1;
			self.item.images[self.currentImage].selected = true;
		}
	}

	
	//Load the init data.
	if($routeParams.item_id){
		self.edit($routeParams.item_id);
	}else{
		self.items = Item.query();
	}

}]);