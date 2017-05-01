/**
 * Created by Ника Тихоновец on 30.04.2017.
 */


nseApp.controller('IndexController', IndexController);

function IndexController($scope, IndexService) {
    $scope.slides =IndexService.getImagesSlide();
    $scope.choices = IndexService.getChoices();
    $scope.numbers = IndexService.getNumberChoice();


    $scope.changeTypeChoice = function (event, optionIndex) {
        $(".container__text").parent().find('.container__text').removeClass("container__text--active");
        $("#" + optionIndex).addClass("container__text--active");
        $(".container__number").parent().find('.container__number').removeClass("container__number--active");
        $(event.target).addClass("container__number--active");
    };
}