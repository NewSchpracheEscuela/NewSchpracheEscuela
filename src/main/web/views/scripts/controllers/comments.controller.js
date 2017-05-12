/**
 * Created by Ника Тихоновец on 01.05.2017.
 */

nseApp.controller('CommentsController', CommentsController);

function CommentsController($scope, RequestService) {
    var getAllComments = function () {
        RequestService.getAll('/comments', function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.all_comments = data;
                for (let item = 0; item < $scope.all_comments.length; item++){
                    getAuthor(item);
                    getCourse(item);
                }
            }
        });
    };

    var getAuthor= function (item ) {
        let user_id = $scope.all_comments[item]['author'];
        RequestService.getItemById('/users/'+user_id, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.all_comments[item]['author'] = data['firstName']+" "+ data['lastName'];
            }
        });
    };

    var getCourse= function (item ) {
        let course_id = $scope.all_comments[item]['course'];
        RequestService.getItemById('/courses/'+course_id, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.all_comments[item]['course'] = data['title'];
            }
        });
    };

    getAllComments();
}