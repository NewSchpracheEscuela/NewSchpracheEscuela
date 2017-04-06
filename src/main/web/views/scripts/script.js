/**
 * Created by alexb on 05-Apr-17.
 */
var app = angular.module('NSE',['ngRoute']);
    app.config([
        '$locationProvider','$routeProvider',function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/hello',
                    {
                        templateUrl:'/resources/html/index.html',
                        controller:'IndexController'
                    }
                )
                .otherwise({
                    redirectTo:"/index"
                });
            $locationProvider.html5Mode(true);
        }
    ]);
    app.controller('IndexController',
    [
        '$scope',function ($scope) {
            $scope.hello = "index"
        }
    ]);