/**
 * Created by Ника Тихоновец on 02.05.2017.
 */

nseApp.factory('AuthService', function ($http, Session) {
    return {
        login: function (credentials) {
            return $http
                .post('/login', credentials)
                .then(function (res) {
                    Session.create(res.id, res.userid, res.role);
                });
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