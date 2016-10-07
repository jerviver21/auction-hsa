var module = angular.module("auctionhsa.controllers");
module.controller("AuctionCtrl", ['$location', '$routeParams', "Auction", "popupService", "LoginService",
function($location, $routeParams,  Auction, popupService, LoginService) {
	var self = this;
	self.auction = new Auction();
	self.currentImage = 0;

	self.edit = function(id){
		Auction.get({id:id}, function(data) {
			data.auctionEnd = new Date(data.auctionEnd);
			self.auction = data;
			if(self.auction.images != null && self.auction.images.length > 0){
				self.auction.images[self.currentImage].selected = true;
			}
		}, function(err) {
			self.errorMessage = "Reading process failed!! "+err;
			popupService.showAlert("Error", err.data.message);
		});
	}
	
	self.makeOffer = function(){
		popupService.showFormDialog("/view/auctions/dialogs/bid.tmpl.html").then(
		function(object) {
			Auction.placeBid({amount:object.amount},self.auction,
			    function(resp, headers){
				  self.auction.maxBidAmount = resp.maxBidAmount;
				  if(!resp.user.name){
					  $location.path("/user_edit/"+resp.user.id);
				  }
			    },
			    function(err){
			      self.errorMessage = "Saving process failed!! "+err;
			      popupService.showAlert("Error", err.data.message);
			    }
			);
			
		}, function() {
	    });
	}
	
	//Navigation
	self.goOfferView = function(id){
		
		if(LoginService.isAuthenticated()){
			$location.path("/auction_edit/"+id);
		}else{
			$location.path("/login");
		}
	}
	
	
	//Control over images
	self.previousImage = function(){
		if(self.currentImage > 0){
			self.auction.images[self.currentImage].selected = false;
			self.currentImage = self.currentImage - 1;
			self.auction.images[self.currentImage].selected = true;
		}
	}
	
	
	self.nextImage = function(){
		if(self.auction.images != null && self.currentImage < (self.auction.images.length - 1) ){
			self.auction.images[self.currentImage].selected = false;
			self.currentImage = self.currentImage  + 1;
			self.auction.images[self.currentImage].selected = true;
		}
	}
	
	

	
	//Load the init data.
	if($routeParams.id){
		self.edit($routeParams.id);
	}else{
		self.auctions = Auction.query();
	}

}]);