/**
 * Created by Ника Тихоновец on 30.04.2017.
 */
nseApp.controller('AdminController', AdminController);

function AdminController($scope, RequestService) {
    $scope.base_url = "/users";
    $scope.currentDate = new Date();

    $("li.header__item:contains('Админка')").addClass('header__item--active');

    $scope.initData = function (event,url) {
        $scope.base_url = url;
        changeUrl();
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

    $scope.getItems = function (url) {
        RequestService.getAll(url, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.items = data;
            }
        });
    } ;

    $scope.getUsers = function () {
        RequestService.getAll('/users', function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.users = data;
            }
        });
    } ;

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
        let data = $scope.Item["formKeys"];
        RequestService.addItem($scope.base_url, data, function (err, data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                $scope.hidePopup('.popup__add');
                getAll();
            }
        });
    };

    $scope.Item = {};
    $scope.editItemPopup = function (popup, item) {
        $scope.edit = item;
        $scope.Item.formKeys = item;
        $scope.showPopup(popup);
    };

    $scope.editItem = function () {
        $scope.key = $scope.dataKeys[0].toString();
        $scope.id = $scope.edit[$scope.key];
        let url = $scope.base_url + "/" + $scope.id;
        let data = $scope.Item["formKeys"];
        RequestService.editItem(url, data, function (err, data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                $scope.hidePopup('.popup__edit');
                getAll();
            }
        });
    };

    var getKeys = function(obj){
        var keys = [];
        for(var key in obj){
            keys.push(key);
        }
        return keys;
    };

    $scope.hidePopup = function (popup) {
        $(popup).hide();
        $scope.Item = {};
        getAll();
    };

    $scope.showPopup = function (popup) {
        $scope.errorMessage = "";
        $(popup).show();
    };

    var changeUrl = function () {
        $scope.edit_form = 'resources/html/partials' + $scope.base_url + '/edit_form.html';
        $scope.add_form = 'resources/html/partials' + $scope.base_url + '/add_form.html';
        $scope.table_view = 'resources/html/partials' + $scope.base_url + '/table_view.html';
    };
    changeUrl();

}

