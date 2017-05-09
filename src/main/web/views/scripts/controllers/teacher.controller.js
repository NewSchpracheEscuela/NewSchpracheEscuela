/**
 * Created by Ника Тихоновец on 09.05.2017.
 */

nseApp.controller('TeacherController', TeacherController);

function TeacherController($scope, RequestService, $rootScope) {

    $scope.getAboutInfo = function () {
        $scope.currentUser = $rootScope.current_userId;
        RequestService.getItemById('/users/' + $scope.currentUser, function (err, data) {
            if (!err) {
                //  alert(err.message);
                $scope.about_info = data;
            }
        });
    };

    $scope.getLessons = function () {
        $scope.currentUser = $rootScope.current_userId;
        RequestService.getAll('/lessons/teacher/' + $scope.currentUser, function (err, data) {
            if (!err) {
                $scope.lessons = data;
                $scope.groups = getGroups($scope.lessons);
            }
        });
    };

    function getGroups(arr) {
        var result = [];

        nextInput:
            for (var i = 0; i < arr.length; i++) {
                var str = arr[i];
                for (var j = 0; j < result.length; j++) {
                    if (result[j]['group'] == str['group']) continue nextInput;
                }
                str['link'] = "/teacher/group/"+str['group'];
                result.push(str);
            }
        return result;
    }

    $scope.getLessons();
}
