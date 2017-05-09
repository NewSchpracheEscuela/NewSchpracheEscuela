/**
 * Created by Ника Тихоновец on 01.05.2017.
 */

nseApp.controller('NewsController', NewsController);

function NewsController($scope, RequestService) {


    var getAllNews = function () {
        RequestService.getAll('/news', function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.all_news = data;
            }
        });
    };

    // var getAuthor= function (item ) {
    //     let user_id = $scope.all_news[item]['author'];
    //     RequestService.getItemById('/users/'+user_id, function(err,data){
    //         if (err){
    //             alert(err.message);
    //         }else{
    //             $scope.all_news[item]['author'] = data['firstName']+" "+ data['lastName'];
    //         }
    //     });
    // };

    $scope.Item = {};
    $scope.addItem = function () {
        let data = $scope.Item["formKeys"];
        RequestService.addItem("/news", data, function (err, data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                $scope.hidePopup('.popup__add');
                getAllNews();
            }
        });
    };

    $scope.showPopup = function () {
        $scope.currentDate = new Date();
        $scope.errorMessage = "";
        $('.popup__add').show();
    };

    $scope.hidePopup = function (popup) {
        $(popup).hide();
        $scope.Item = {};
        getAllNews();
    };

    getAllNews();
}