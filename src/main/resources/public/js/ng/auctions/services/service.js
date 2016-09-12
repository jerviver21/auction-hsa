var module = angular.module("auctionhsa.services");
module.factory("Auction", ["$resource", function($resource) {
	return $resource('/auctions/:id', { id: '@_id' }, {
		//Forma de agregar otros parametros
		placeBid: {
		      method:'POST',
		      url: '/auctions/:amount',
		      isArray: false
		}
	});
}]);