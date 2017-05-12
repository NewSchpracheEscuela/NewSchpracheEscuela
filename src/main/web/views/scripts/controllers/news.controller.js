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
                for (let item = 0; item < $scope.all_news.length; item++){
                    getAuthor(item);
                }
            }
        });
    };

    var getAuthor= function (item ) {
        let user_id = $scope.all_news[item]['author'];
        RequestService.getItemById('/users/'+user_id, function(err,data){
            if (err){
                alert(err.message);
            }else{
                $scope.all_news[item]['author'] = data['firstName']+" "+ data['lastName'];
            }
        });
    };


    getAllNews();
}