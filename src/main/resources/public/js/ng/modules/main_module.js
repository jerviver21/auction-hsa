var app = angular.module("auctionhsa", ["ngRoute", "auctionhsa.services", "auctionhsa.controllers"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/item_list", {
        templateUrl : "/view/items/list.html",
    })
    .when("/item_edit/:item_id", {
        templateUrl : "/view/items/edit.html",
    })
    .when("/item_edit", {
        templateUrl : "/view/items/edit.html",
    });
});