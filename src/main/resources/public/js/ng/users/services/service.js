var module = angular.module("auctionhsa.services");
module.factory("User", ["$resource", function($resource) {
	return $resource('/users/:id', { id: '@_id' }, {

	});
}]);