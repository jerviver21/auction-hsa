var app = angular.module("auctionhsa", ["ngRoute", "auctionhsa.services", "auctionhsa.controllers", "auctionhsa.commons"]);

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
    })
    .when("/item_edit_image", {
        templateUrl : "/view/items/edit_images.html",
    });
});