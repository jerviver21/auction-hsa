var app = angular.module("auctionhsa", ["ngRoute", "auctionhsa.services", "auctionhsa.controllers", "auctionhsa.commons"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "/view/main.html"
    })
    .when("/auction_edit/:id", {
		templateUrl: "/view/auctions/offer.html"
    })
    .when("/item_list", {
        templateUrl : "/view/items/list.html"
    })
    .when("/item_edit/:item_id", {
        templateUrl : "/view/items/edit.html"
    })
    .when("/item_edit", {
        templateUrl : "/view/items/edit.html"
    })
    .when("/item_edit_image/:item_id", {
        templateUrl : "/view/items/edit_images.html"
    })
    .when("/user_edit", {
        templateUrl : "/view/users/edit.html"
    })
    .when("/user_edit/:id", {
        templateUrl : "/view/users/edit.html"
    })
    .when("/user_list", {
        templateUrl : "/view/users/list.html"
    })
    .when("/login", {
        templateUrl : "/view/login.html"
    })
    ;
    
    $routeProvider.otherwise({
		redirectTo: '/'
	});
});


/*
 * Para comprender el servicio $q, este genera promesas y funciona para el asincronismo
 * retornamos un promise (creada a través de $q) y realizamos la operación, cuando la operación
 * acabe a defered le pasamos el dato del resultado o el error en su defecto. 7
 * Y la promesa va a tomar estos valores.
function sumaAsincrona(a,b) {
   var defered=$q.defer();
   var promise=defered.promise;
    
   $timeout(function() {
      try{
         var resultado=a+b;
         defered.resolve(resultado);
      } catch (e) {
         defered.reject(e);
      }   
   },3000); 
    
   return promise;
}
 * 
 */