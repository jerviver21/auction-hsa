var module = angular.module("auctionhsa.services");
module.factory("Item", ["$resource", function($resource) {
	return $resource('/items/:id', { id: '@_id' }, {
		update: {
		  method: 'PUT'
		}
	});
}]);