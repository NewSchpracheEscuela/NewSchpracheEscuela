/**
 * Created by Ника Тихоновец on 08.05.2017.
 */

nseApp.controller('RegistrationController', RegistrationController);

function RegistrationController($scope, RequestService, USER_ROLES, $location) {

    $scope.userInfo = {
        login: '',
        password_hash: '',
        email: '',
        role: "ROLE_STUDENT",
        firstName: '',
        lastName: '',
        patronym: '',
        contactInfo: ''
    };
    function getPasswordHash(password) {
        
    }

    $scope.registration = function (userInfo) {
        RequestService.addItem('/users', userInfo, function (err, data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                $location.path('/authorization');
            }
        });
    };

}