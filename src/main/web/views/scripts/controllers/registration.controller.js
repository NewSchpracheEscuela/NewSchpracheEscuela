/**
 * Created by Ника Тихоновец on 08.05.2017.
 */

nseApp.controller('RegistrationController', RegistrationController);

function RegistrationController($scope, RequestService, USER_ROLES, $location,$base64) {

    $scope.userInfo = {
        login: '',
        password_hash: '',
        email: '',
        role: USER_ROLES.guest,
        firstName: '',
        lastName: '',
        patronym: '',
        contactInfo: ''
    };
    function getPasswordHash(password) {
        return $base64.encode(password+'NSE');
    }

    $scope.registration = function (userInfo) {
        let reginfo = Object.assign({},userInfo);
        reginfo.password_hash = getPasswordHash(userInfo.password_hash);
        RequestService.addItem('/users', reginfo, function (err, data) {
            if(err){
                $('.reg__error').show();
                $scope.errorMessage = err.code;
            } else{
                $location.path('/authorization');
            }
        });
    };

}