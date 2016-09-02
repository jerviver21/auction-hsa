var module = angular.module("auctionhsa.controllers");
module.controller("ItemCtrl", ['$routeParams', '$mdDialog',"Item", function($routeParams, $mdDialog, Item) {
	var self = this;
	self.item = new Item();
	
	self.edit = function(item_id){
		Item.get({id:item_id}, function(data) {
			self.item = data;
		}, function(err) {
			self.errorMessage = "Getting process failed!! "+err;
		    console.log(err);
		});
	}
	
	self.save = function () {  
	  Item.save(self.item,
	    function(resp, headers){
		  self.item.id = resp.id;
	      self.showAlert("Success","The item was sucessfully saved!!");
	    },
	    function(err){
	      self.errorMessage = "Saving process failed!! "+err;
	      self.showAlert("Error", err.data.message);
	    });
	};
	
	//Load the init data.
	if($routeParams.item_id){
		self.edit($routeParams.item_id);
	}else{
		self.items = Item.query();
	}
	
	
	//Controlling efects in view
	self.showAlert = function(title, content) {
		$mdDialog.show( 
				$mdDialog.alert().parent(angular.element(document.querySelector('#popupContainer')))
				.clickOutsideToClose(true)
				.title(title)
				.textContent(content)
				.ariaLabel('Alert Dialog Demo')
				.theme('altTheme')
				.ok('Got it!')
				
		    );
	};
	
}]);