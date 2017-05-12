/**
 * Created by Ника Тихоновец on 02.05.2017.
 */

nseApp.controller('LoginController', LoginController);

function LoginController($scope, $rootScope, AUTH_EVENTS, AuthService,$location,$base64) {

    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function (credentials) {
        let authinf = Object.assign({},credentials);
        authinf.password = $base64.encode(authinf.password + 'NSE');
        AuthService.login(authinf).then(function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $location.path('/index');
        }, function () {
            $('.reg__error').show();
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
    };

    $rootScope.isAuthenticated = AuthService.isAuthenticated();

    $scope.logout = function () {
        AuthService.logout()
    }
}