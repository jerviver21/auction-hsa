var module = angular.module("auctionhsa.services");
module.factory("Common", ["$resource", function($resource) {
	return $resource('/commons/:id', { id: '@_id' }, {
		//Forma de agregar otros parametros
		getRegions: {
		      method:'GET',
		      url: '/commons/regions',
		      isArray:true
		},
		getCities: {
		      method:'GET',
		      url: '/commons/cities/:id',
		      isArray:true
		}
		
	});
}]);