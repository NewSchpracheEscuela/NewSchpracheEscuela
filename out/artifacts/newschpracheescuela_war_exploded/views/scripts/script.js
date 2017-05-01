var nseApp = angular.module('NSE', ['ngRoute', 'ngMap'])
    .config(nseAppConfig);

function nseAppConfig ($routeProvider,$locationProvider) {
    $routeProvider
        .when('/index',
            {
                templateUrl: '/resources/html/index.html',
                controller: 'IndexController'
            }
        )
        .when('/contacts',
            {
                templateUrl: '/resources/html/contact.html'
            }
        )
        .when('/backend',
            {
                templateUrl: '/resources/html/admin.html',
                controller: 'AdminController'
            }
        )
        .otherwise({
            redirectTo: "/index"
        });
    $locationProvider.html5Mode(true);
};