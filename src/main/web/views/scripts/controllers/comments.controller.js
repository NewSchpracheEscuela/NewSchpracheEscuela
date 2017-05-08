/**
 * Created by Ника Тихоновец on 01.05.2017.
 */

nseApp.controller('CommentsController', CommentsController);

$("li.header__item:contains('Отзывы')").addClass('header__item--active');

function CommentsController($scope, RequestService) {
    var getAllComments = function () {
        RequestService.getAll('/comments', function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.all_comments = data;
            }
        });
    };

    // var getAuthor= function (item ) {
    //     let user_id = $scope.all_comments[item]['author'];
    //     RequestService.getItemById('/users/'+user_id, function(err,data){
    //         if (err){
    //             alert(err.message);
    //         }else{
    //             $scope.all_comments[item]['author'] = data['firstName']+" "+ data['lastName'];
    //         }
    //     });
    // };
    //
    // var getCourse= function (item ) {
    //     let course_id = $scope.all_comments[item]['course'];
    //     RequestService.getItemById('/courses/'+course_id, function(err,data){
    //         if (err){
    //             alert(err.message);
    //         }else{
    //             $scope.all_comments[item]['course'] = data['title'];
    //         }
    //     });
    // };

    $scope.getItems = function (url) {
        RequestService.getAll(url, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.items = data;
            }
        });
    } ;

    $scope.Item = {};
    $scope.addItem = function () {
        let data = $scope.Item["formKeys"];
        RequestService.addItem('/comments', data, function (err, data) {
            if(err){
                $scope.errorMessage = err.code;
            } else{
                $scope.hidePopup('.popup__add');
                getAllComments();
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
        getAllComments();
    };


    getAllComments();
}