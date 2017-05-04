var nseApp = angular.module('NSE', ['ngRoute', 'ngMap'])
    .config(nseAppConfig);

function nseAppConfig ($routeProvider,$locationProvider) {
    $routeProvider
        .when('/index',
            {
                templateUrl: '/resources/html/pages/index.html',
                controller: 'IndexController'
            }
        )
        .when('/contacts',
            {
                templateUrl: '/resources/html/pages/contact.html'
            }
        )
        .when('/admin',
            {
                templateUrl: '/resources/html/pages/admin.html',
                controller: 'AdminController',
               /* data: {
                    authorizedRoles: [USER_ROLES.admin]
                }*/
            }
        )
        .when('/all_comments',
            {
                templateUrl: '/resources/html/pages/comments.html',
                controller: 'CommentsController'
            }
        )
        .when('/all_news',
            {
                templateUrl: '/resources/html/pages/news.html',
                controller: 'NewsController'
            }
        )
        .when('/authorization',
            {
                templateUrl: '/resources/html/pages/login.html',
                controller: 'LoginController'
            }
        )
        .otherwise({
            redirectTo: "/index"
        });
    $locationProvider.html5Mode(true);
}
/*
nseApp.run(function ($rootScope, AUTH_EVENTS, AuthService) {
    $rootScope.$on('$locationChangeStart', function (event, next) {
        var authorizedRoles = next.data.authorizedRoles;
        if (!AuthService.isAuthorized(authorizedRoles)) {
            event.preventDefault();
            if (AuthService.isAuthenticated()) {
                $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
            } else {
                $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
            }
        }
    });
});*/