/**
 * Created by Ника Тихоновец on 02.05.2017.
 */

nseApp.factory('AuthService', function ($http, Session,$cookies,$location,$route) {
    return {
        login: function (credentials) {
            return $http
                .post('/sign_in', credentials)
                    .then(function (res) {
                        Session.create(res.data.userId, res.data.role);
                        // var interceptor = function() {
                        //     return {
                        //         'request': function(config) {
                        //             config.headers['Authorization'] = res.data.authHeader;
                        //         }
                        //     }
                        // };
                        $http.defaults.headers.common['Authorization'] = 'Basic ' + res.data.authHeader;
                        $cookies.put('Authorization',res.data.authHeader)
                    });
        },
        logout: function () {
                Session.destroy();
                $http.defaults.headers.common['Authorization'] = null;
                $cookies.remove('Authorization')
                if($location.path() === '/index'){
                    $route.reload()
                }else{
                    $location.path('/index');
                }
        },
        isAuthenticated: function () {
            return !!Session.userId;
        },
        isAuthorized: function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                authorizedRoles = [authorizedRoles];
            }
            return (this.isAuthenticated() &&
            authorizedRoles.indexOf(Session.userRole) !== -1);
        }
    };
});