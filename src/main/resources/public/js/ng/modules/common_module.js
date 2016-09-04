angular.module("auctionhsa.commons", ['ngMaterial', 'ngMessages'])
.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('altTheme')
	  	.primaryPalette('pink')
	    .accentPalette('orange').dark();
});