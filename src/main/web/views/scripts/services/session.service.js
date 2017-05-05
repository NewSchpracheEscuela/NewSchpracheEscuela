/**
 * Created by Ника Тихоновец on 02.05.2017.
 */

nseApp.service('Session', function () {
    this.create = function (userId, userRole) {
        this.userId = userId;
        this.userRole = userRole;
    };
    this.destroy = function () {
        this.userId = null;
        this.userRole = null;
    };
    return this;
});