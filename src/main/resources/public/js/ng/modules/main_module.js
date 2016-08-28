var app = angular.module("auctionhsa", ["ngRoute", 
                                        "ngResource", 
                                        "auctionhsa.services", 
                                        "auctionhsa.controllers"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/item_list", {
        templateUrl : "/view/items/list.html",
    })
    .when("/item_edit", {
        templateUrl : "/view/items/edit.html",
    });
});