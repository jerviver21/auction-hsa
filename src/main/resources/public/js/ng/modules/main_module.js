var app = angular.module("auctionhsa", ["ngRoute", "auctionhsa.services", "auctionhsa.controllers", "auctionhsa.commons"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "/view/main.html",
    })
    .when("/auction_edit/:id", {
        templateUrl : "/view/auctions/offer.html",
    })
    .when("/item_list", {
        templateUrl : "/view/items/list.html",
    })
    .when("/item_edit/:item_id", {
        templateUrl : "/view/items/edit.html",
    })
    .when("/item_edit", {
        templateUrl : "/view/items/edit.html",
    })
    .when("/item_edit_image/:item_id", {
        templateUrl : "/view/items/edit_images.html",
    })
    .when("/user_edit", {
        templateUrl : "/view/users/edit.html",
    })
    .when("/user_edit/:id", {
        templateUrl : "/view/users/edit.html",
    })
    .when("/user_list", {
        templateUrl : "/view/users/list.html",
    })
    .when("/login", {
        templateUrl : "/view/login.html",
    })
    ;
});