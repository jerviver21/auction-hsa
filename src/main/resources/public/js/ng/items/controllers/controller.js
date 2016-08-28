var module = angular.module("auctionhsa.controllers");
module.controller("ItemCtrl", ["Item", function(Item) {
	var self = this;
	self.items = Item.query();
	
	self.edit = function(item_id){
		self.item = Item.get({id:item_id});
		console.log(self.item.id)
	}
	
	self.save = function () {  
	  self.item.save(
	    function(resp, headers){
	      self.item = resp
	      console.log("System asign id: "+self.item.id)
	    },
	    function(err){
	      self.errorMessage = "Saving process failed!! "+err;
	      console.log(err);
	    });
	};
}]);