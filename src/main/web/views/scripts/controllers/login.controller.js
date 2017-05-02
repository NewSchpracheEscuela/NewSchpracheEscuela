/**
 * Created by Ника Тихоновец on 02.05.2017.
 */

nseApp.controller('LoginController', LoginController);

function LoginController($scope, $rootScope, AUTH_EVENTS, AuthService) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $scope.setCurrentUser(user);
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
    };
}