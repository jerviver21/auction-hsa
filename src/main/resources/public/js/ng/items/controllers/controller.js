var module = angular.module("auctionhsa.controllers");
module.controller("ItemCtrl", ['$routeParams',"Item", function($routeParams, Item) {
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
	      self.item = resp
	      console.log("System asign id: "+self.item.id)
	    },
	    function(err){
	      self.errorMessage = "Saving process failed!! "+err;
	      console.log(err);
	    });
	};
	
	//Load the init data.
	if($routeParams.item_id){
		self.edit($routeParams.item_id);
	}else{
		self.items = Item.query();
	}
}]);