/**
 * Created by Ника Тихоновец on 02.05.2017.
 */

nseApp.controller('LoginController', LoginController);

function LoginController($scope, $rootScope, AUTH_EVENTS, AuthService,$location) {

    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $location.path('/index');
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
    };

    $rootScope.isAuthenticated = AuthService.isAuthenticated();

    $scope.logout = function () {
        AuthService.logout()
    }
}