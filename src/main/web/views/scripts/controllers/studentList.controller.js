/**
 * Created by Ника Тихоновец on 12.05.2017.
 */

nseApp.controller('StudentListController', StudentListController);

function StudentListController($scope, RequestService, $rootScope, $location) {
    $scope.groupId = $location.id;
};