/**
 * Created by Ника Тихоновец on 30.04.2017.
 */
nseApp.controller('AdminController', AdminController);

function AdminController($scope, $http, RequestService) {
    $scope.base_url = "/users";

    $scope.initData = function (event,url) {
        $scope.base_url = url;
        $(".menu__list .menu__item").parent().find('.menu__item').removeClass("menu__item--active");
        $(event.target).parent().addClass("menu__item--active");
        getAll();
    };

    var getAll = function () {
        RequestService.getAll($scope.base_url, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.data = data;
                $scope.dataKeys = getKeys($scope.data[0]);

                $scope.formKeys = $scope.dataKeys.slice();
                $scope.formKeys.splice(0,1);
            }
        });
    };

    getAll();
    $scope.deleteItem = function (item) {
        $scope.key = $scope.dataKeys[0].toString();
        $scope.id = item[$scope.key];
        let url = $scope.base_url + '/' + $scope.id;
        RequestService.deleteItem(url, function (err, data) {
           if(err){
               alert(err.message)
           } else{
               getAll();
           }
        });
    };

    $scope.Item = {};
    $scope.addItem = function () {
        if($scope.addForm.$valid) {
            let data = $scope.Item["formKeys"];
            RequestService.addItem($scope.base_url, data, function (err, data) {
                if(err){
                    $scope.errorMessage = err.code;
                } else{
                    $scope.hidePopup('addForm', '.popup__add');
                    getAll();
                }
            });
        }else{
            $scope.errorMessage = "Заполните все поля!";
        }
    };

    $scope.Item = {};
    $scope.editItemPopup = function (popup, item) {
        $scope.edit = item;
        $scope.Item.formKeys = item;
        $scope.showPopup(popup);
    };

    $scope.editItem = function () {
        if($scope.editForm.$valid){
            $scope.key = $scope.dataKeys[0].toString();
            $scope.id = $scope.edit[$scope.key];
            let url = $scope.base_url + "/" + $scope.id;
            let data = $scope.Item["formKeys"];
            RequestService.editItem(url, data, function (err, data) {
                if(err){
                    $scope.errorMessage = err.code;
                } else{
                    $scope.hidePopup('editForm','.popup__edit');
                    getAll();
                }
            });
        }else {
            $scope.errorMessage = "Заполните все поля!";
        }
    };

    var getKeys = function(obj){
        var keys = [];
        for(var key in obj){
            keys.push(key);
        }
        return keys;
    };

    $scope.hidePopup = function (name, popup) {
        $(popup).hide();
        $(".popup__bg").hide();
        $scope.Item = {};
    };

    $scope.showPopup = function (popup) {
        $scope.errorMessage = "";
        $(popup).show();
        $(".popup__bg").show();
        $("body").animate({"scrollTop":0},"slow");
    };
}

