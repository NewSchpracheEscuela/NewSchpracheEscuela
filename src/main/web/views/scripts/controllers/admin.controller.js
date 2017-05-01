/**
 * Created by Ника Тихоновец on 30.04.2017.
 */
nseApp.controller('AdminController', AdminController);

function AdminController($scope, $http) {
    $scope.base_url = "/users";

    $scope.initData = function (event,url) {
        $scope.base_url = url;
        $(".menu__list .menu__item").parent().find('.menu__item').removeClass("menu__item--active");
        $(event.target).parent().addClass("menu__item--active");
        getAllUsers();
    };

    var getAllUsers =  function(){$http({
        method: 'GET',
        url: $scope.base_url
    }).then(function successCallback(response) {
        if (response.status == 200){
            $scope.data = response.data;
            $scope.dataKeys = getKeys($scope.data[0]);

            $scope.formKeys = $scope.dataKeys.slice();
            $scope.formKeys.splice(0,1);
        }else{
            alert("Ошибка сервера!");
        }
    }, function errorCallback(response) {
        alert("Error : " +response.status);
    })};

    getAllUsers();
    $scope.deleteItem = function (item) {
        $scope.key = $scope.dataKeys[0].toString();
        $scope.id = item[$scope.key];
        $http({
            method: 'DELETE',
            url: $scope.base_url + '/' + $scope.id
        }).then(function successCallback(response) {
            if (response.status == 200)
            {
                getAllUsers();
            }else{
                alert("Ошибка сервера");
            }
        }, function errorCallback(response) {
            alert("Error : " +response.status);
        });
    };

    $scope.Item = {};
    $scope.addItem = function () {
        if($scope.addForm.$valid) {
            $http({
                method: 'POST',
                url: $scope.base_url,
                data: $scope.Item["formKeys"]
            }).then(function successCallback(response) {
                if (response.status == 200) {
                    $scope.hidePopup('addForm', '.popup__add');
                    getAllUsers();
                } else {
                    $scope.errorMessage = "Произошла ошибка добавления. Проверьте введенные данные! И попробуйте снова.";
                }
            }, function errorCallback(response) {
                $scope.errorMessage = "Произошла ошибка добавления. Проверьте введенные данные! И попробуйте снова.";
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
            $http({
                method: 'PUT',
                url: $scope.base_url + "/" + $scope.id,
                data: $scope.Item["formKeys"]
            }).then(function successCallback(response) {
                if (response.status == 200){
                    $scope.hidePopup('editForm','.popup__edit');
                    getAllUsers();
                }else{
                    $scope.errorMessage = "Произошла ошибка редактирования. Проверьте введенные данные! И попробуйте снова.";
                }
            }, function errorCallback(response) {
                $scope.errorMessage = "Произошла ошибка редактирования. Проверьте введенные данные! И попробуйте снова.";
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
        // document.forms[name].reset();
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

