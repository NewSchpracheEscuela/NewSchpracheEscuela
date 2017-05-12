/**
 * Created by Ника Тихоновец on 30.04.2017.
 */
nseApp.controller('AdminController', AdminController);

function AdminController($scope, RequestService) {
    $scope.base_url = "/users";
    $scope.currentDate = new Date();


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

    $scope.getAuthor= function () {
        let i;
        for(i=0; i < $scope.data.length; i++){
            let user_id = $scope.data[i]['teacher_id'];
            RequestService.getItemById('/users/'+user_id, function(err,info){
                if (err){
                    alert(err.message);
                }else{
                    $scope.data[i]['name'] = info['firstName']+" "+ info['lastName'];
                }
            });
        }
    };

    $scope.getAuthorName = function (id) {
        let url = '/people/' + $scope.id;
        RequestService.getItemById(url, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.author = data;
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
               $scope.hidePopup('.popup__delete');
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
    $scope.getNameById = function (user_id) {
        RequestService.getItemById('users/'+user_id,function (err,data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                return data.lastName + data.firstName;
            }
            })
        };
    $scope.Item = {};
    $scope.editItemPopup = function (popup, item) {
        $scope.edit = item;
        $scope.Item.formKeys = item;
        $scope.showPopup(popup);
    };

    $scope.deleteItemPopup = function (popup,item) {
        $scope.itemForDelete = item;
        $scope.showPopup(popup);
    };
    $scope.delete = function () {
        $scope.deleteItem($scope.itemForDelete);
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
        $('.body').css("overflow","auto");
        $scope.Item = {};
        getAll();
    };

    $scope.showPopup = function (popup) {
        $scope.errorMessage = "";
        $(popup).show();
        $('.body').css("overflow","hidden");
    };

    var changeUrl = function () {
        $scope.edit_form = 'resources/html/partials' + $scope.base_url + '/edit_form.html';
        $scope.add_form = 'resources/html/partials' + $scope.base_url + '/add_form.html';
        $scope.table_view = 'resources/html/partials' + $scope.base_url + '/table_view.html';
    };
    changeUrl();

    $scope.blankPopup = function (item) {
        $scope.format;
        $scope.type= "blank";
        $scope.itemDoc = item['user_id'];
        $scope.showPopup('.popup__choice');
    };

    $scope.diplomaPopup = function (item) {
        $scope.format;
        $scope.type= "diploma";
        $scope.itemDoc = item['user_id'];
        $scope.showPopup('.popup__choice');
    };

    $scope.teachersPopup = function () {
        $scope.format;
        $scope.type= "teachers";
        $scope.showPopup('.popup__choice');
    };

    $scope.groupsPopup = function () {
        $scope.format;
        $scope.type= "groups";
        $scope.showPopup('.popup__choice');
    };
    $scope.languagesPopup = function () {
        $scope.format;
        $scope.type= "languages";
        $scope.showPopup('.popup__choice');
    };


    $scope.hidePopupDoc = function (typeDoc) {
        $('.popup__choice').hide();
        $('.body').css("overflow","auto");
        $scope.format = typeDoc;
        let url
        if($scope.type == 'blank' || $scope.type == 'diploma'){
             url= "/documents/"+$scope.type + "/"+ $scope.format+"?user_id="+$scope.itemDoc+"&isProtected=true";
        }else{
            url = "/documents/"+$scope.type + "/"+ $scope.format+"?"+"isProtected=true";
        }
        window.open(url);
    }
}

