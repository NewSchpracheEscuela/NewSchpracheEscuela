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
        .when('/registration',
            {
                templateUrl: '/resources/html/pages/registration.html',
                controller: 'RegistrationController'
            }
        )
        .when('/teacher',
            {
                templateUrl: '/resources/html/pages/teacher.html',
                controller: 'TeacherController'
            }
        )
        .when('/student',
            {
                templateUrl: '/resources/html/pages/student.html',
                controller: 'StudentController'
            }
        )
        .when('/404',
            {
                templateUrl: '/resources/html/pages/404.html'
            })
        .otherwise({
            redirectTo: "/404"
        });
    $locationProvider.html5Mode(true);
}

nseApp.run(function ($rootScope, AUTH_EVENTS, AuthService,$cookies,$http, $base64) {
    // $rootScope.$on('$locationChangeStart', function (event, next) {
    //     var authorizedRoles = next.data.authorizedRoles;
    //     if (!AuthService.isAuthorized(authorizedRoles)) {
    //         event.preventDefault();
    //         if (AuthService.isAuthenticated()) {
    //             $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
    //         } else {
    //             $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
    //         }
    //     }
    // });


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
        }, function(){

        });
    }
});