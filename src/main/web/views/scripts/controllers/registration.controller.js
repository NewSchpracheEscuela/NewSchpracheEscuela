/**
 * Created by Ника Тихоновец on 08.05.2017.
 */

nseApp.controller('RegistrationController', RegistrationController);

function RegistrationController($scope, RequestService, USER_ROLES, $location,$base64) {

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
        return $base64.encode(password+'NSE');
    }

    $scope.registration = function (userInfo) {
        let reginfo = userInfo;
        reginfo.password_hash = getPasswordHash(userInfo.password_hash);
        RequestService.addItem('/users', reginfo, function (err, data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                $location.path('/authorization');
            }
        });
    };

}