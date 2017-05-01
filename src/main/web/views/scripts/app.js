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
        .when('/admin',
            {
                templateUrl: '/resources/html/admin.html',
                controller: 'AdminController'
            }
        )
        .when('/all_comments',
            {
                templateUrl: '/resources/html/comments.html',
                controller: 'CommentsController'
            }
        )
        .when('/all_news',
            {
                templateUrl: '/resources/html/news.html',
                controller: 'NewsController'
            }
        )
        .otherwise({
            redirectTo: "/index"
        });
    $locationProvider.html5Mode(true);
};