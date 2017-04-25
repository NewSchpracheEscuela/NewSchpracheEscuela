/**
 * Created by alexb on 05-Apr-17.
 */
angular.module('NSE',['ngRoute'])
    .config([
        '$locationProvider','$routeProvider',function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/index',
                    {
                        templateUrl:'/resources/html/index.html',
                        controller:'IndexController'
                    }
                )
                .when('/contacts',
                    {
                        templateUrl:'/resources/html/index.html',
                        controller:'IndexController'
                    })
                .otherwise({
                    redirectTo:"/index"
                });
            $locationProvider.html5Mode(true);
        }
    ])
    .controller('IndexController',
    [
        '$scope',function ($scope) {
            $scope.hello = "Angular index"

            $scope.contact = "Alex Budnitsky"
        }
    ]);