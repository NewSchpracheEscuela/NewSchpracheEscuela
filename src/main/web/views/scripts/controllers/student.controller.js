/**
 * Created by Ника Тихоновец on 09.05.2017.
 */

nseApp.controller('StudentController', StudentController);

function StudentController($scope, RequestService, $rootScope) {

    $scope.getLessons = function () {
        $scope.currentUser = $rootScope.current_userId;
        RequestService.getAll('/lessons/student/' + $scope.currentUser, function (err, data) {
            if (!err) {
                $scope.lessons = data;
            }
        });
    };
    $scope.getLessons();
}