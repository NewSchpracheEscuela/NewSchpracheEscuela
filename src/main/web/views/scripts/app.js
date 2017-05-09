var nseApp = angular.module('NSE', ['ngRoute', 'ngMap','ngCookies','base64'])
    .config(nseAppConfig);

function nseAppConfig ($routeProvider,$locationProvider,USER_ROLES) {
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
                data:{
                    authorizedRoles: [USER_ROLES.admin]
                }
            }
        )
        .when('/all_comments',
            {
                templateUrl: '/resources/html/pages/comments.html',
                controller: 'CommentsController',
                data:{
                    authorizedRoles: [USER_ROLES.admin]
                }
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
        .when('/registration',
            {
                templateUrl: '/resources/html/pages/registration.html',
                controller: 'RegistrationController'
            }
        )
        // .when('/403',
        //     {
        //         templateUrl: '/resources/html/pages/403.html',
        //     }
        // )
        .when('/404',
            {
                templateUrl: '/resources/html/pages/404.html'
            }
        )
        .otherwise({
            redirectTo: "/404"
        });
    $locationProvider.html5Mode(true);
}

nseApp.run(function ($rootScope, AUTH_EVENTS, AuthService,$cookies,$http, $base64,$location) {
    $rootScope.$on('$routeChangeStart', function (event, next) {
        if (next.data){
            var authorizedRoles = next.data.authorizedRoles;
            if (!AuthService.isAuthorized(authorizedRoles)) {
                $location.path('/403')
            }
        }
    });


    if($cookies.get('Authorization')){
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.get('Authorization');
        var req = {
            method: 'GET',
            url: '/checklogin',
            headers: {
                'Authorization': 'Basic ' + $cookies.get('Authorization')
            },
        }

        $http(req).then(function(){
            var credentialsString = $base64.decode($cookies.get('Authorization'));
            var credentials = {'username':credentialsString.split(':')[0],
                                'password':credentialsString.split(':')[1]}
            AuthService.login(credentials);
        });
    }
});